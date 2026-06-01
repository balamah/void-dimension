package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.function.Predicate;
import java.util.Comparator;
import java.util.List;
import net.balamah.voiddim.interfaces.MinecraftEntityDongle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;

public class FindPassiveEntitiesGoal<T extends MobEntity & MinecraftEntityDongle>
	extends Goal
{
	protected final T entity;

	protected final TargetPredicate predicate = TargetPredicate.createAttackable().setBaseMaxDistance(64.0);
	protected int delay = toGoalTicks(20);

	public FindPassiveEntitiesGoal(T entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		if (this.delay > 0) {
			this.delay--;
			return false;
		} else {
			this.delay = toGoalTicks(60);
			ServerWorld serverWorld = getServerLevel(this.entity.getWorld());

			Predicate<PassiveEntity> predicate =
				target -> this.entity.testPredicate(serverWorld, target, this.predicate);

			List<PassiveEntity> list = this.entity.getWorld().getEntitiesByClass(
				PassiveEntity.class,
				this.entity.getBoundingBox().expand(16.0, 64.0, 16.0),
				predicate
			);

			if (!list.isEmpty()) {
				list.sort(Comparator.<PassiveEntity>comparingDouble(e -> e.getY()).reversed());

				for (PassiveEntity passiveEntity : list) {
					if (this.entity.testPredicate(serverWorld, passiveEntity, TargetPredicate.DEFAULT)) {
						this.entity.setTarget(passiveEntity);
						return true;
					}
				}
			}

			return false;
		}
	}

	@Override
	public boolean shouldContinue() {
		LivingEntity livingEntity = this.entity.getTarget();
		return livingEntity != null
			? this.entity.testPredicate(getServerLevel(this.entity.getWorld()), livingEntity, TargetPredicate.DEFAULT)
			: false;
	}
}
