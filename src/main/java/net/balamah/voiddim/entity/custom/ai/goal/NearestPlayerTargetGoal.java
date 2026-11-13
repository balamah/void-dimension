package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

import java.util.List;

public class NearestPlayerTargetGoal extends ModNearestEntityTargetGoal<Player> {
	protected List<GameType> ignoredGametypes = List.of(
		GameType.SPECTATOR, GameType.CREATIVE
	);
	
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
            player -> player.isAlive() && player instanceof ServerPlayer serverPlayer &&
				!this.ignoredGametypes.contains(serverPlayer.gameMode.getGameModeForPlayer())
        );
	}
}
