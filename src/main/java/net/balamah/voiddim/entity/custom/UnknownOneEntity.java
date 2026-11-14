package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

// TODO: Implement entity UnkownOneEntity
public class UnknownOneEntity extends CorruptedHostileEntity {
	public UnknownOneEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
