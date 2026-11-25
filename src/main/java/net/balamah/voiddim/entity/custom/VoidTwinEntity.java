package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.block.custom.WallDimmedTorchBlock;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

// TODO: Add sounds
public class VoidTwinEntity extends CorruptedHostileEntity {
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();
	public final AnimationState attack3AnimationState = new AnimationState();

	public VoidTwinEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void tick() {
		super.tick();

		for (BlockPos pos : this.getTorches(5, 5, 5)) {
			BlockState state = this.level().getBlockState(pos);
			BlockState futureBlockState = this.getDimmedTorchState(state);

			if (futureBlockState != null) {
				this.level().setBlockAndUpdate(pos, futureBlockState);
				this.playSound(SoundEvents.FIRE_EXTINGUISH);
			}
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.2F)
			.add(Attributes.ATTACK_DAMAGE, 9.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 35);
	}

	protected Iterable<BlockPos> getTorches(int x, int y, int z) {
		BlockPos entityPos = this.blockPosition();

		return BlockPos.betweenClosed(entityPos.offset(-x, -y, -z), entityPos.offset(x, y, z));
	}

	@Nullable
	protected BlockState getDimmedTorchState(BlockState currentState) {
		if (currentState == Blocks.TORCH.defaultBlockState()) {
			return ModBlocks.DIMMED_TORCH.defaultBlockState();
		} else if (currentState.getBlock() == Blocks.WALL_TORCH) {
			return ModBlocks.DIMMED_WALL_TORCH.defaultBlockState().setValue(
				WallDimmedTorchBlock.FACING, currentState.getValue(WallTorchBlock.FACING)
			);
		}

		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.VOID_TWIN_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.VOID_TWIN_DAMAGE;
	}
}
