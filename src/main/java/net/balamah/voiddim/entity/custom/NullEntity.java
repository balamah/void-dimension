package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.StaringEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class NullEntity extends StaringEntity {
	public NullEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}
}
