package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.function.Predicate;
import net.balamah.voiddim.entity.custom.Entity303;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntitySpawnReason;
import net.balamah.voiddim.entity.ModEntities;

@Mixin(FireBlock.class)
public class FireblockMixin {
	private BlockPattern entity303pattern;
	private final Predicate<BlockState> entity303headPredicate = state -> state.isOf(Blocks.FIRE);

	@Inject(method = "onBlockAdded", at = @At("HEAD"))
	protected void onBlockAdded(
		BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify,
		CallbackInfo ci
	) {
		if (!oldState.isOf(state.getBlock())) {
			this.trySpawnEntity(world, pos);
		}
	}

	protected void trySpawnEntity(World world, BlockPos blockPos) {
		BlockPattern.Result entity303pattern = this.getEntity303Pattern().searchAround(
			world, blockPos
		);

		if (entity303pattern != null) {
			Entity303 entity303 = ModEntities.ENTITY303.create(world, EntitySpawnReason.TRIGGERED);
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
				.where('!', CachedBlockPosition.matchesBlockState(AbstractBlock.AbstractBlockState::isAir))
				.where('M', CachedBlockPosition.matchesBlockState(this.entity303headPredicate))
				.where('1', this.getBlockPredicate(Blocks.NETHERRACK))
				.where('2', this.getBlockPredicate(Blocks.OBSIDIAN))
				.where('3', this.getBlockPredicate(Blocks.IRON_BLOCK))
				.build();
		}

		return this.entity303pattern;
	}

	protected void spawnEntity(
		World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos
	) {
		Vec3d currentVectorPosition = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
		if (this.isBossSpawned(world, currentVectorPosition, 30)) {
			return;
		}

		entity.snapTo(
			pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F
		);

		world.setBlockState(pos, Blocks.AIR.getDefaultState());
		world.spawnEntity(entity);
		world.playSound(
			null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_WITHER_SPAWN,
			SoundCategory.AMBIENT
		);

		List<ServerPlayerEntity> entities = world.getNonSpectatingEntities(
				ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0));

		for (ServerPlayerEntity serverPlayerEntity : entities) {
			Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
		}
	}

	protected Predicate<CachedBlockPosition> getBlockPredicate(Block block) {
		return CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(block));
	}

    protected boolean isBossSpawned(World world, Vec3d position, double radius) {
        Box box = new Box(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

		List<Entity303> mobs = world.getEntitiesByClass(
			Entity303.class, box, entity -> entity.isAlive()
		);

        return !mobs.isEmpty();
    }
}
