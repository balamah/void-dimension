package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.effect.ModDamageSources;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ThrownBlockEntity extends Projectile {
	protected final BlockState blockState;

	public ThrownBlockEntity(
		EntityType<? extends ThrownBlockEntity> entityType, Level world, BlockState blockState
	) {
		super(entityType, world);

		this.blockState = blockState;
	}

	public ThrownBlockEntity(EntityType<? extends ThrownBlockEntity> entityType, Level world) {
		super(entityType, world);

		this.blockState = Blocks.STONE.defaultBlockState();
	}

	public BlockState getBlockState() {
		return this.blockState;
	}

	public Identifier getBlockTextureIdentifier() {
		Identifier blockIdentifier = BuiltInRegistries.BLOCK.getKey(this.blockState.getBlock());

		return blockIdentifier.withPrefix("textures/block/").withSuffix(".png");
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (this.level() instanceof ServerLevel serverWorld) {
			LivingEntity livingEntity2 = (this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity entity = entityHitResult.getEntity();
			if (livingEntity2 != null) {
				livingEntity2.setLastHurtMob(entity);
			}

			DamageSource damageSource = ModDamageSources.thrownBlock(serverWorld);
			float damage = this.blockState.getBlock().defaultDestroyTime();
			VoidDimension.LOGGER.info(String.format("Damage of the %s is %s", blockState.toString(), damage));

			entity.hurtServer(serverWorld, damageSource, damage);
		}
	}

	@Override
	protected void defineSynchedData(Builder entityData) {}
}
