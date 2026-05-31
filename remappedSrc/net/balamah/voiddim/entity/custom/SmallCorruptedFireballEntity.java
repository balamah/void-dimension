package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.block.custom.CorruptedFireBlock;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;

public class SmallCorruptedFireballEntity extends Fireball {
	public SmallCorruptedFireballEntity(
		EntityType<? extends SmallCorruptedFireballEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public SmallCorruptedFireballEntity(
		World world, LivingEntity owner, Vec3d velocity
	) {
		super(ModEntities.SMALL_CORRUPTED_FIREBALL, owner, velocity, world);
	}

	public SmallCorruptedFireballEntity(
		World world, double x, double y, double z, Vec3d velocity
	) {
		super(ModEntities.SMALL_CORRUPTED_FIREBALL, x, y, z, velocity, world);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		if (this.level() instanceof ServerWorld serverWorld) {
			Entity target = entityHitResult.getEntity();
			Entity owner = this.getOwner();
			int fireTicks = target.getFireTicks();
			target.setOnFireFor(5.0F);
			DamageSource damageSource = this.damageSources().fireball(this, target);
			if (!target.hurtServer(serverWorld, damageSource, 5.0F)) {
				target.setFireTicks(fireTicks);
			} else {
				this.damageEntity(serverWorld, damageSource, target);
			}
		}
	}

	protected void damageEntity(
		ServerWorld serverWorld, DamageSource damageSource, Entity entity
	) {
		EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
		if (entity instanceof LivingEntity livingEntity) {
			livingEntity.addStatusEffect(
				new StatusEffectInstance(ModEffects.CORRUPTION, 60, 1)
			);
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);

		World world = this.level();

		if (world instanceof ServerWorld serverWorld) {
			Entity entity = this.getOwner();
			if (!(entity instanceof MobEntity)) {
				BlockPos blockPos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
				if (world.isAir(blockPos)) {
					world.setBlockState(blockPos, CorruptedFireBlock.getState(world, blockPos));
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
