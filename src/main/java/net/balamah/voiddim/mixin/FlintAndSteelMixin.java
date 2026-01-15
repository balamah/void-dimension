package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import net.balamah.voiddim.tag.ModBlockTags;
import net.balamah.voiddim.block.ModBlocks;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void injectWorld(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos().offset(ctx.getSide());

        if (world.getBlockState(pos.down()).isIn(ModBlockTags.CORRUPTED_FIRE_BASE_BLOCKS)) {
            world.setBlockState(pos, ModBlocks.CORRUPTED_FIRE.getDefaultState());
			world.playSound(
				null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE,
				SoundCategory.BLOCKS, 1.0F, 1.0F
			);

            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
