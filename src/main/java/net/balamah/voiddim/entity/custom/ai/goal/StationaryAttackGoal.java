package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.EnumSet;

import net.balamah.voiddim.entity.custom.WormOfCorruptionEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class StationaryAttackGoal extends Goal {
    private final WormOfCorruptionEntity worm;
    private LivingEntity target;
    private int attackCooldown = 0;

    public StationaryAttackGoal(WormOfCorruptionEntity worm) {
        this.worm = worm;
        this.setControls(EnumSet.of(Control.TARGET, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        this.target = worm.getTarget();
        return target != null && target.isAlive() && worm.squaredDistanceTo(target) <= 4.0;
    }

    @Override
    public void tick() {
        if (attackCooldown > 0) attackCooldown--;
        if (attackCooldown == 0 && target != null && target.isAlive()) {
            worm.tryAttack((ServerWorld) worm.getEntityWorld(), target);
            attackCooldown = 20;
        }
    }
}
