package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;

public class CorruptedBlazeEntity extends BlazeEntity {
	public CorruptedBlazeEntity(EntityType<? extends BlazeEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.MAX_HEALTH, 35)
			.add(EntityAttributes.ATTACK_DAMAGE, 12.0)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.4F)
			.add(EntityAttributes.FOLLOW_RANGE, 16.0);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(4, new ShootCorruptedFireballGoal(this));
		this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0F));
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));

		this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
		this.targetSelector.add(
			0, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, true)
		);
		this.targetSelector.add(
			3, new ActiveTargetGoal<PassiveEntity>(this, PassiveEntity.class, true)
		);
	}

	@Override
	public boolean handleFallDamage(
		double fallDistance, float damagePerDistance, DamageSource damageSource
	) {
		return false;
	}

	@Override
	public boolean hurtByWater() {
		return false;
	}

	@Override
	public void tickMovement() {
		World world = this.getEntityWorld();

		if (world.isClient() && this.random.nextInt(10) == 0) {
			for (int i = 0; i < 2; i++) {
				world.addParticleClient(
					ModParticleTypes.CORRUPTED_FLAME,
					this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5),
					0.0, 0.0, 0.0
				);
			}
		}

		super.tickMovement();
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);

		if (hit && (target instanceof PlayerEntity playerEntity)) {
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
