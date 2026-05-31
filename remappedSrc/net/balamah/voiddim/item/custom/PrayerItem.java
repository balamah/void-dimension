package net.balamah.voiddim.item.custom;

import java.util.function.Consumer;
import net.balamah.voiddim.interfaces.VoidPrayerDataAccess;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.balamah.voiddim.effect.ModEffects;

public class PrayerItem extends Item {
	protected int prayerTime;
	protected int buffTime;
	protected int prayersHourMax;

	public PrayerItem(
		net.minecraft.item.Item.Settings settings, int prayerTime, int buffTime, int prayersHourMax
	) {
		super(settings);

		this.prayerTime = prayerTime;
		this.buffTime = buffTime;
		this.prayersHourMax = prayersHourMax;
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		user.setCurrentHand(hand);

		return ActionResult.CONSUME;
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.BLOCK;
	}

	@Override
	public void appendHoverText(
		ItemStack stack, TooltipContext context, TooltipDisplay displayComponent,
		Consumer<Text> textConsumer, TooltipType type
	) {
		int seconds = this.prayerTime / 20;

		MutableText text =
			Text.translatable("item.void-dimension.prayer_item.info.time", seconds)
			.formatted(Formatting.GOLD);

		textConsumer.accept(text);
	}

	@Override
	public int getMaxUseTime(ItemStack stack, LivingEntity user) {
		return this.prayerTime;
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (!(user instanceof PlayerEntity player) || world.isClient()) {
			return stack;
		}

		String message;
		Formatting color;

		NbtCompound data = ((VoidPrayerDataAccess) player).getVoidPrayerData();

		long currentHour = System.currentTimeMillis() / (1000 * 60 * 60);

		int prayCount = data.getInt("PrayerCount").orElse(0);
		long lastHour = data.getLong("LastHour").orElse(0l);

		if (lastHour < currentHour) {
			prayCount = 0;
			data.putInt("PrayerCount", 0);
			data.putLong("LastHour", currentHour);
		}

		if (prayCount < this.prayersHourMax) {
			message = "text.void-dimension.prayer_success";
			color = Formatting.GOLD;

			StatusEffectInstance effect =
				new StatusEffectInstance(ModEffects.DIVINE_PROTECTION, this.buffTime, 1);

			player.addStatusEffect(effect);

			data.putInt("PrayerCount", prayCount + 1);
		} else {
			message = "text.void-dimension.prayer_failure";
			color = Formatting.RED;
		}

			player.sendOverlayMessage(Text.translatable(message).formatted(color));

		return stack;
	}
}
