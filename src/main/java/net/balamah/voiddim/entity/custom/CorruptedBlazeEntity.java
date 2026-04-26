package net.balamah.voiddim.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;

public class CorruptedBlazeEntity extends Blaze {
	public CorruptedBlazeEntity(EntityType<? extends Blaze> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 35)
			.add(Attributes.ATTACK_DAMAGE, 12.0)
			.add(Attributes.MOVEMENT_SPEED, 0.4F)
			.add(Attributes.FOLLOW_RANGE, 16.0);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new ShootCorruptedFireballGoal(this));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(
			0, new NearestAttackableTargetGoal<Player>(this, Player.class, true)
		);
		this.targetSelector.addGoal(
			3, new NearestAttackableTargetGoal<AgeableMob>(this, AgeableMob.class, true)
		);
	}

	@Override
	public boolean causeFallDamage(
		double fallDistance, float damagePerDistance, DamageSource damageSource
	) {
		return false;
	}

	@Override
	public boolean isSensitiveToWater() {
		return false;
	}

	@Override
	public void aiStep() {
		Level world = this.level();

		if (world.isClientSide() && this.random.nextInt(10) == 0) {
			for (int i = 0; i < 2; i++) {
				world.addParticle(
					ModParticleTypes.CORRUPTED_FLAME,
					this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5),
					0.0, 0.0, 0.0
				);
			}
		}

		super.aiStep();
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean hit = super.doHurtTarget(world, target);

		if (hit && (target instanceof Player playerEntity)) {
			McCodeHelper.disableShield(playerEntity);
		}

		return hit;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.CORRUPTED_BLAZE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.CORRUPTED_BLAZE_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.CORRUPTED_BLAZE_DEATH;
	}
}
