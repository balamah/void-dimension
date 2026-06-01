package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.balamah.voiddim.effect.ModEffects;

public class EyeBrightHeadEntity extends AbstractHurtingProjectile {
	public EyeBrightHeadEntity(
		EntityType<? extends EyeBrightHeadEntity> entityType, Level world
	) {
		super(entityType, world);
	}

	public EyeBrightHeadEntity(Level world, LivingEntity owner, Vec3 velocity) {
		super(ModEntities.EYE_BRIGHT_HEAD, owner, velocity, world);
	}

	@Override
	public float getBlockExplosionResistance(
		Explosion explosion, BlockGetter world, BlockPos pos,
		BlockState blockState, FluidState fluidState, float max
	) {
		return this.canDestroy(blockState) ? Math.min(0.8F, max) : max;
	}

	protected boolean canDestroy(BlockState block) {
		return !block.isAir() && !block.is(BlockTags.WITHER_IMMUNE);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (!(this.level() instanceof ServerLevel serverWorld)) {
			return;
		}

		Entity entityHit = entityHitResult.getEntity();
		boolean hitResult;
		if (this.getOwner() instanceof LivingEntity livingEntity) {
			DamageSource damageSource = ModDamageSources.eyeBrightHead(serverWorld);
			// TODO: Increase damage
			hitResult = entityHit.hurt(damageSource, 8.0F);
			if (hitResult) {
				if (entityHit.isAlive()) {
					EnchantmentHelper.doPostAttackEffects(serverWorld, entityHit, damageSource);
				} else {
					livingEntity.heal(5.0F);
				}
			}
		} else {
			hitResult = entityHit.hurt(this.damageSources().magic(), 5.0F);
		}

		if (hitResult && entityHit instanceof LivingEntity livingEntityx) {
			livingEntityx.addEffect(
				new MobEffectInstance(ModEffects.CORRUPTION, 20, 1), this.getEffectSource()
			);
		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {
			this.level().explode(
				this, this.getX(), this.getY(), this.getZ(),
				1.0F, false, Level.ExplosionInteraction.MOB
			);

			this.discard();
		}
	}
}
