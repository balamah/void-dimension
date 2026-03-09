package net.balamah.voiddim.entity.custom.base;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.sound.ModSounds;

public abstract class StaringEntity extends SunBurningEntity {
	protected Class<? extends Entity>[] projectileEntities = new Class[]{
		ArrowEntity.class,
		TntEntity.class,
		BedrockBombEntity.class,
		FireballEntity.class
	};

	public StaringEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 100)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.0F)
			.add(EntityAttributes.MAX_HEALTH, 20);
	}

	@Override
	public void tick() {
		super.tick();

		LivingEntity target = this.getTarget();
		if (this.canSeeClosePlayer(target)) {
			this.disappear(target);
		}

		if (this.areProjectilesNearby(this.getEntityWorld())) {
			this.fade();
		}
	}

	protected boolean canSeeClosePlayer(LivingEntity entity) {
		/*
		 * Return true, if player entity exists and located in within 10 blocks
		 * and both entities see each other
		 */
		return entity != null && entity instanceof PlayerEntity target &&
			this.distanceTo(target) < 10 && this.canSee(target) && target.canSee(this);
	}

	protected boolean areProjectilesNearby(World world) {
		int radius = 4;
		Vec3d position = new Vec3d(this.getX(), this.getY(), this.getZ());
		
        Box box = new Box(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

		for (Class<? extends Entity> projectileEntity : this.projectileEntities) {
			List<? extends Entity> nearProjectileEntities = world.getEntitiesByClass(
				projectileEntity, box, entity -> entity != null
			);

			if (!nearProjectileEntities.isEmpty()) {
				return true;
			}
		}

		return false;
	}

	protected void disappear(LivingEntity target) {
		if (target.hasStatusEffect(ModEffects.DIVINE_PROTECTION)) {
			this.kill((ServerWorld) this.getEntityWorld());
		} else {
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
			this.fade();
		}
	}

	protected void fade() {
		this.playSound(SoundEvents.ENTITY_WITHER_SPAWN);
		this.discard();
	}

	@Override
	protected void initGoals() {
		this.targetSelector.add(0, McCodeHelper.getTargetGoal(this, PlayerEntity.class));

		this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 100.0F));
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.STARING_ENTITY_DEATH;
	}
}
