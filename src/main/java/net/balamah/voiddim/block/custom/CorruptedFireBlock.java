package net.balamah.voiddim.block.custom;

import com.mojang.serialization.MapCodec;
import net.balamah.voiddim.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.balamah.voiddim.block.ModBlocks;

public class CorruptedFireBlock extends BaseFireBlock {
	public static final MapCodec<CorruptedFireBlock> CODEC = simpleCodec(CorruptedFireBlock::new);

	@Override
	public MapCodec<CorruptedFireBlock> codec() {
		return CODEC;
	}

	public CorruptedFireBlock(BlockBehaviour.Properties settings) {
		super(settings, 18f);
	}

	@Override
	protected BlockState updateShape(
		BlockState state,
		LevelReader world,
		ScheduledTickAccess tickView,
		BlockPos pos,
		Direction direction,
		BlockPos neighborPos,
		BlockState neighborState,
		RandomSource random
	)
	{
		return (this.canSurvive(state, world, pos)) ?
			this.defaultBlockState() : Blocks.AIR.defaultBlockState();
	}

	@Override
	protected void entityInside(
		BlockState state, Level world, BlockPos pos, Entity entity,
		InsideBlockEffectApplier handler, boolean bl
	) {
		super.entityInside(state, world, pos, entity, handler, bl);

		if (!world.isClientSide() && world instanceof ServerLevel serverWorld) {
			entity.clearFire();

			DamageSource damageSource =
				new DamageSource(world.registryAccess()
					.lookupOrThrow(Registries.DAMAGE_TYPE)
					.get(ModEffects.CORRUPTION_DAMAGE.identifier())
					.orElseThrow());

			entity.hurtServer(serverWorld, damageSource, 7.5F);

            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(ModEffects.CORRUPTION, 20, 0));
            }

			entity.setRemainingFireTicks(40);
		}
	}

	public static BlockState getState(LevelReader world, BlockPos pos) {
		return ModBlocks.CORRUPTED_FIRE.defaultBlockState();
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader worldView, BlockPos pos) {
		return !state.isAir();
	}

	@Override
	protected boolean canBurn(BlockState state) {
		return true;
	}
}
