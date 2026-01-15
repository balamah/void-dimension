package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;

public class ThrownBlockEntity extends AbstractWindChargeEntity {
	protected final BlockState blockState;

	public ThrownBlockEntity(
		ShatteredSentinelMasterEntity entity, World world, BlockState blockState
	) {
		super(
			ModEntities.THROWN_BLOCK, world,
			entity, entity.getX(), entity.getChargeY(), entity.getZ()
		);

		this.blockState = blockState;
	}

	public ThrownBlockEntity(EntityType<? extends ThrownBlockEntity> entityType, World world) {
		super(entityType, world);

		this.blockState = Blocks.STONE.getDefaultState();
	}

	@Override
	protected void createExplosion(Vec3d pos) {}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);

		if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
			LivingEntity livingEntity2 =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
							livingEntity : null;

			Entity entity = entityHitResult.getEntity();
			if (livingEntity2 != null) livingEntity2.onAttacking(entity);

			DamageSource damageSource = ModDamageSources.thrownBlock(serverWorld);
			float damage = this.blockState.getBlock().getHardness();

			entity.damage(serverWorld, damageSource, damage);
		}
	}
}
