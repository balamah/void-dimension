package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.function.Predicate;
import java.util.Comparator;
import java.util.List;
import net.balamah.voiddim.interfaces.MinecraftEntityDongle;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class FindPassiveEntitiesGoal<T extends Mob & MinecraftEntityDongle>
	extends Goal
{
	protected final T entity;

	protected final TargetingConditions predicate = TargetingConditions.forCombat().range(64.0);
	protected int delay = reducedTickDelay(20);

	public FindPassiveEntitiesGoal(T entity) {
		this.entity = entity;
	}

	@Override
	public boolean canUse() {
		if (this.delay > 0) {
			this.delay--;
			return false;
		} else {
			this.delay = reducedTickDelay(60);
			if (!(this.entity.level() instanceof ServerLevel serverWorld)) {
				return false;
			}

			Predicate<AgeableMob> predicate =
				target -> this.entity.testPredicate(serverWorld, target, this.predicate);

			List<AgeableMob> list = this.entity.level().getEntitiesOfClass(
				AgeableMob.class,
				this.entity.getBoundingBox().inflate(16.0, 64.0, 16.0),
				predicate
			);

			if (!list.isEmpty()) {
				list.sort(Comparator.<AgeableMob>comparingDouble(e -> e.getY()).reversed());

				for (AgeableMob passiveEntity : list) {
					if (this.entity.testPredicate(serverWorld, passiveEntity, TargetingConditions.DEFAULT)) {
						this.entity.setTarget(passiveEntity);
						return true;
					}
				}
			}

			return false;
		}
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity livingEntity = this.entity.getTarget();
		return livingEntity != null
			&& this.entity.level() instanceof ServerLevel serverWorld
			&& this.entity.testPredicate(serverWorld, livingEntity, TargetingConditions.DEFAULT);
	}
}
