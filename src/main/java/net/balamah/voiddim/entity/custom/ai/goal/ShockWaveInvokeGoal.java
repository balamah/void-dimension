package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;

public class ShockWaveInvokeGoal extends Goal {
	protected final ShatteredSentinelMasterEntity entity;
	protected final int invocationTickCooldown;
	protected final float entitySpeed;
	protected final float radius;

	protected final Identifier attributeId = Identifier.ofVanilla("speed");
	protected final EntityAttributeModifier attributeModifier =
		new EntityAttributeModifier(
			this.attributeId, -2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final EntityAttributeInstance entityAttributeInstance;

	protected int tick;

	public ShockWaveInvokeGoal(
		ShatteredSentinelMasterEntity entity, int invocationTickCooldown, float radius
	) {
		this.entity = entity;
		this.invocationTickCooldown = invocationTickCooldown;
		this.entitySpeed = this.entity.getMovementSpeed();
		this.entityAttributeInstance =
			this.entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);

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
		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHOCK_WAVE_INVOKE
		);

		this.entity.playSound(SoundEvents.ENTITY_PLAYER_BREATH, 4, 1);
	}

	@Override
	public void tick() {
		World world = this.entity.getEntityWorld();

		if (!(world instanceof ServerWorld serverWorld)) return;

		this.tick++;

		if (this.tick == this.invocationTickCooldown) {
			McCodeHelper.createShockWave(serverWorld, entity, radius);
		}
	}

	@Override
	public void stop() {
		super.stop();

		int shockWaveTicks =
			this.entity.getShockWaveCooldown() + this.entity.getRandom().nextInt(100);

		this.entity.setShockWaveTicks(shockWaveTicks);
		this.entity.setStopAttacks(false);
		this.entityAttributeInstance.removeModifier(this.attributeModifier);
		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHOCK_WAVE_INVOKE_STOP
		);

		this.entity.attackCount = 0;
		this.tick = 0;
	}

	@Override
	public boolean shouldContinue() {
		return this.tick <= this.invocationTickCooldown;
	}
}
