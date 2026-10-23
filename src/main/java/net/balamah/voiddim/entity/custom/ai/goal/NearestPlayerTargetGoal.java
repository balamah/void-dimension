package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.minecraft.world.entity.player.Player;

public class NearestPlayerTargetGoal extends ModNearestEntityTargetGoal<Player> {
	public NearestPlayerTargetGoal(ModHostileEntity entity, double range) {
		super(entity, range, Player.class);
	}

	@Override
	protected Player findNearestEntity() {
		return this.entity.level().getNearestPlayer(
            this.entity.getX(),
            this.entity.getY(),
            this.entity.getZ(),
            this.range,
            player -> player.isAlive() && !player.isSpectator()
        );
	}
}
