package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Box;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.base.OneShotDamageGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.MagnetTargetUser;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.entity.ModEntityStatuses;

import java.util.Random;

public class MagnetTargetGoal<T extends CorruptedHostileEntity & MagnetTargetUser>
	extends OneShotDamageGoal<T>
{
	protected final Random random = new Random();
	protected final int preparationTick;
	protected final int executionTick;

	protected boolean pulledEntitiesInMagnet;
	protected Box magnetBox;

	public MagnetTargetGoal(T entity, int preparationTick, int executionTick) {
		super(entity);

		this.preparationTick = preparationTick;
		this.executionTick = executionTick;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && this.entity.distanceTo(target) > 5 &&
				this.entity.getMagnetTargetTicks() == 0;
	}

	@Override
	public void start() {
		super.start();

		this.createMagnetBox();
	}

	@Override
	public void stop() {
		super.stop();

		this.pulledEntitiesInMagnet = false;
		this.removeModifier(this.attributeInstance, this.attributeModifier);
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null && !this.pulledEntitiesInMagnet;
	}

	// TODO: Add code in conditions
	@Override
	public void tick() {
		super.tick();

		this.displayParticles(this.world, this.magnetBox.getCenter());

		if (this.tick == this.preparationTick) {
			this.addModifier(this.attributeInstance, this.attributeId, this.attributeModifier);
		}

		if (this.tick == this.executionTick) {
			this.sendEntityStatus(ModEntityStatuses.SPECIAL_ATTACK);
			this.pullEntities(world, this.magnetBox);

			this.pulledEntitiesInMagnet = true;
		}
	}

	protected void pullEntities(World world, Box box) {
		for (Entity victim : world.getCrammedEntities(entity, box)) {
			victim.setPosition(this.entity.getEntityPos());
		}
	}

	protected void createMagnetBox() {
		LivingEntity target = this.entity.getTarget();

		double x = target.getX();
		double y = target.getY();
		double z = target.getZ();

		this.magnetBox = new Box(
			x, y, z,
			x, y + 2, z
		);
	}

	protected void displayParticles(World world, Vec3d position) {
		if (!(this.world instanceof ServerWorld serverWorld)) {
			return;
		}

		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble(5.5) * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		serverWorld.spawnParticles(
			ModParticleTypes.CORRUPTION, position.x, position.y, position.z, 10, vz, vx, vy, 0.1
		);
	}
}
