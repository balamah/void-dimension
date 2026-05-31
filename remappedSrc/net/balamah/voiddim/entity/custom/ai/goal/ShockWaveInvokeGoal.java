package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.ShockWaveUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;

public class ShockWaveInvokeGoal<T extends BossEntity & ShockWaveUser> extends SlowMovementGoal<T>
{
	protected final int invocationTickCooldown;
	protected final float entitySpeed;
	protected final float radius;

	public ShockWaveInvokeGoal(T entity, int invocationTickCooldown, float radius) {
		super(entity);

		this.invocationTickCooldown = invocationTickCooldown;
		this.entitySpeed = this.entity.getMovementSpeed();

		this.radius = radius;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null &&
			target.distanceTo(this.entity) <= 15 &&
			this.entity.getShockWaveTicks() == 0 &&
			!this.entity.areAttacksStopped();
	}

	@Override
	public void start() {
		this.tick = 0;

		if (!this.entityAttributeInstance.hasModifier(this.attributeId)) {
			this.entityAttributeInstance.addTemporaryModifier(this.attributeModifier);
		}

		this.entity.setStopAttacks(true);
		this.addSpeedModifier();
		this.entity.playSound(SoundEvents.ENTITY_PLAYER_BREATH, 4, 1);
		this.sendEntityStatus(ModEntityStatuses.SHOCK_WAVE_INVOKE);
	}

	@Override
	public void tick() {
		super.tick();

		if (!(this.world instanceof ServerWorld serverWorld)) {
			return;
		}

		if (this.tick == this.invocationTickCooldown) {
			McCodeHelper.createShockWave(serverWorld, entity, radius);
		}
	}

	@Override
	public void stop() {
		super.stop();

		int shockWaveTicks =
			this.entity.getShockWaveCooldown() + this.entity.getRandom().nextInt(100);

		this.removeSpeedModifier();
		this.entity.setShockWaveTicks(shockWaveTicks);
		this.entity.setStopAttacks(false);
		this.entityAttributeInstance.removeModifier(this.attributeModifier);
		this.sendEntityStatus(ModEntityStatuses.SHOCK_WAVE_INVOKE_STOP);

		this.entity.attackCount = 0;
	}

	@Override
	public boolean shouldContinue() {
		return this.tick <= this.invocationTickCooldown;
	}
}
