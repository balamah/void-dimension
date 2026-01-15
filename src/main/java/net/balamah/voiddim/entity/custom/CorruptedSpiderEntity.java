package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.balamah.voiddim.custom.McCodeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CorruptedSpiderEntity extends SpiderEntity {
	public CorruptedSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.MAX_HEALTH, 35)
			.add(EntityAttributes.ATTACK_DAMAGE, 12)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.39F);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);

		if (hit &&
			target instanceof PlayerEntity playerEntity &&
			this.getEntityWorld() instanceof ServerWorld serverWorld
		) {
			McCodeHelper.disableShield(playerEntity);
		}

		return hit;
	}
}
