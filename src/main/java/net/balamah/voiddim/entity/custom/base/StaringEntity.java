package net.balamah.voiddim.entity.custom.base;

import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class StaringEntity extends SunBurningEntity {
	public StaringEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}
}
