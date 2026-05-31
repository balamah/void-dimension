package net.balamah.voiddim.entity.custom;

import net.minecraft.world.World;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.WeightedList;
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
		ModEntities.DARK_GRASP, ModEntities.VOID_BOUND_SERVANT, ModEntities.CORRUPTED_WARRIOR
	};

	public static final ExplosionBehavior EXPLOSION_DAMAGE_CALCULATOR =
		new AdvancedExplosionBehavior(
			false, false, Optional.empty(),
			Registries.BLOCK.get(BlockTags.AIR).map(Function.identity())
		);

	public ConsumedSoulEntity(EntityType<? extends ConsumedSoulEntity> entityType, World level) {
		super(entityType, level);
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		super.onHitEntity(hitResult);

		if (this.level() instanceof ServerWorld world) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = hitResult.getEntity();

			if (Arrays.asList(this.immuneEntities).contains(target.getType())) {
				return;
			}

			if (attackerEntity != null) {
				attackerEntity.onAttacking(target);
			}

			DamageSource damageSource = ModDamageSources.corruption(world);
			boolean damage = target.hurtServer(world, damageSource, 18.5f);

			if (damage && target instanceof LivingEntity targetLivingEntity) {
				targetLivingEntity.addStatusEffect(
					new StatusEffectInstance(ModEffects.SOUL_BURN, 100, 1)
				);
				
				EnchantmentHelper.onTargetDamaged(world, targetLivingEntity, damageSource);
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

	protected void explode(Vec3d pos) {
		this.level()
			.explode(
				this,
				null,
				EXPLOSION_DAMAGE_CALCULATOR,
				pos.getX(),
				pos.getY(),
				pos.getZ(),
				0.5f,
				false,
				World.ExplosionSourceType.TRIGGER,
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
	protected @Nullable ParticleEffect getTrailParticle() {
		return ParticleTypes.SOUL;
	}
}
