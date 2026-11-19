package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.custom.CorruptedEntityKilledTracker;
import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;

public class NearestMurdererPlayerTargetGoal extends NearestPlayerTargetGoal {
	protected final SoundEvent attackSound;
	
	public NearestMurdererPlayerTargetGoal(
		ModHostileEntity entity, double range, SoundEvent attackSound
	) {
		super(entity, range);

		this.attackSound = attackSound;
	}

	@Override
	public boolean canUse() {
		Player player = this.findNearestEntity();
		if (player == null || !(player instanceof ServerPlayer serverPlayer)) {
			return false;
		}

		return CorruptedEntityKilledTracker.hasRecentKill(serverPlayer);
	}

	@Override
	public void start() {
		super.start();

		this.entity.playSound(this.attackSound);
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}
}
