package net.balamah.voiddim.block.custom;

import com.mojang.serialization.MapCodec;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptedFireBlock extends BaseFireBlock {

    public static final MapCodec<CorruptedFireBlock> CODEC =
        simpleCodec(CorruptedFireBlock::new);

    @Override
    public MapCodec<CorruptedFireBlock> codec() {
        return CODEC;
    }

    public CorruptedFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 18.0f);
    }

    @Override
    protected BlockState updateShape(
        BlockState state,
        Direction direction,
        BlockState neighborState,
        net.minecraft.world.level.LevelAccessor level,
        BlockPos pos,
        BlockPos neighborPos
    ) {
        return this.canSurvive(state, level, pos)
            ? this.defaultBlockState()
            : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected void entityInside(
        BlockState state,
        Level level,
        BlockPos pos,
        Entity entity
    ) {
        super.entityInside(state, level, pos, entity);

        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {

            entity.clearFire();

            DamageSource damageSource = new DamageSource(
                level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModEffects.CORRUPTION_DAMAGE)
            );

            entity.hurt(damageSource, 7.5F);

            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(ModEffects.CORRUPTION, 20, 0));
            }

            entity.setRemainingFireTicks(40);
        }
    }

    public static BlockState getState(LevelReader level, BlockPos pos) {
        return ModBlocks.CORRUPTED_FIRE.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !state.isAir();
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }
}
