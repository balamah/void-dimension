package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CorruptedCreeperEntity extends CreeperEntity {
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.MAX_HEALTH, 35)
			.add(EntityAttributes.FOLLOW_RANGE, 40)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.32F)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
	}

	public CorruptedCreeperEntity(
		EntityType<? extends CreeperEntity> entityType, World world
	) {
		super(entityType, world);
	}
}
