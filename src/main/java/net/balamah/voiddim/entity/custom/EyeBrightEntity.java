package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ShootingCooldownUser;
import net.balamah.voiddim.interfaces.MagnetTargetUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.sound.ModSounds;

public class EyeBrightEntity extends CorruptedHostileEntity
	implements ShootingCooldownUser, MagnetTargetUser
{
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();
	public final AnimationState attack3AnimationState = new AnimationState();
	public final AnimationState shoot1AnimationState = new AnimationState();
	public final AnimationState shoot2AnimationState = new AnimationState();
	public final AnimationState shoot3AnimationState = new AnimationState();
	public final AnimationState shoot4AnimationState = new AnimationState();
	public final AnimationState magnettedAttackAnimationState = new AnimationState();

	protected int idleAnimationTimeout;
	protected int attackInterval;

	protected int shootCoooldown = 80;
	protected int shootTicks = this.random.nextInt(30);

	protected int magnetTargetCoooldown = 80;
	protected int magnetTargetTicks = this.random.nextInt(100);
	
	protected AnimationState[] attackAnimations = {
		this.attack1AnimationState, this.attack2AnimationState, this.attack3AnimationState
	};

	protected AnimationState[] shootAnimations = {
		this.shoot1AnimationState, this.shoot2AnimationState, this.shoot3AnimationState
	};

	public EyeBrightEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 20)
			.add(EntityAttributes.STEP_HEIGHT, 1)
			.add(EntityAttributes.ATTACK_DAMAGE, 15.0F)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.2f)
			.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.6f)
			.add(EntityAttributes.MAX_HEALTH, 150);
	}

	@Override
	public int getShootCooldown() {
		return this.shootCoooldown;
	}

	@Override
	public int getShootTicks() {
		return this.shootTicks;
	}

	@Override
	public void setShootTicks(int ticks) {
		this.shootTicks = ticks;
	}

	@Override
	public int getMagnetTargetCooldown() {
		return this.magnetTargetCoooldown;
	}

	@Override
	public int getMagnetTargetTicks() {
		return this.magnetTargetTicks;
	}

	@Override
	public void setMagnetTargetTicks(int ticks) {
		this.magnetTargetTicks = ticks;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}

		if (this.shootTicks > 0) {
			this.shootTicks--;
		}

		if (this.magnetTargetTicks > 0) {
			this.magnetTargetTicks--;
		}
	}

	@Override
	public void mobTick(ServerWorld world) {
		super.mobTick(world);

		if (this.getEntityWorld().isClient()) {
			this.setupAnimationStates();
		}

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.sendEntityStatus(this, ModEntityStatuses.STOP_ATTACK);
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
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.attackAnimations);
				break;
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.attackAnimations);
				this.playRandomAnimation(this.attackAnimations);
				break;
			case ModEntityStatuses.SHOOT:
				this.stopAnimations(this.shootAnimations);
				this.playRandomAnimation(this.shootAnimations);
				break;
			case ModEntityStatuses.SPECIAL_ATTACK:
				this.magnettedAttackAnimationState.start(status);
				break;
			default:
				super.handleStatus(status);	
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		// TODO: Fix magnetting
		this.goalSelector.add(1, new MagnetTargetGoal<EyeBrightEntity>(this, 10, 15));
		this.goalSelector.add(
			2, new EyeBrightShootHeadGoal(
				this, SoundEvents.ENTITY_PLAYER_BREATH, ModSounds.EYE_BRIGHT_SHOOT_PREPARE, 2, 6
			)
		);
	}

	protected void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 40;
			this.idleAnimationState.start(this.age);
		} else {
			--this.idleAnimationTimeout;
		}
	}
}
