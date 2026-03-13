package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;

public class HerobrineEntity extends BossEntity {
	public HerobrineEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 32)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.2F)
			.add(EntityAttributes.ATTACK_DAMAGE, 13.0F)
			.add(EntityAttributes.STEP_HEIGHT, 3.0)
			.add(EntityAttributes.MAX_HEALTH, 315);
	}
}
