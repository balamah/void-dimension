package net.balamah.voiddim.block.custom;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import net.balamah.voiddim.sound.ModSounds;

public class WindManipulationBlock extends Block {
	protected Direction direction = Direction.UP;

	public WindManipulationBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	@Override
	public void onEntityLand(BlockView world, Entity entity) {
		entity.handleFallDamage(entity.fallDistance, 0.0F, entity.getDamageSources().fall());
		entity.fallDistance = 0.0F;
	}

	@Override
	public void randomDisplayTick(
		BlockState state, World world, BlockPos pos, Random random
	) {
		if (!(world instanceof ClientWorld clientWorld)) return;

		double x = pos.getX() + 0.55 - random.nextFloat() * 0.1F;
		double y = pos.getY() + 1.55 - random.nextFloat() * 0.1F;
		double z = pos.getZ() + 0.55 - random.nextFloat() * 0.1F;
		double g = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;

		if (random.nextInt(3) == 0) {
			world.addParticleClient(
				ParticleTypes.GUST,
				x + direction.getOffsetX() * g,
				y + direction.getOffsetY() * g,
				z + direction.getOffsetZ() * g,
				random.nextGaussian() * 0.005,
				random.nextGaussian() * 0.005,
				random.nextGaussian() * 0.005
			);

		}
	}

	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (world.isClient() && !(entity instanceof LivingEntity)) return;

		Vec3d velocity = entity.getVelocity();
		entity.setVelocity(velocity.x, 1.5, velocity.z);
		entity.velocityDirty = true;

		playSound(world, entity, ModSounds.WIND_MANIPULATION_JUMP);
	}

	protected void playSound(World world, Entity entity, SoundEvent sound) {
		world.playSound(
			null,
			entity.getX(), entity.getY(), entity.getZ(),
			sound,
			SoundCategory.AMBIENT,
			2.0f,
			1.0f
		);
	}
}
