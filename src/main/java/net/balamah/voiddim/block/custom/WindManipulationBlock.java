package net.balamah.voiddim.block.custom;

import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class WindManipulationBlock extends Block {

    protected Direction direction = Direction.UP;

    public WindManipulationBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter level, Entity entity) {
        entity.causeFallDamage(entity.fallDistance, 0.0F, entity.damageSources().fall());
        entity.fallDistance = 0.0F;
    }

    @Override
    public void animateTick(
        BlockState state,
        Level level,
        BlockPos pos,
        RandomSource random
    ) {
        double x = pos.getX() + 0.55 - random.nextFloat() * 0.1F;
        double y = pos.getY() + 1.55 - random.nextFloat() * 0.1F;
        double z = pos.getZ() + 0.55 - random.nextFloat() * 0.1F;
        double g = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;

        if (random.nextInt(3) == 0) {
            level.addParticle(
                ParticleTypes.GUST,
                x + direction.getStepX() * g,
                y + direction.getStepY() * g,
                z + direction.getStepZ() * g,
                random.nextGaussian() * 0.005,
                random.nextGaussian() * 0.005,
                random.nextGaussian() * 0.005
            );
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (level.isClientSide() && !(entity instanceof LivingEntity)) return;

        Vec3 velocity = entity.getDeltaMovement();
        entity.setDeltaMovement(velocity.x, 1.5, velocity.z);
        entity.hasImpulse = true;

        playSound(level, entity, ModSounds.WIND_MANIPULATION_JUMP);
    }

    protected void playSound(Level level, Entity entity, SoundEvent sound) {
        level.playSound(
            null,
            entity.getX(),
            entity.getY(),
            entity.getZ(),
            sound,
            SoundSource.AMBIENT,
            2.0f,
            1.0f
        );
    }
}
