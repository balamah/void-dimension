package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

// TODO: Implement entity MemorySeekerEntity
public class MemorySeekerEntity extends CorruptedHostileEntity {
	public MemorySeekerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
