package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Comparator;
import java.util.EnumSet;

import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.LivingEntity;

public class ModNearestEntityTargetGoal<E extends LivingEntity> extends Goal {
    protected final ModHostileEntity entity;
    protected final double range;
    protected Class<E> targetClass;
    protected E target;

	public ModNearestEntityTargetGoal(
		ModHostileEntity entity, double range, Class<E> targetClass
	) {
        this.entity = entity;
		this.targetClass = targetClass;
        this.range = range;

        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        return this.findNearestEntity() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null
            && this.target.isAlive()
            && !this.target.isSpectator()
            && this.entity.distanceToSqr(this.target) <= this.range * this.range;
    }

    @Override
    public void start() {
        this.entity.setTarget(this.target);
    }

    @Override
    public void tick() {
        this.target = this.findNearestEntity();

        if (this.target != null && this.entity.getTarget() != this.target) {
            this.entity.setTarget(this.target);
        }
    }

    protected E findNearestEntity() {
		return this.entity.level()
			.getEntitiesOfClass(
				this.targetClass,
				this.entity.getBoundingBox().inflate(this.range),
				entity -> entity != this.entity && entity.isAlive()
			)
			.stream()
			.min(Comparator.comparingDouble(entity -> this.entity.distanceToSqr(entity)))
			.orElse(null);
    }
}
