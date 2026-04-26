package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.tag.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.block.ModBlocks;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void injectWorld(UseOnContext ctx, CallbackInfoReturnable<InteractionResult> cir) {
        Level world = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos().relative(ctx.getClickedFace());

        if (world.getBlockState(pos.below()).is(ModBlockTags.CORRUPTED_FIRE_BASE_BLOCKS)) {
            world.setBlockAndUpdate(pos, ModBlocks.CORRUPTED_FIRE.defaultBlockState());
			world.playSound(
				null, pos, SoundEvents.FLINTANDSTEEL_USE,
				SoundSource.BLOCKS, 1.0F, 1.0F
			);

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
