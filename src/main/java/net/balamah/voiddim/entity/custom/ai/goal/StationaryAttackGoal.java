package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.EnumSet;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.StationaryAttackUser;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class StationaryAttackGoal<T extends CorruptedHostileEntity & StationaryAttackUser>
	extends Goal
{
    private final T entity;
    private LivingEntity target;
    private int attackCooldown = 0;

    public StationaryAttackGoal(T entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Flag.TARGET, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.target = entity.getTarget();
        return target != null && target.isAlive() && entity.distanceToSqr(target) <= 4.0;
    }

    @Override
    public void tick() {
        if (attackCooldown > 0) attackCooldown--;
        if (attackCooldown == 0 && target != null && target.isAlive()) {
            entity.doHurtTarget((ServerLevel) entity.level(), target);
            attackCooldown = 20;
        }
    }
}
