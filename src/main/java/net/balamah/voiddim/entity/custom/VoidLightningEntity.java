package net.balamah.voiddim.entity.custom;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;

import net.balamah.voiddim.block.custom.CorruptedFireBlock;

/**
 * The lightning damage can be found in {@link net.balamah.voiddim.mixin.EntityMixin}
 * inside onStruckByLightning(ServerWorld LightningEntity CallbackInfo) injection
 */
public class VoidLightningEntity extends LightningEntity {
	@SuppressWarnings("unused")
	private int blocksSetOnFire;
	private boolean cosmetic;

	public VoidLightningEntity(EntityType<? extends LightningEntity> entityType, World world) {
		super(entityType, world);
	}

	// TODO: Spawn corrupt fire
	// Code copied from minecraft's decompiled code, don't be angry at me
	@SuppressWarnings("unused")
	private void spawnFire(int spreadAttempts) {
		if (!this.cosmetic && this.getEntityWorld() instanceof ServerWorld serverWorld) {
			BlockPos var7 = this.getBlockPos();
			if (serverWorld.canFireSpread(var7)) {
				BlockState blockState = CorruptedFireBlock.getState(serverWorld, var7);
				if (serverWorld.getBlockState(var7).isAir() && blockState.canPlaceAt(serverWorld, var7)) {
					serverWorld.setBlockState(var7, blockState);
					this.blocksSetOnFire++;
				}

				for (int i = 0; i < spreadAttempts; i++) {
					BlockPos blockPos2 = var7.add(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
					blockState = CorruptedFireBlock.getState(serverWorld, blockPos2);
					if (serverWorld.getBlockState(blockPos2).isAir() && blockState.canPlaceAt(serverWorld, blockPos2)) {
						serverWorld.setBlockState(blockPos2, blockState);
						this.blocksSetOnFire++;
					}
				}
			}
		}
	}
}
