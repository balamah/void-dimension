package net.balamah.voiddim.block.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.world.tick.ScheduledTickView;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Direction;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.world.WorldView;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.block.ModBlocks;

public class CorruptedFireBlock extends AbstractFireBlock {
	public static final MapCodec<CorruptedFireBlock> CODEC = createCodec(CorruptedFireBlock::new);

	@Override
	public MapCodec<CorruptedFireBlock> getCodec() {
		return CODEC;
	}

	public CorruptedFireBlock(AbstractBlock.Settings settings) {
		super(settings, 18f);
	}

	@Override
	protected BlockState getStateForNeighborUpdate(
		BlockState state,
		WorldView world,
		ScheduledTickView tickView,
		BlockPos pos,
		Direction direction,
		BlockPos neighborPos,
		BlockState neighborState,
		Random random
	)
	{
		return (this.canPlaceAt(state, world, pos)) ?
			this.getDefaultState() : Blocks.AIR.getDefaultState();
	}

	@Override
	protected void onEntityCollision(
		BlockState state, World world, BlockPos pos, Entity entity,
		EntityCollisionHandler handler, boolean bl
	) {
		super.onEntityCollision(state, world, pos, entity, handler, bl);

		if (!world.isClient() && world instanceof ServerWorld serverWorld) {
			entity.extinguish();

			DamageSource damageSource =
				new DamageSource(world.getRegistryManager()
					.getOrThrow(RegistryKeys.DAMAGE_TYPE)
					.getEntry(ModEffects.CORRUPTION_DAMAGE.getValue())
					.orElseThrow());

			entity.damage(serverWorld, damageSource, 7.5F);

            if (entity instanceof LivingEntity living) {
                living.addStatusEffect(new StatusEffectInstance(ModEffects.CORRUPTION, 20, 0));
            }

			entity.setFireTicks(40);
		}
	}

	public static BlockState getState(WorldView world, BlockPos pos) {
		return ModBlocks.CORRUPTED_FIRE.getDefaultState();
	}

	@Override
	protected boolean canPlaceAt(BlockState state, WorldView worldView, BlockPos pos) {
		return !state.isAir();
	}

	@Override
	protected boolean isFlammable(BlockState state) {
		return true;
	}
}
