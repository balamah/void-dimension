package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.ModNearestEntityTargetGoal;
import net.balamah.voiddim.entity.custom.ai.goal.RoarNearestMurdererPlayerTargetGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.RoarUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

// TODO: Implement entity WatcherEntity
public class WatcherEntity extends CorruptedHostileEntity implements RoarUser {
	public final AnimationState rageBaitAnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();

	protected boolean isScreaming;

	protected int attackInterval;

	protected AnimationState[] normalAttackAnimations = {
		this.attack1AnimationState,
		this.attack2AnimationState,
	};

	private static final EntityDataAccessor<Boolean> IS_SCREAMING =
		SynchedEntityData.defineId(WatcherEntity.class, EntityDataSerializers.BOOLEAN);

	public WatcherEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.4F)
			.add(Attributes.ATTACK_DAMAGE, 13.0F)
			.add(Attributes.STEP_HEIGHT, 2.0)
			.add(Attributes.MAX_HEALTH, 65);
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.ROAR:
				this.rageBaitAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.ROAR_STOP:
				this.rageBaitAnimationState.stop();
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	public boolean isScreaming() {
		return this.isScreaming;
	}

	@Override
	public void setScreaming(boolean screaming) {
		this.isScreaming = screaming;
	}

	public boolean hasScreamingSynced() {
		return this.entityData.get(IS_SCREAMING);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_SCREAMING, false);
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

			// A magic number, don't touch
			this.attackInterval = 14;
		}

		return result;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getTarget() == null) {
			this.getNavigation().stop();
		}

		if (!this.level().isClientSide()) {
			this.entityData.set(IS_SCREAMING, this.isScreaming());
		}
	}

	@Override
	protected void customServerAiStep(ServerLevel world) {
		super.customServerAiStep(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.broadcastEntityEvent(this, ModEntityStatuses.STOP_ATTACK);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}
	}

	@Override
	protected void initTargets() {
		// TODO: Change sound ModSounds.ENTITY303_DEATH
		this.targetSelector.addGoal(
			0, new RoarNearestMurdererPlayerTargetGoal<>(this, 32, ModSounds.ENTITY303_DEATH, 34)
		);

		this.targetSelector.addGoal(
			2, new ModNearestEntityTargetGoal<AgeableMob>(this, 32, AgeableMob.class)
		);
	}
}
