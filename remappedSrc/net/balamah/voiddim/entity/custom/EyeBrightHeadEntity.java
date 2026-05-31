package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.explosion.Explosion;
import net.balamah.voiddim.effect.ModEffects;

public class EyeBrightHeadEntity extends AbstractHurtingProjectile {
	public EyeBrightHeadEntity(
		EntityType<? extends EyeBrightHeadEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public EyeBrightHeadEntity(World world, LivingEntity owner, Vec3d velocity) {
		super(ModEntities.EYE_BRIGHT_HEAD, owner, velocity, world);
	}

	@Override
	public float getBlockExplosionResistance(
		Explosion explosion, BlockView world, BlockPos pos,
		BlockState blockState, FluidState fluidState, float max
	) {
		return this.canDestroy(blockState) ? Math.min(0.8F, max) : max;
	}

	protected boolean canDestroy(BlockState block) {
		return !block.isAir() && !block.isIn(BlockTags.WITHER_IMMUNE);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (!(this.level() instanceof ServerWorld serverWorld)) {
			return;
		}

		Entity entityHit = entityHitResult.getEntity();
		boolean hitResult;
		if (this.getOwner() instanceof LivingEntity livingEntity) {
			DamageSource damageSource = ModDamageSources.eyeBrightHead(serverWorld);
			// TODO: Increase damage
			hitResult = entityHit.hurtServer(serverWorld, damageSource, 8.0F);
			if (hitResult) {
				if (entityHit.isAlive()) {
					EnchantmentHelper.onTargetDamaged(serverWorld, entityHit, damageSource);
				} else {
					livingEntity.heal(5.0F);
				}
			}
		} else {
			hitResult = entityHit.hurtServer(serverWorld, this.damageSources().magic(), 5.0F);
		}

		if (hitResult && entityHit instanceof LivingEntity livingEntityx) {
			livingEntityx.addStatusEffect(
				new StatusEffectInstance(ModEffects.CORRUPTION, 20, 1), this.getEffectSource()
			);
		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {
			this.level().explode(
				this, this.getX(), this.getY(), this.getZ(),
				1.0F, false, World.ExplosionSourceType.MOB
			);

			this.discard();
		}
	}
}
