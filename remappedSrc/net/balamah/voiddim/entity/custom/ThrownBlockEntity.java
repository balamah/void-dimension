package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;

public class ThrownBlockEntity extends AbstractWindCharge {
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
	protected void explode(Vec3d pos) {}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);

		if (this.level() instanceof ServerWorld serverWorld) {
			LivingEntity livingEntity2 =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
							livingEntity : null;

			Entity entity = entityHitResult.getEntity();
			if (livingEntity2 != null) livingEntity2.onAttacking(entity);

			DamageSource damageSource = ModDamageSources.thrownBlock(serverWorld);
			float damage = this.blockState.getBlock().getHardness();

			entity.hurtServer(serverWorld, damageSource, damage);
		}
	}
}
