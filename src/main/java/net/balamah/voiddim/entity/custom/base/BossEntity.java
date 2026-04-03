package net.balamah.voiddim.entity.custom.base;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.custom.McCodeHelper;

public class BossEntity extends CorruptedHostileEntity {
	protected final ServerBossBar bossBar =
		McCodeHelper.getBossBar(this.getDisplayName(), BossBar.Color.WHITE);

	public BossEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);

		this.bossBar.addPlayer(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);

		this.bossBar.removePlayer(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);

		this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
	}
}
