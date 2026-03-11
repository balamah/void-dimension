package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.StaringEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class StaringCatEntity extends StaringEntity {
	public StaringCatEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public boolean isInSittingPose() {
		return true;
	}
}
