package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

import net.balamah.voiddim.entity.ModEntityStatuses;

public class ShootLightningGoal<T extends CorruptedHostileEntity> extends SlowMovementGoal<T> {
	protected Random random = new Random();
	protected Vec3d targetPosition;

	public ShootLightningGoal(T entity) {
		super(entity);
	}

	@Override
	public boolean canStart() {
		return this.entity.getTarget() != null;
	}

	@Override
	public boolean shouldContinue() {
		return this.canStart() && this.tick < 11;
	}

	@Override
	public void start() {
		super.start();

		this.targetPosition = this.getTargetPosition(this.entity.getTarget());
		this.sendEntityStatus(ModEntityStatuses.HEROBRINE_LIGHTNING_INVOKE);
		// TODO: Chance sound
		this.world.playSound(
			this.entity, targetPosition.x, targetPosition.y, targetPosition.z,
			SoundEvents.BLOCK_ANVIL_USE, SoundCategory.HOSTILE
		);
	}

	@Override
	public void stop() {
		super.stop();

		this.sendEntityStatus(ModEntityStatuses.HEROBRINE_LIGHTNING_INVOKE_STOP);
	}

	@Override
	public void tick() {
		super.tick();

		this.displayParticles(this.targetPosition);

		if (this.tick == 10) {
			this.summonLightningBolt(this.targetPosition);
		}
	}

	protected Vec3d getTargetPosition(LivingEntity target) {
		return target.getEntityPos();
	}

	protected void displayParticles(Vec3d position) {
		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble() * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		if (random.nextInt(5) == 0) {
			this.world.addParticleClient(
				ParticleTypes.SOUL, position.x, position.y, position.z, vx, vy, vz
			);
		}
	}

	protected void summonLightningBolt(Vec3d position) {
		LightningEntity lightningBolt = new LightningEntity(
			EntityType.LIGHTNING_BOLT, this.world
		);

		lightningBolt.setPosition(position);
		this.world.spawnEntity(lightningBolt);
	}
}
