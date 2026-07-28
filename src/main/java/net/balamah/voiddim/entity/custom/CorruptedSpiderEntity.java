package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.custom.McCodeHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CorruptedSpiderEntity extends Spider {
	public CorruptedSpiderEntity(EntityType<? extends Spider> entityType, Level world) {
		super(entityType, world);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 35)
			.add(Attributes.ATTACK_DAMAGE, 12)
			.add(Attributes.MOVEMENT_SPEED, 0.39F);
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean hit = super.doHurtTarget(world, target);

		if (hit &&
			target instanceof Player playerEntity &&
			this.level() instanceof ServerLevel serverWorld
		) {
			McCodeHelper.disableShield(playerEntity);
		}

		return hit;
	}
}
