package net.balamah.voiddim.entity.custom.base;

import java.util.List;

import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public abstract class StaringEntity extends SunBurningEntity {
	protected Class<? extends Entity>[] projectileEntities = new Class[]{
		Arrow.class,
		PrimedTnt.class,
		BedrockBombEntity.class,
		LargeFireball.class
	};

	public StaringEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.FOLLOW_RANGE, 100)
			.add(Attributes.MOVEMENT_SPEED, 0.0F)
			.add(Attributes.MAX_HEALTH, 20);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide()) {
			LivingEntity target = this.getTarget();
			if (this.canSeeClosePlayer(target)) {
				this.disappear(target);
			}

			if (this.areProjectilesNearby(this.level())) {
				this.fade();
			}
		}
	}

	protected boolean canSeeClosePlayer(LivingEntity entity) {
		/*
		 * Return true, if player entity exists and located in within 10 blocks
		 * and both entities see each other
		 */
		return entity != null && entity instanceof Player target &&
			this.distanceTo(target) < 10 && this.hasLineOfSight(target) && target.hasLineOfSight(this);
	}

	protected boolean areProjectilesNearby(Level world) {
		int radius = 10;
		Vec3 position = new Vec3(this.getX(), this.getY(), this.getZ());
		
        AABB box = new AABB(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

		List<Entity> nearProjectileEntities = world.getEntities(
			this,
			box,
			entity -> (entity instanceof Projectile || entity instanceof PrimedTnt)
		);

		if (!nearProjectileEntities.isEmpty()) {
			return true;
		}

		return false;
	}

	protected void disappear(LivingEntity target) {
		if (target.hasEffect(ModEffects.DIVINE_PROTECTION)) {
			this.kill((ServerLevel) this.level());
		} else {
			target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
			this.fade();
		}
	}

	protected void fade() {
		this.makeSound(SoundEvents.WITHER_SPAWN);
		this.discard();
	}

	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(0, McCodeHelper.getTargetGoal(this, Player.class));

		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 100.0F));
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.STARING_ENTITY_DEATH;
	}
}
