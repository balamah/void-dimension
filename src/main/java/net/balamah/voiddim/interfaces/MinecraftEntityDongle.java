package net.balamah.voiddim.interfaces;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public interface MinecraftEntityDongle {
	public boolean testPredicate(
		ServerLevel world, LivingEntity target, TargetingConditions predicate
	);
}
