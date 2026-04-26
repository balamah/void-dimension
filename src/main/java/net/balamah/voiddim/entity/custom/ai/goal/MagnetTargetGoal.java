package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.OneShotDamageGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.MagnetTargetUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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
	protected final ParticleOptions boxParticleIndicator;

	protected boolean finishedGoal;
	protected AABB magnetBox;

	public MagnetTargetGoal(
		T entity, int preparationTick, int executionTick, double magnetBoxSize,
		ParticleOptions boxParticleIndicator
	) {
		super(entity);

		this.preparationTick = preparationTick;
		this.executionTick = executionTick;
		this.magnetBoxSize = magnetBoxSize;
		this.boxParticleIndicator = boxParticleIndicator;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public boolean canUse() {
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
	public boolean canContinueToUse() {
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

	protected void pullEntities(Level world, AABB box) {
		for (Entity victim : world.getEntities(entity, box)) {
			double heightOffset = this.getHeightOffset(victim);

			double distanceToAttacker = victim.distanceTo(this.entity);
			Vec3 directionToAttacker = this.entity.position()
					.subtract(victim.position()).normalize();

			double pullingStrength = (distanceToAttacker - heightOffset) / 2;
			Vec3 velocityToAttacker = directionToAttacker.scale(pullingStrength);
			Vec3 newVelocity = victim.getDeltaMovement().add(velocityToAttacker);

			victim.setDeltaMovement(newVelocity);

			// Pull players also. Player's velocity cannot be changed,
			// unless EntityVelocityUpdateS2CPacket is sent
			if (victim instanceof ServerPlayer player) {
				player.connection.send(new ClientboundSetEntityMotionPacket(player));
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

		this.magnetBox = new AABB(
			x - this.magnetBoxSize, y, z - this.magnetBoxSize,
			x + this.magnetBoxSize, y + 2, z + this.magnetBoxSize
		);
	}

	protected void displayParticles(Level world, Vec3 position) {
		if (!(this.world instanceof ServerLevel serverWorld)) {
			return;
		}

		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble(5.5) * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		serverWorld.sendParticles(
				this.boxParticleIndicator, position.x, position.y, position.z, 5, vz, vx, vy, 0.1);
	}

	protected void playSound(SoundEvent sound) {
		McCodeHelper.playSound(
			this.world, sound, this.magnetBox.getCenter(), SoundSource.AMBIENT, 1, 1
		);
	}
}
