package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.DarkGraspUser;
import net.balamah.voiddim.sound.ModSounds;
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
		EntityType<? extends HostileEntity> entityType, World world
	) {
		super(entityType, world);

		this.experiencePoints = 15;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 64)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.28F)
			.add(EntityAttributes.MAX_HEALTH, 365)
			.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.ATTACK_DAMAGE, 11.5F)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
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
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.SPECIAL_ATTACK:
				this.specialAttackAnimationState.start(this.age);
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
				this.strongAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.STRONGEST_ATTACK:
				this.strongestAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.PROJECTILE_INVOKE:
				this.summonProjectileAnimationState.start(this.age);
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean result = super.tryAttack(world, target);
		
		if (result) {
			world.sendEntityStatus(this, ModEntityStatuses.ATTACK);

			// A magic number, don't touch
			this.attackInterval = 8;
		}

		return result;
	}

	@Override
	protected void initGoals() {
		/*
		 * TODO: Add goals
		 * - ShootProjectiles(ConsumedSoul)
		 * - ThunderWaveInvoke	:: play CorruptedWarriorAnimations.SPECIAL_ATTACK
		 */
		super.initGoals();

		Goal summonEntitiesGoal = new SummonEntitiesGoal<CorruptedWarriorEntity, VoidBoundServantEntity>(
			this, VoidBoundServantEntity.class, ModEntities.VOID_BOUND_SERVANT, 10
		);

		this.goalSelector.add(5, summonEntitiesGoal);
		this.goalSelector.add(4, new DarkGraspInvokeGoal<>(this, 5, 0, 7));
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.sendEntityStatus(this, ModEntityStatuses.STOP_ATTACK);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}

		if (this.darkGraspTicks > 0) {
			this.darkGraspTicks--;
		}
	}
}
