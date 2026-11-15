package net.balamah.voiddim.entity.custom;

import org.jspecify.annotations.Nullable;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;

// TODO: Implement entity GhostEntity
public class GhostEntity extends CorruptedHostileEntity implements NeutralMob {
    public final AnimationState walkAnimationState = new AnimationState();

	private long persistentAngerEndTime;
	private @Nullable EntityReference<LivingEntity> persistentAngerTarget;

	public GhostEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.2F)
			.add(Attributes.ATTACK_DAMAGE, 13.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 25);
	}

	@Override
	protected void initTargets() {
		this.targetSelector.addGoal(
			2, new NearestAttackableTargetGoal<>(this, Player.class, 5, false, false, this::isAngryAt)
		);
		
		this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
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
	public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> persistentAngerTarget) {
		this.persistentAngerTarget = persistentAngerTarget;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setTimeToRemainAngry(TimeUtil.rangeOfSeconds(120, 160).sample(this.random));
	}

	@Override
	protected @Nullable SoundEvent getAmbientSound() {
		// TODO Auto-generated method stub
		return super.getAmbientSound();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		// TODO Auto-generated method stub
		return super.getHurtSound(source);
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO Auto-generated method stub
		return super.getDeathSound();
	}
}
