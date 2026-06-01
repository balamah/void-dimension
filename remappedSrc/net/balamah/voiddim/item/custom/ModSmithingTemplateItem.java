package net.balamah.voiddim.item.custom;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
	public ModSmithingTemplateItem(
		Text appliesToText, Text ingredientsText,
		Text baseSlotDescriptionText, Text additionsSlotDescriptionText,
		List<ResourceLocation> emptyBaseSlotTextures,
		List<ResourceLocation> emptyAdditionsSlotTextures,
		Item.Settings settings
	) {
		super(appliesToText, ingredientsText,
			  baseSlotDescriptionText, additionsSlotDescriptionText,
			  emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
	}

	public static SmithingTemplateItem createVoidUpgrade(Item.Settings settings) {
		return new SmithingTemplateItem(
			Text.translatable("upgrade.void-dimension.apply_to")
			.formatted(Formatting.BLUE),
			Text.translatable("upgrade.void-dimension.ingredients")
			.formatted(Formatting.BLUE),
			Text.translatable("upgrade.void-dimension.base_slot_description"),
			Text.translatable("upgrade.void-dimension.additions_slot_description"),
			getUpgradeEmptyBaseSlotTextures(),
			List.of(ResourceLocation.withDefaultNamespace("container/slot/ingot")),
			settings
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
