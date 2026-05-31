package net.balamah.voiddim.entity.custom.base;

import net.balamah.voiddim.custom.McCodeHelper;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BossEntity extends CorruptedHostileEntity {
	protected final ServerBossEvent bossBar =
		McCodeHelper.getBossBar(this.getDisplayName(), BossEvent.BossBarColor.WHITE);

	public BossEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		this.bossBar.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		this.bossBar.removePlayer(player);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
	}
}
