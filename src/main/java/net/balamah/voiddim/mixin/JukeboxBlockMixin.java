package net.balamah.voiddim.mixin;

import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JukeboxBlock.class)
public class JukeboxBlockMixin {
	@Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
	private void playCalm4Fallback(
		ItemStack itemStack,
		BlockState state,
		Level level,
		BlockPos pos,
		Player player,
		InteractionHand hand,
		BlockHitResult hitResult,
		CallbackInfoReturnable<InteractionResult> cir
	) {
		if (
			state.getValue(JukeboxBlock.HAS_RECORD)
				|| !itemStack.is(ModItems.MUSIC_DISC_CALM4)
				|| itemStack.has(DataComponents.JUKEBOX_PLAYABLE)
		) {
			return;
		}

		if (level.isClientSide()) {
			level.playLocalSound(pos, ModSounds.MUSIC_CALM4, SoundSource.RECORDS, 4.0F, 1.0F, false);
			cir.setReturnValue(InteractionResult.SUCCESS);
		}
	}
}
