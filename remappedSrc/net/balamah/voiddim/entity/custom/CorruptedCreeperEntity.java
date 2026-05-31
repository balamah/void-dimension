package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class CorruptedCreeperEntity extends CreeperEntity {
	public static DefaultAttributeContainer.Builder createCreeperAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 25)
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.32F)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0);
	}

	public CorruptedCreeperEntity(
		EntityType<? extends CreeperEntity> entityType, World world
	) {
		super(entityType, world);
	}
}
