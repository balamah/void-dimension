package net.balamah.voiddim.entity.custom;

import java.util.function.Function;
import java.util.Optional;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class VoidSphereEntity extends AbstractWindCharge {
	public static final ExplosionDamageCalculator EXPLOSION_BEHAVIOR =
		new SimpleExplosionDamageCalculator(
			true, false,
			Optional.of(1.22F),
			BuiltInRegistries.BLOCK.get(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS)
			.map(Function.identity())
		);

	public VoidSphereEntity(VoidHarbingerEntity entity, Level world) {
		super(ModEntities.VOID_SPHERE, world, entity, entity.getX(),
			  entity.getChargeY(), entity.getZ());
	}

	public VoidSphereEntity(EntityType<? extends VoidSphereEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void explode(Vec3 pos) {
		this.level()
			.explode(
				this,
				null,
				EXPLOSION_DAMAGE_CALCULATOR,
				pos.x(),
				pos.y(),
				pos.z(),
				1.2F,
				false,
				Level.ExplosionInteraction.TRIGGER,
				ParticleTypes.GUST_EMITTER_SMALL,
				ParticleTypes.GUST_EMITTER_LARGE,
				WeightedList.of(),
				ModSounds.VOID_SPHERE_BURST
			);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (this.level() instanceof ServerLevel serverWorld) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = entityHitResult.getEntity();
			if (attackerEntity != null) attackerEntity.setLastHurtMob(target);

			DamageSource damageSource = ModDamageSources.corruption(serverWorld);
			boolean damage = target.hurtServer(serverWorld, damageSource, 18.5f);

			if (damage && target instanceof LivingEntity targetLivingEntity) {
				targetLivingEntity.addEffect(
					new MobEffectInstance(ModEffects.CORRUPTION, 20, 1)
				);
				
				EnchantmentHelper.doPostAttackEffects(
					serverWorld, targetLivingEntity, damageSource
				);
			}

			Vec3 position = new Vec3(this.getX(), this.getY(), this.getZ());

			this.explode(position);
		}
	}
}
