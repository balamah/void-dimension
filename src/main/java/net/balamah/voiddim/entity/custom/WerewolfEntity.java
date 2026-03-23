package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class WerewolfEntity extends CorruptedHostileEntity {
	public AnimationState walkAnimationState = new AnimationState();
	public AnimationState attackHitAnimationState = new AnimationState();
	public AnimationState attackBiteAnimationState = new AnimationState();

	protected int attackTicksLeft;

	public WerewolfEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);

		this.experiencePoints = 15;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 64)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.ATTACK_DAMAGE, 7.0F)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		this.attackTicksLeft = 10;

		byte status = ModEntityStatuses.ATTACK;

		if (world.random.nextFloat() < 0.15f &&
			target instanceof PlayerEntity playerEntity
		) {
			McCodeHelper.disableShield(playerEntity);

			status = ModEntityStatuses.BREAK_SHIELD;
		}

		world.sendEntityStatus(this, status);

		return super.tryAttack(world, target);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();

		if (this.attackTicksLeft > 0) {
			this.attackTicksLeft--;
		}
	}

	public int getAttackTicksLeft() {
		return this.attackTicksLeft;
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.attackBiteAnimationState.start(this.age);
				this.attackTicksLeft = 10;
				break;
			case ModEntityStatuses.BREAK_SHIELD:
				this.attackHitAnimationState.start(this.age);
				this.attackTicksLeft = 10;
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.WEREWOLF_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.WEREWOLF_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.WEREWOLF_HIT;
	}
}
