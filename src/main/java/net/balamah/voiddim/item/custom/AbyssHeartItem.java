package net.balamah.voiddim.item.custom;

import java.util.function.Consumer;
import java.util.EnumSet;
import java.util.List;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AbyssHeartItem extends Item {
	protected List<ResourceKey<Level>> allowedDimensions = List.of(
		ModDimensions.ABYSS_WORLD, ModDimensions.VOID_WORLD
	);

	public AbyssHeartItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (level.isClientSide()) {
			return InteractionResult.PASS;
		}

		EnumSet<Relative> flags = EnumSet.of(Relative.X, Relative.Y, Relative.Z);
		ResourceKey<Level> currentDimension = player.level().dimension();

		ServerLevel overworld = player.level().getServer().getLevel(Level.OVERWORLD);
		int coordinateMultiplier = this.getCoordinateMultiplier(currentDimension);

		if (!this.allowedDimensions.contains(currentDimension) || overworld == null) {
			return InteractionResult.FAIL;
		}

		player.teleportTo(
			overworld,
			player.getX() * coordinateMultiplier,
			240,
			player.getZ() * coordinateMultiplier,
			flags, player.getYRot(), player.getXRot(), true
		);

		player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 800, 1));

		return InteractionResult.SUCCESS;
	}

	public int getCoordinateMultiplier(ResourceKey<Level> currentDimension) {
		switch (currentDimension.identifier().toString()) {
			case "void-dimension:abyss":
				return 12;
			case "void-dimension:void":
				return 1;
			default:
				return 1;
		}
	}

	@Override
	public void appendHoverText(
		ItemStack stack, TooltipContext context, TooltipDisplay displayComponent,
		Consumer<Component> textConsumer, TooltipFlag type
	) {
		textConsumer.accept(
			Component.translatable("item.void-dimension.abyss_heart.info")
			.withStyle(ChatFormatting.GOLD)
		);
	}
}
