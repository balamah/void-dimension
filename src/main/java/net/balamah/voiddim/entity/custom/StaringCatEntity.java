package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.StaringEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class StaringCatEntity extends StaringEntity {
	public StaringCatEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	public boolean isInSittingPose() {
		return true;
	}
}
