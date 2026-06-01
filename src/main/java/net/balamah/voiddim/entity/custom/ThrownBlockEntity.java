package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownBlockEntity extends AbstractWindCharge {
	protected final BlockState blockState;

	public ThrownBlockEntity(
		ShatteredSentinelMasterEntity entity, Level world, BlockState blockState
	) {
		super(
			ModEntities.THROWN_BLOCK, world,
			entity, entity.getX(), entity.getChargeY(), entity.getZ()
		);

		this.blockState = blockState;
	}

	public ThrownBlockEntity(EntityType<? extends ThrownBlockEntity> entityType, Level world) {
		super(entityType, world);

		this.blockState = Blocks.STONE.defaultBlockState();
	}

	@Override
	protected void explode(Vec3 pos) {}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (this.level() instanceof ServerLevel serverWorld) {
			LivingEntity livingEntity2 =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
							livingEntity : null;

			Entity entity = entityHitResult.getEntity();
			if (livingEntity2 != null) livingEntity2.setLastHurtMob(entity);

			DamageSource damageSource = ModDamageSources.thrownBlock(serverWorld);
			float damage = this.blockState.getBlock().defaultDestroyTime();

			entity.hurt(damageSource, damage);
		}
	}
}
