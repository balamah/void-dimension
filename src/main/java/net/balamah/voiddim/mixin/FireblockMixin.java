package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.function.Predicate;
import net.balamah.voiddim.entity.custom.Entity303;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.balamah.voiddim.entity.ModEntities;

@Mixin(FireBlock.class)
public class FireblockMixin {
	private BlockPattern entity303pattern;
	private final Predicate<BlockState> entity303headPredicate = state -> state.is(Blocks.FIRE);

	@Inject(method = "onPlace", at = @At("HEAD"))
	protected void onBlockAdded(
		BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify,
		CallbackInfo ci
	) {
		if (!oldState.is(state.getBlock())) {
			this.trySpawnEntity(world, pos);
		}
	}

	protected void trySpawnEntity(Level world, BlockPos blockPos) {
		BlockPattern.BlockPatternMatch entity303pattern = this.getEntity303Pattern().find(
			world, blockPos
		);

		if (entity303pattern != null) {
			Entity303 entity303 = ModEntities.ENTITY303.create(world);
			if (entity303 != null) {
				this.spawnEntity(world, entity303pattern, entity303, blockPos);
			}
		}
	}

	protected BlockPattern getEntity303Pattern() {
		if (this.entity303pattern == null) {
			this.entity303pattern = BlockPatternBuilder.start()
				.aisle("!!!", "!M!", "!!!") 
				.aisle("!!!", "!1!", "!!!") 
				.aisle("!2!", "232", "!2!")
				.where('!', BlockInWorld.hasState(BlockBehaviour.BlockStateBase::isAir))
				.where('M', BlockInWorld.hasState(this.entity303headPredicate))
				.where('1', this.getBlockPredicate(Blocks.NETHERRACK))
				.where('2', this.getBlockPredicate(Blocks.OBSIDIAN))
				.where('3', this.getBlockPredicate(Blocks.IRON_BLOCK))
				.build();
		}

		return this.entity303pattern;
	}

	protected void spawnEntity(
		Level world, BlockPattern.BlockPatternMatch patternResult, Entity entity, BlockPos pos
	) {
		Vec3 currentVectorPosition = new Vec3(pos.getX(), pos.getY(), pos.getZ());
		if (this.isBossSpawned(world, currentVectorPosition, 30)) {
			return;
		}

		entity.moveTo(
			pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F
		);

		world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		world.addFreshEntity(entity);
		world.playSound(
			null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.WITHER_SPAWN,
			SoundSource.AMBIENT
		);

		List<ServerPlayer> entities = world.getEntitiesOfClass(
				ServerPlayer.class, entity.getBoundingBox().inflate(5.0));

		for (ServerPlayer serverPlayerEntity : entities) {
			CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
		}
	}

	protected Predicate<BlockInWorld> getBlockPredicate(Block block) {
		return BlockInWorld.hasState(BlockStatePredicate.forBlock(block));
	}

    protected boolean isBossSpawned(Level world, Vec3 position, double radius) {
        AABB box = new AABB(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

		List<Entity303> mobs = world.getEntitiesOfClass(
			Entity303.class, box, entity -> entity.isAlive()
		);

        return !mobs.isEmpty();
    }
}
