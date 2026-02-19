package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class HollowedAlphaSteveEntity extends PathAwareEntity {
	public HollowedAlphaSteveEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return PathAwareEntity.createMobAttributes()
			.add(EntityAttributes.MAX_HEALTH, 35)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.4F);
	}
}
