package net.balamah.voiddim.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import net.balamah.voiddim.entity.custom.base.ModProjectile;
import net.balamah.voiddim.entity.ModEntities;

public class ConsumedSoulEntity extends ModProjectile {
	protected EntityType<?>[] immuneEntities = {
		ModEntities.DARK_GRASP, ModEntities.VOID_BOUND_SERVANT, ModEntities.CORRUPTED_WARRIOR
	};

	public ConsumedSoulEntity(
		EntityType<? extends ConsumedSoulEntity> entityType, Level level
	) {
		super(entityType, level);
	}
}
