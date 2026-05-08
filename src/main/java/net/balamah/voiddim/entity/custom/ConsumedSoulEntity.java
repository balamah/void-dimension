package net.balamah.voiddim.entity.custom;

import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.tags.BlockTags;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

public class ConsumedSoulEntity extends AbstractHurtingProjectile {
	protected final EntityType<?>[] immuneEntities = {
		ModEntities.DARK_GRASP, ModEntities.VOID_BOUND_SERVANT
	};

	public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR =
		new SimpleExplosionDamageCalculator(
			false, false, Optional.empty(),
			BuiltInRegistries.BLOCK.get(BlockTags.AIR).map(Function.identity())
		);

	public ConsumedSoulEntity(EntityType<? extends ConsumedSoulEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		super.onHitEntity(hitResult);

		if (this.level() instanceof ServerLevel world) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = hitResult.getEntity();

			if (Arrays.asList(this.immuneEntities).contains(target.getType())) {
				return;
			}

			if (attackerEntity != null) {
				attackerEntity.setLastHurtMob(target);
			}

			DamageSource damageSource = ModDamageSources.corruption(world);
			boolean damage = target.hurtServer(world, damageSource, 18.5f);

			if (damage && target instanceof LivingEntity targetLivingEntity) {
				targetLivingEntity.addEffect(
					new MobEffectInstance(ModEffects.CORRUPTION, 20, 1)
				);
				
				EnchantmentHelper.doPostAttackEffects(world, targetLivingEntity, damageSource);
			}
			
			this.disappear();
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult hitResult) {
		super.onHitBlock(hitResult);

		if (!this.level().isClientSide()) {
			this.disappear();
		}
	}
	
	protected void disappear() {
		this.explode(this.position());

		this.discard();
	}

	protected void explode(Vec3 pos) {
		this.level()
			.explode(
				this,
				null,
				EXPLOSION_DAMAGE_CALCULATOR,
				pos.x(),
				pos.y(),
				pos.z(),
				0.5f,
				false,
				Level.ExplosionInteraction.TRIGGER,
				ParticleTypes.GUST_EMITTER_SMALL,
				ParticleTypes.GUST_EMITTER_LARGE,
				WeightedList.of(),
				ModSounds.CONSUMED_SOUL_HIT
			);
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	protected @Nullable ParticleOptions getTrailParticle() {
		return ParticleTypes.SOUL;
	}
}
