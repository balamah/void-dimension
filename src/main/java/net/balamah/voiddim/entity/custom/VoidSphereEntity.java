package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.function.Function;
import java.util.Optional;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.sound.ModSounds;

public class VoidSphereEntity extends AbstractWindChargeEntity {
	public static final ExplosionBehavior EXPLOSION_BEHAVIOR =
		new AdvancedExplosionBehavior(
			true, false,
			Optional.of(1.22F),
			Registries.BLOCK.getOptional(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS)
			.map(Function.identity())
		);

	public VoidSphereEntity(VoidHarbingerEntity entity, World world) {
		super(ModEntities.VOID_SPHERE, world, entity, entity.getX(),
			  entity.getChargeY(), entity.getZ());
	}

	public VoidSphereEntity(EntityType<? extends VoidSphereEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void createExplosion(Vec3d pos) {
		this.getEntityWorld()
			.createExplosion(
				this,
				null,
				EXPLOSION_BEHAVIOR,
				pos.getX(),
				pos.getY(),
				pos.getZ(),
				1.2F,
				false,
				World.ExplosionSourceType.TRIGGER,
				ParticleTypes.GUST_EMITTER_SMALL,
				ParticleTypes.GUST_EMITTER_LARGE,
				Pool.empty(),
				ModSounds.VOID_SPHERE_BURST
			);
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);

		if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = entityHitResult.getEntity();
			if (attackerEntity != null) attackerEntity.onAttacking(target);

			DamageSource damageSource = ModDamageSources.corruption(serverWorld);
			boolean damage = target.damage(serverWorld, damageSource, 18.5f);

			if (damage && target instanceof LivingEntity targetLivingEntity) {
				targetLivingEntity.addStatusEffect(
					new StatusEffectInstance(ModEffects.CORRUPTION, 20, 1)
				);
				
				EnchantmentHelper.onTargetDamaged(
					serverWorld, targetLivingEntity, damageSource
				);
			}

			Vec3d position = new Vec3d(this.getX(), this.getY(), this.getZ());

			this.createExplosion(position);
		}
	}
}
