package net.balamah.voiddim.entity.custom.base;

import org.jspecify.annotations.Nullable;

import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class CorruptedNeuteralEntity
	extends CorruptedHostileEntity implements NeutralMob
{
	protected long persistentAngerEndTime;
	protected @Nullable EntityReference<LivingEntity> persistentAngerTarget;

	protected boolean isAggressive;

	private static final EntityDataAccessor<Boolean> HAS_TARGET =
		SynchedEntityData.defineId(CorruptedNeuteralEntity.class, EntityDataSerializers.BOOLEAN);

	public CorruptedNeuteralEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public long getPersistentAngerEndTime() {
		return this.persistentAngerEndTime;
	}

	@Override
	public void setPersistentAngerEndTime(long endTime) {
		this.persistentAngerEndTime = endTime;
	}

	@Override
	public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(
		@Nullable EntityReference<LivingEntity> persistentAngerTarget
	) {
		this.persistentAngerTarget = persistentAngerTarget;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setTimeToRemainAngry(TimeUtil.rangeOfSeconds(120, 160).sample(this.random));
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide()) {
			boolean hasTarget = this.getTarget() != null;
			this.entityData.set(HAS_TARGET, hasTarget);
		}
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
		boolean hurt = super.hurtServer(level, source, amount);

		if (hurt && source.getEntity() instanceof LivingEntity attacker) {
			this.setPersistentAngerTarget(EntityReference.of(attacker));
			this.startPersistentAngerTimer();
			this.setTarget(attacker);
		}

		return hurt;
	}

	public boolean isAggressive() {
		return this.isAggressive;
	}

	public void setAggressive(boolean isAggressive) {
		this.isAggressive = isAggressive;
	}

	public boolean hasTargetSynced() {
		return this.entityData.get(HAS_TARGET);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(HAS_TARGET, false);
	}

	@Override
	protected void initTargets() {
		this.targetSelector.addGoal(
			2,
			new NearestAttackableTargetGoal<>(
				this, Player.class, 5, false, false, this::isAngryAt
			)
		);
		
		this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
	}
}
