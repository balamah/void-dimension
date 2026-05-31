package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.StaringEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class NullEntity extends StaringEntity {
	public NullEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}
}
