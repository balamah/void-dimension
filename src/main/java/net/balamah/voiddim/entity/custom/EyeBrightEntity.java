package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ShootingCooldownUser;
import net.balamah.voiddim.interfaces.MagnetTargetUser;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

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

	protected int shootCoooldown = 60;
	protected int shootTicks = this.random.nextInt(30);

	protected int magnetTargetCoooldown = 150;
	protected int magnetTargetTicks = this.random.nextInt(100);
	
	protected AnimationState[] attackAnimations = {
		this.attack1AnimationState, this.attack2AnimationState, this.attack3AnimationState
	};

	protected AnimationState[] shootAnimations = {
		this.shoot1AnimationState, this.shoot2AnimationState, this.shoot3AnimationState
	};

	public EyeBrightEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 25)
			.add(Attributes.STEP_HEIGHT, 1)
			.add(Attributes.ATTACK_DAMAGE, 15.0F)
			.add(Attributes.MOVEMENT_SPEED, 0.2f)
			.add(Attributes.KNOCKBACK_RESISTANCE, 0.6f)
			.add(Attributes.MAX_HEALTH, 150);
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

		if (this.level().isClientSide()) {
			this.setupAnimationStates();
		}
	}

	@Override
	public void customServerAiStep(ServerLevel world) {
		super.customServerAiStep(world);

		if (this.level().isClientSide()) {
			this.setupAnimationStates();
		}

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.broadcastEntityEvent(this, ModEntityStatuses.STOP_ATTACK);
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
	public void handleEntityEvent(byte status) {
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
				this.magnettedAttackAnimationState.start(this.tickCount);
				break;
			default:
				super.handleEntityEvent(status);	
		}
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(
			1, new MagnetTargetGoal<EyeBrightEntity>(this, 10, 20, 1, ModParticleTypes.CORRUPTION)
		);

		this.goalSelector.addGoal(
			2,
			new EyeBrightShootHeadGoal(
				this, SoundEvents.PLAYER_BREATH, ModSounds.EYE_BRIGHT_SHOOT_PREPARE, 2, 6
			)
		);
	}

	protected void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 40;
			this.idleAnimationState.start(this.tickCount);
		} else {
			--this.idleAnimationTimeout;
		}
	}
}
