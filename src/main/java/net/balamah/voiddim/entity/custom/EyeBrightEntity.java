package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class EyeBrightEntity extends CorruptedHostileEntity {
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
			.add(EntityAttributes.MOVEMENT_SPEED, 0.15f)
			.add(EntityAttributes.MAX_HEALTH, 150);
	}

	@Override
	public void mobTick(ServerWorld world) {
		super.mobTick(world);

		if (this.getEntityWorld().isClient()) {
			this.setupAnimationStates();
		}

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.sendEntityStatus(
				this, ModEntityStatuses.STOP_ATTACK
			);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
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

	protected void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 40;
			this.idleAnimationState.start(this.age);
		} else {
			--this.idleAnimationTimeout;
		}
	}
}
