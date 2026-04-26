package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.block.custom.CorruptedFireBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * The lightning damage can be found in {@link net.balamah.voiddim.mixin.EntityMixin}
 * inside onStruckByLightning(ServerWorld LightningEntity CallbackInfo) injection
 */
public class VoidLightningEntity extends LightningBolt {
	@SuppressWarnings("unused")
	private int blocksSetOnFire;
	private boolean cosmetic;

	public VoidLightningEntity(EntityType<? extends LightningBolt> entityType, Level world) {
		super(entityType, world);
	}

	// TODO: Spawn corrupt fire
	// Code copied from minecraft's decompiled code, don't be angry at me
	@SuppressWarnings("unused")
	private void spawnFire(int spreadAttempts) {
		if (!this.cosmetic && this.level() instanceof ServerLevel serverWorld) {
			BlockPos var7 = this.blockPosition();
			if (serverWorld.canSpreadFireAround(var7)) {
				BlockState blockState = CorruptedFireBlock.getState(serverWorld, var7);
				if (serverWorld.getBlockState(var7).isAir() && blockState.canSurvive(serverWorld, var7)) {
					serverWorld.setBlockAndUpdate(var7, blockState);
					this.blocksSetOnFire++;
				}

				for (int i = 0; i < spreadAttempts; i++) {
					BlockPos blockPos2 = var7.offset(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
					blockState = CorruptedFireBlock.getState(serverWorld, blockPos2);
					if (serverWorld.getBlockState(blockPos2).isAir() && blockState.canSurvive(serverWorld, blockPos2)) {
						serverWorld.setBlockAndUpdate(blockPos2, blockState);
						this.blocksSetOnFire++;
					}
				}
			}
		}
	}
}
