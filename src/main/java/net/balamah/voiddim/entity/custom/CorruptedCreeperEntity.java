package net.balamah.voiddim.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class CorruptedCreeperEntity extends Creeper {
	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 25)
			.add(Attributes.FOLLOW_RANGE, 25)
			.add(Attributes.MOVEMENT_SPEED, 0.32F)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	public CorruptedCreeperEntity(
		EntityType<? extends Creeper> entityType, Level world
	) {
		super(entityType, world);
	}
}
