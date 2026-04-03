package net.balamah.voiddim.interfaces;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;

public interface MinecraftEntityDongle {
	public boolean testPredicate(
		ServerWorld world, LivingEntity target, TargetPredicate predicate
	);
}
