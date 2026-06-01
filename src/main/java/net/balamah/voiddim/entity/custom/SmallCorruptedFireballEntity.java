package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.block.custom.CorruptedFireBlock;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SmallCorruptedFireballEntity extends Fireball {
	public SmallCorruptedFireballEntity(
		EntityType<? extends SmallCorruptedFireballEntity> entityType, Level world
	) {
		super(entityType, world);
	}

	public SmallCorruptedFireballEntity(
		Level world, LivingEntity owner, Vec3 velocity
	) {
		super(ModEntities.SMALL_CORRUPTED_FIREBALL, owner, velocity, world);
	}

	public SmallCorruptedFireballEntity(
		Level world, double x, double y, double z, Vec3 velocity
	) {
		super(ModEntities.SMALL_CORRUPTED_FIREBALL, x, y, z, velocity, world);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		if (this.level() instanceof ServerLevel serverWorld) {
			Entity target = entityHitResult.getEntity();
			Entity owner = this.getOwner();
			int fireTicks = target.getRemainingFireTicks();
			target.igniteForSeconds(5.0F);
			DamageSource damageSource = this.damageSources().fireball(this, target);
			if (!target.hurt(damageSource, 5.0F)) {
				target.setRemainingFireTicks(fireTicks);
			} else {
				this.damageEntity(serverWorld, damageSource, target);
			}
		}
	}

	protected void damageEntity(
		ServerLevel serverWorld, DamageSource damageSource, Entity entity
	) {
		EnchantmentHelper.doPostAttackEffects(serverWorld, entity, damageSource);
		if (entity instanceof LivingEntity livingEntity) {
			livingEntity.addEffect(
				new MobEffectInstance(ModEffects.CORRUPTION, 60, 1)
			);
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);

		Level world = this.level();

		if (world instanceof ServerLevel serverWorld) {
			Entity entity = this.getOwner();
			if (!(entity instanceof Mob)) {
				BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
				if (world.isEmptyBlock(blockPos)) {
					world.setBlockAndUpdate(blockPos, CorruptedFireBlock.getState(world, blockPos));
				}
			}
		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {
			this.discard();
		}
	}

	private ItemStack getDefaultItem() {
		return new ItemStack(ModItems.CORRUPTED_FIRE_CHARGE);
	}
}
