package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

// TODO: Implement entity GhostEntity
public class GhostEntity extends CorruptedHostileEntity {
    public final AnimationState walkAnimationState = new AnimationState();

	public GhostEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
