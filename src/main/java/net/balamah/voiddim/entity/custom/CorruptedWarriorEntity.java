package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.DarkGraspUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.entity.ModEntities;

public class CorruptedWarriorEntity extends BossEntity implements DarkGraspUser {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState strongAttackAnimationState = new AnimationState();
	public final AnimationState strongestAttackAnimationState = new AnimationState();
	public final AnimationState summonProjectileAnimationState = new AnimationState();
	public final AnimationState specialAttackAnimationState = new AnimationState();
	public final AnimationState normalAttack1AnimationState = new AnimationState();
	public final AnimationState normalAttack2AnimationState = new AnimationState();
	public final AnimationState normalAttack3AnimationState = new AnimationState();

	protected final int darkGraspCooldown = 65;

	protected int attackInterval;
	protected int darkGraspTicks;

	protected AnimationState[] normalAttackAnimations = {
		this.normalAttack1AnimationState,
		this.normalAttack2AnimationState,
		this.normalAttack3AnimationState
	};

	public CorruptedWarriorEntity(
		EntityType<? extends Monster> entityType, Level world
	) {
		super(entityType, world);

		this.xpReward = 15;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0.28F)
			.add(Attributes.MAX_HEALTH, 365)
			.add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.ATTACK_DAMAGE, 11.5F)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public int getDarkGraspInvokeCooldown() {
		return this.darkGraspCooldown;
	}

	@Override
	public int getDarkGraspInvokeTicks() {
		return this.darkGraspTicks;
	}

	@Override
	public void setDarkGraspInvokeTicks(int ticks) {
		this.darkGraspTicks = ticks;
	}

	@Override
	public SoundEvent getDarkGraspSound() {
		return ModSounds.CORRUPTED_WARRIOR_DARK_GRASP_PREPARE;
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.SPECIAL_ATTACK:
				this.specialAttackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.summonProjectileAnimationState.stop();
				break;
			case ModEntityStatuses.STOP_SPECIAL_ATTACK:
				this.specialAttackAnimationState.stop();
				this.strongAttackAnimationState.stop();
				this.strongestAttackAnimationState.stop();
				break;
			case ModEntityStatuses.STRONG_ATTACK:
				this.strongAttackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.STRONGEST_ATTACK:
				this.strongestAttackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.PROJECTILE_INVOKE:
				this.summonProjectileAnimationState.start(this.tickCount);
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

			// A magic number, don't touch
			this.attackInterval = 8;
		}

		return result;
	}

	@Override
	protected void registerGoals() {
		/*
		 * TODO: Add goals
		 * - ShootProjectiles(ConsumedSoul)
		 * - ThunderWaveInvoke	:: play CorruptedWarriorAnimations.SPECIAL_ATTACK
		 */
		super.registerGoals();

		Goal summonEntitiesGoal =
			new SummonEntitiesGoal<CorruptedWarriorEntity, VoidBoundServantEntity>(
				this, VoidBoundServantEntity.class, ModEntities.VOID_BOUND_SERVANT, 10
			);

		// TODO: Replace with ShootProjectiles
		Goal shootingGoal = new ShootProjectileGoal<CorruptedWarriorEntity, ConsumedSoulEntity>(
			this, world -> new ConsumedSoulEntity(ModEntities.CONSUMED_SOUL, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50, 60
		);

		this.goalSelector.addGoal(1, shootingGoal);
		// TODO: Restore goals
		// this.goalSelector.addGoal(4, new DarkGraspInvokeGoal<>(this, 5, 0, 7));
		// this.goalSelector.addGoal(5, summonEntitiesGoal);
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

		if (this.darkGraspTicks > 0) {
			this.darkGraspTicks--;
		}
	}
}
