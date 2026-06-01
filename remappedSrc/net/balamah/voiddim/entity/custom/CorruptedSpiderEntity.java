package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.custom.McCodeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.entity.monster.spider.Spider;

public class CorruptedSpiderEntity extends Spider {
	public CorruptedSpiderEntity(EntityType<? extends Spider> entityType, World world) {
		super(entityType, world);
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 35)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.39F);
	}

	@Override
	public boolean doHurtTarget(ServerWorld world, Entity target) {
		boolean hit = super.doHurtTarget(world, target);

		if (hit &&
			target instanceof PlayerEntity playerEntity &&
			this.level() instanceof ServerWorld serverWorld
		) {
			McCodeHelper.disableShield(playerEntity);
		}

		return hit;
	}
}
