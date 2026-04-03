package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.EnumSet;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.StationaryAttackUser;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class StationaryAttackGoal<T extends CorruptedHostileEntity & StationaryAttackUser>
	extends Goal
{
    private final T entity;
    private LivingEntity target;
    private int attackCooldown = 0;

    public StationaryAttackGoal(T entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.TARGET, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        this.target = entity.getTarget();
        return target != null && target.isAlive() && entity.squaredDistanceTo(target) <= 4.0;
    }

    @Override
    public void tick() {
        if (attackCooldown > 0) attackCooldown--;
        if (attackCooldown == 0 && target != null && target.isAlive()) {
            entity.tryAttack((ServerWorld) entity.getEntityWorld(), target);
            attackCooldown = 20;
        }
    }
}
