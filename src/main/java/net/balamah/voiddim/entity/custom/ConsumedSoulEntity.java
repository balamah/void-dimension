package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.effect.ModDamageSources;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ConsumedSoulEntity extends Projectile {
	public ConsumedSoulEntity(EntityType<? extends ConsumedSoulEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		super.onHitEntity(hitResult);

		if (this.level() instanceof ServerLevel world) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = hitResult.getEntity();
			if (attackerEntity != null) attackerEntity.setLastHurtMob(target);

			DamageSource damageSource = ModDamageSources.corruption(world);
			target.hurtServer(world, damageSource, 18.5f);
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		// No synced data for now.
	}

	protected void readAdditionalSaveData(CompoundTag tag) {
		// No persistent data for now.
	}

	protected void addAdditionalSaveData(CompoundTag tag) {
		// No persistent data for now.
	}
}
