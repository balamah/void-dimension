package net.balamah.voiddim.block.custom;

import com.mojang.serialization.MapCodec;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.effect.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;

public class CorruptedFireBlock extends AbstractFireBlock {

    public static final MapCodec<CorruptedFireBlock> CODEC =
        createCodec(CorruptedFireBlock::new);

    @Override
    public MapCodec<CorruptedFireBlock> codec() {
        return CODEC;
    }

    public CorruptedFireBlock(Properties properties) {
        super(properties, 18.0F);
    }

    @Override
    protected BlockState updateShape(
        BlockState state,
        Level level,
        BlockPos pos,
        Direction direction,
        BlockPos neighborPos,
        BlockState neighborState,
        RandomSource random
    ) {
        return this.canSurvive(state, level, pos)
            ? this.defaultBlockState()
            : net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
    }

    @Override
    public void entityInside(
        BlockState state,
        Level level,
        BlockPos pos,
        Entity entity
    ) {
        if (!level.isClientSide()) {

            entity.clearFire();

            DamageSource damageSource =
                new DamageSource(
                    level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModEffects.CORRUPTION_DAMAGE)
                );

            entity.hurtServer((ServerLevel) level, damageSource, 7.5F);

            if (entity instanceof LivingEntity living) {
                living.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    ModEffects.CORRUPTION,
                    20,
                    0
                ));
            }

            entity.setRemainingFireTicks(40);
        }
    }

    public static BlockState getState(BlockGetter level, BlockPos pos) {
        return ModBlocks.CORRUPTED_FIRE.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !state.isAir();
    }

    @Override
    protected boolean isFlammable(BlockState state) {
        return true;
    }
}
