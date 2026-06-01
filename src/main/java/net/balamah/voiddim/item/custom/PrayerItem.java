package net.balamah.voiddim.item.custom;

import java.util.List;
import net.balamah.voiddim.interfaces.VoidPrayerDataAccess;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.effect.ModEffects;

public class PrayerItem extends Item {
	protected int prayerTime;
	protected int buffTime;
	protected int prayersHourMax;

	public PrayerItem(
		Properties settings, int prayerTime, int buffTime, int prayersHourMax
	) {
		super(settings);

		this.prayerTime = prayerTime;
		this.buffTime = buffTime;
		this.prayersHourMax = prayersHourMax;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		user.startUsingItem(hand);

		return InteractionResultHolder.consume(user.getItemInHand(hand));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK;
	}

	@Override
	public void appendHoverText(
		ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type
	) {
		int seconds = this.prayerTime / 20;

		MutableComponent text =
			Component.translatable("item.void-dimension.prayer_item.info.time", seconds)
			.withStyle(ChatFormatting.GOLD);

		tooltip.add(text);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return this.prayerTime;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		if (!(user instanceof Player player) || world.isClientSide()) {
			return stack;
		}

		String message;
		ChatFormatting color;

		CompoundTag data = ((VoidPrayerDataAccess) player).getVoidPrayerData();

		long currentHour = System.currentTimeMillis() / (1000 * 60 * 60);

		int prayCount = data.getInt("PrayerCount");
		long lastHour = data.getLong("LastHour");

		if (lastHour < currentHour) {
			prayCount = 0;
			data.putInt("PrayerCount", 0);
			data.putLong("LastHour", currentHour);
		}

		if (prayCount < this.prayersHourMax) {
			message = "text.void-dimension.prayer_success";
			color = ChatFormatting.GOLD;

			MobEffectInstance effect =
				new MobEffectInstance(ModEffects.DIVINE_PROTECTION, this.buffTime, 1);

			player.addEffect(effect);

			data.putInt("PrayerCount", prayCount + 1);
		} else {
			message = "text.void-dimension.prayer_failure";
			color = ChatFormatting.RED;
		}

			player.displayClientMessage(Component.translatable(message).withStyle(color), true);

		return stack;
	}
}
