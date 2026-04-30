package net.balamah.voiddim.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class ConsumedSoulEntity extends Projectile {
	public ConsumedSoulEntity(EntityType<? extends ConsumedSoulEntity> entityType, Level level) {
		super(entityType, level);
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
