package net.balamah.voiddim.entity.custom;

import java.util.function.Function;
import java.util.Optional;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.World;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;

public class VoidSphereEntity extends AbstractWindCharge {
	public static final ExplosionBehavior EXPLOSION_BEHAVIOR =
		new AdvancedExplosionBehavior(
			true, false,
			Optional.of(1.22F),
			Registries.BLOCK.get(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS)
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
	protected void explode(Vec3d pos) {
		this.level()
			.explode(
				this,
				null,
				EXPLOSION_DAMAGE_CALCULATOR,
				pos.getX(),
				pos.getY(),
				pos.getZ(),
				1.2F,
				false,
				World.ExplosionSourceType.TRIGGER,
				ParticleTypes.GUST_EMITTER_SMALL,
				ParticleTypes.GUST_EMITTER_LARGE,
				WeightedList.of(),
				ModSounds.VOID_SPHERE_BURST
			);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (this.level() instanceof ServerWorld serverWorld) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = entityHitResult.getEntity();
			if (attackerEntity != null) attackerEntity.onAttacking(target);

			DamageSource damageSource = ModDamageSources.corruption(serverWorld);
			boolean damage = target.hurtServer(serverWorld, damageSource, 18.5f);

			if (damage && target instanceof LivingEntity targetLivingEntity) {
				targetLivingEntity.addStatusEffect(
					new StatusEffectInstance(ModEffects.CORRUPTION, 20, 1)
				);
				
				EnchantmentHelper.onTargetDamaged(
					serverWorld, targetLivingEntity, damageSource
				);
			}

			Vec3d position = new Vec3d(this.getX(), this.getY(), this.getZ());

			this.explode(position);
		}
	}
}
