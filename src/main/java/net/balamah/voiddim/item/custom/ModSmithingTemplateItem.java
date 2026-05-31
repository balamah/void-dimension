package net.balamah.voiddim.item.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
	public ModSmithingTemplateItem(
		Component appliesToText, Component ingredientsText,
		Component baseSlotDescriptionText, Component additionsSlotDescriptionText,
		List<ResourceLocation> emptyBaseSlotTextures,
		List<ResourceLocation> emptyAdditionsSlotTextures,
		Item.Properties settings
	) {
		super(appliesToText, ingredientsText,
			  Component.translatable("upgrade.void-dimension").withStyle(ChatFormatting.GRAY),
			  baseSlotDescriptionText, additionsSlotDescriptionText,
			  emptyBaseSlotTextures, emptyAdditionsSlotTextures);
	}

	public static SmithingTemplateItem createVoidUpgrade(Item.Properties settings) {
		return new SmithingTemplateItem(
			Component.translatable("upgrade.void-dimension.apply_to")
			.withStyle(ChatFormatting.BLUE),
			Component.translatable("upgrade.void-dimension.ingredients")
			.withStyle(ChatFormatting.BLUE),
			Component.translatable("upgrade.void-dimension").withStyle(ChatFormatting.GRAY),
			Component.translatable("upgrade.void-dimension.base_slot_description"),
			Component.translatable("upgrade.void-dimension.additions_slot_description"),
			getUpgradeEmptyBaseSlotTextures(),
			List.of(ResourceLocation.withDefaultNamespace("container/slot/ingot"))
		);
	}

	private static List<ResourceLocation> getUpgradeEmptyBaseSlotTextures() {
		return List.of(
			ResourceLocation.withDefaultNamespace("container/slot/helmet"),
			ResourceLocation.withDefaultNamespace("container/slot/chestplate"),
			ResourceLocation.withDefaultNamespace("container/slot/leggings"),
			ResourceLocation.withDefaultNamespace("container/slot/boots"),
			ResourceLocation.withDefaultNamespace("container/slot/axe"),
			ResourceLocation.withDefaultNamespace("container/slot/sword"),
			ResourceLocation.withDefaultNamespace("container/slot/spear"),
			ResourceLocation.withDefaultNamespace("container/slot/pickaxe"),
			ResourceLocation.withDefaultNamespace("container/slot/shovel"),
			ResourceLocation.withDefaultNamespace("container/slot/hoe"),
			ResourceLocation.withDefaultNamespace("container/slot/horse_armor"),
			ResourceLocation.withDefaultNamespace("container/slot/nautilus_armor")
		);
	}
}
