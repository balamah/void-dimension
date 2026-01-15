package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;

public class CorruptedWarriorEntity extends BossEntity {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState strongAttackAnimationState = new AnimationState();
	public final AnimationState strongestAttackAnimationState = new AnimationState();
	public final AnimationState summonProjectileAnimationState = new AnimationState();
	public final AnimationState specialAttackAnimationState = new AnimationState();
	public final AnimationState normalAttack1AnimationState = new AnimationState();
	public final AnimationState normalAttack2AnimationState = new AnimationState();
	public final AnimationState normalAttack3AnimationState = new AnimationState();

	protected int attackInterval;

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
			.add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.MAX_HEALTH, 365)
			.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.ATTACK_DAMAGE, 14.5F)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.CORRUPTED_WARRIOR_ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.CORRUPTED_WARRIOR_SPECIAL_ATTACK:
				this.specialAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.CORRUPTED_WARRIOR_ATTACKS_STOP:
				this.stopAnimations(this.normalAttackAnimations);
				this.specialAttackAnimationState.stop();
				this.strongAttackAnimationState.stop();
				this.strongestAttackAnimationState.stop();
				this.summonProjectileAnimationState.stop();
				break;
			case ModEntityStatuses.CORRUPTED_WARRIOR_STRONG_ATTACK:
				this.strongAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.CORRUPTED_WARRIOR_STRONGEST_ATTACK:
				this.strongestAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.CORRUPTED_WARRIOR_SUMMON_PROJECTILE:
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
			world.sendEntityStatus(this, ModEntityStatuses.CORRUPTED_WARRIOR_ATTACK);

			// A magic number, don't touch
			this.attackInterval = 8;
		}

		return result;
	}

	@Override
	protected void initGoals() {
		/*
		 * TODO: Add goals
		 * - SummonEntities(VoidBoundServant)
		 * - ShootProjectiles(ConsumedSoul)
		 * - DarkGraspInvoke    :: basically like invoker spike attack
		 * - ThunderWaveInvoke	:: play CorruptedWarriorAnimations.SPECIAL_ATTACK
		 */
		super.initGoals();

		this.goalSelector.add(4, new DarkGraspInvokeGoal(this));
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.sendEntityStatus(
				this, ModEntityStatuses.CORRUPTED_WARRIOR_ATTACKS_STOP
			);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}
	}
}
