package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WerewolfEntity extends CorruptedHostileEntity {
	public AnimationState walkAnimationState = new AnimationState();
	public AnimationState attackHitAnimationState = new AnimationState();
	public AnimationState attackBiteAnimationState = new AnimationState();

	protected int attackTicksLeft;

	public WerewolfEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.xpReward = 15;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.ATTACK_DAMAGE, 7.0F)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		this.attackTicksLeft = 10;

		byte status = ModEntityStatuses.ATTACK;

		if (this.level().getRandom().nextFloat() < 0.15f &&
			target instanceof Player playerEntity
		) {
			McCodeHelper.disableShield(playerEntity);

			status = ModEntityStatuses.BREAK_SHIELD;
		}

		this.level().broadcastEntityEvent(this, status);

		return super.doHurtTarget(target);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackTicksLeft > 0) {
			this.attackTicksLeft--;
		}
	}

	public int getAttackTicksLeft() {
		return this.attackTicksLeft;
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.attackBiteAnimationState.start(this.tickCount);
				this.attackTicksLeft = 10;
				break;
			case ModEntityStatuses.BREAK_SHIELD:
				this.attackHitAnimationState.start(this.tickCount);
				this.attackTicksLeft = 10;
				break;
			default: super.handleEntityEvent(status);
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
