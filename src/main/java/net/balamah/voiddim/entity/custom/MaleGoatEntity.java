package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.item.ModItems;

import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;

public class MaleGoatEntity extends Goat {
	public MaleGoatEntity(EntityType<? extends Goat> type, Level level) {
		super(type, level);
	}

	@Override
	public InteractionResult mobInteract(final Player player, final InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		if (heldItem.is(Items.BUCKET) && !this.isBaby()) {
			player.playSound(this.getMilkingSound(), 1.0F, 1.0F);
			ItemStack bucketOrMilkBucket = ItemUtils.createFilledResult(
				heldItem, player, ModItems.MALE_GOAT_MILK_BUCKET.getDefaultInstance()
			);

			player.setItemInHand(hand, bucketOrMilkBucket);

			return InteractionResult.SUCCESS;
		}

		InteractionResult interactionResult = super.mobInteract(player, hand);
		if (interactionResult.consumesAction() && this.isFood(heldItem)) {
			this.playEatingSound();
		}

		return interactionResult;
	}
}
