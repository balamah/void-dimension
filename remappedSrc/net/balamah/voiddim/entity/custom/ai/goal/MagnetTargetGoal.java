package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.OneShotDamageGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.MagnetTargetUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntityStatuses;

import java.util.Random;

public class MagnetTargetGoal<T extends CorruptedHostileEntity & MagnetTargetUser>
	extends OneShotDamageGoal<T>
{
	protected final Random random = new Random();

	protected final int preparationTick;
	protected final int executionTick;
	protected final double magnetBoxSize;
	protected final ParticleEffect boxParticleIndicator;

	protected boolean finishedGoal;
	protected Box magnetBox;

	public MagnetTargetGoal(
		T entity, int preparationTick, int executionTick, double magnetBoxSize,
		ParticleEffect boxParticleIndicator
	) {
		super(entity);

		this.preparationTick = preparationTick;
		this.executionTick = executionTick;
		this.magnetBoxSize = magnetBoxSize;
		this.boxParticleIndicator = boxParticleIndicator;
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
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

		this.finishedGoal = false;
		this.removeModifier(this.attributeInstance, this.attributeModifier);

		this.entity.setMagnetTargetTicks(
			this.entity.getMagnetTargetCooldown() + this.random.nextInt(20)
		);
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null && !this.finishedGoal;
	}

	@Override
	public void tick() {
		super.tick();

		this.displayParticles(this.world, this.magnetBox.getCenter());

		if (this.tick == this.preparationTick) {
			this.addModifier(this.attributeInstance, this.attributeId, this.attributeModifier);

			this.playSound(ModSounds.MAGNET_PREPARE);
		}

		if (this.tick == this.executionTick) {
			this.sendEntityStatus(ModEntityStatuses.SPECIAL_ATTACK);
			this.pullEntities(world, this.magnetBox);

			this.playSound(ModSounds.MAGNET_EXECUTE);
		}

		if (this.tick > this.executionTick) {
			this.finishedGoal = true;
		}
	}

	protected void pullEntities(World world, Box box) {
		for (Entity victim : world.getOtherEntities(entity, box)) {
			double heightOffset = this.getHeightOffset(victim);

			double distanceToAttacker = victim.distanceTo(this.entity);
			Vec3d directionToAttacker = this.entity.getPos()
					.subtract(victim.getPos()).normalize();

			double pullingStrength = (distanceToAttacker - heightOffset) / 2;
			Vec3d velocityToAttacker = directionToAttacker.multiply(pullingStrength);
			Vec3d newVelocity = victim.getVelocity().add(velocityToAttacker);

			victim.setVelocity(newVelocity);

			// Pull players also. Player's velocity cannot be changed,
			// unless EntityVelocityUpdateS2CPacket is sent
			if (victim instanceof ServerPlayerEntity player) {
				player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(player));
			}
		}
	}

	protected double getHeightOffset(Entity victim) {
		double heightOffset = 0;
		if (victim.getY() > this.entity.getY()) {
			heightOffset = (victim.getY() - this.entity.getY()) / 1.1;
		}

		return heightOffset;
	}

	protected void createMagnetBox() {
		LivingEntity target = this.entity.getTarget();

		double x = target.getX();
		double y = target.getY();
		double z = target.getZ();

		this.magnetBox = new Box(
			x - this.magnetBoxSize, y, z - this.magnetBoxSize,
			x + this.magnetBoxSize, y + 2, z + this.magnetBoxSize
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
				this.boxParticleIndicator, position.x, position.y, position.z, 5, vz, vx, vy, 0.1);
	}

	protected void playSound(SoundEvent sound) {
		McCodeHelper.playSound(
			this.world, sound, this.magnetBox.getCenter(), SoundCategory.AMBIENT, 1, 1
		);
	}
}
