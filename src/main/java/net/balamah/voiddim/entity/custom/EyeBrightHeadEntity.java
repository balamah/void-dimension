package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;

public class EyeBrightHeadEntity extends ExplosiveProjectileEntity {
	public EyeBrightHeadEntity(
		EntityType<? extends EyeBrightHeadEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public EyeBrightHeadEntity(World world, LivingEntity owner, Vec3d velocity) {
		super(ModEntities.EYE_BRIGHT_HEAD, owner, velocity, world);
	}

	@Override
	public float getEffectiveExplosionResistance(
		Explosion explosion, BlockView world, BlockPos pos,
		BlockState blockState, FluidState fluidState, float max
	) {
		return this.canDestroy(blockState) ? Math.min(0.8F, max) : max;
	}

	protected boolean canDestroy(BlockState block) {
		return !block.isAir() && !block.isIn(BlockTags.WITHER_IMMUNE);
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);

		if (!(this.getEntityWorld() instanceof ServerWorld serverWorld)) {
			return;
		}

		Entity entityHit = entityHitResult.getEntity();
		boolean hitResult;
		if (this.getOwner() instanceof LivingEntity livingEntity) {
			DamageSource damageSource = ModDamageSources.eyeBrightHead(serverWorld);
			// TODO: Increase damage
			hitResult = entityHit.damage(serverWorld, damageSource, 8.0F);
			if (hitResult) {
				if (entityHit.isAlive()) {
					EnchantmentHelper.onTargetDamaged(serverWorld, entityHit, damageSource);
				} else {
					livingEntity.heal(5.0F);
				}
			}
		} else {
			hitResult = entityHit.damage(serverWorld, this.getDamageSources().magic(), 5.0F);
		}

		if (hitResult && entityHit instanceof LivingEntity livingEntityx) {
			livingEntityx.addStatusEffect(
				new StatusEffectInstance(ModEffects.CORRUPTION, 20, 1), this.getEffectCause()
			);
		}
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getEntityWorld().isClient()) {
			this.getEntityWorld().createExplosion(
				this, this.getX(), this.getY(), this.getZ(),
				1.0F, false, World.ExplosionSourceType.MOB
			);

			this.discard();
		}
	}
}
