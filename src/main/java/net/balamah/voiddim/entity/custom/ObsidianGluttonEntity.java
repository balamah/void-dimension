package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

// TODO: Implement entity ObsidianGluttonEntity
public class ObsidianGluttonEntity extends CorruptedHostileEntity {
	public ObsidianGluttonEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
