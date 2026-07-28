package net.balamah.voiddim.item.custom;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
	public ModSmithingTemplateItem(
		Component appliesToText, Component ingredientsText,
		Component baseSlotDescriptionText, Component additionsSlotDescriptionText,
		List<Identifier> emptyBaseSlotTextures,
		List<Identifier> emptyAdditionsSlotTextures,
		Item.Properties settings
	) {
		super(appliesToText, ingredientsText,
			  baseSlotDescriptionText, additionsSlotDescriptionText,
			  emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
	}

	public static SmithingTemplateItem createVoidUpgrade(Item.Properties settings) {
		return new SmithingTemplateItem(
			Component.translatable("upgrade.void-dimension.apply_to")
			.withStyle(ChatFormatting.BLUE),
			Component.translatable("upgrade.void-dimension.ingredients")
			.withStyle(ChatFormatting.BLUE),
			Component.translatable("upgrade.void-dimension.base_slot_description"),
			Component.translatable("upgrade.void-dimension.additions_slot_description"),
			getUpgradeEmptyBaseSlotTextures(),
			List.of(Identifier.withDefaultNamespace("container/slot/ingot")),
			settings
		);
	}

	private static List<Identifier> getUpgradeEmptyBaseSlotTextures() {
		return List.of(
			Identifier.withDefaultNamespace("container/slot/helmet"),
			Identifier.withDefaultNamespace("container/slot/chestplate"),
			Identifier.withDefaultNamespace("container/slot/leggings"),
			Identifier.withDefaultNamespace("container/slot/boots"),
			Identifier.withDefaultNamespace("container/slot/axe"),
			Identifier.withDefaultNamespace("container/slot/sword"),
			Identifier.withDefaultNamespace("container/slot/spear"),
			Identifier.withDefaultNamespace("container/slot/pickaxe"),
			Identifier.withDefaultNamespace("container/slot/shovel"),
			Identifier.withDefaultNamespace("container/slot/hoe"),
			Identifier.withDefaultNamespace("container/slot/horse_armor"),
			Identifier.withDefaultNamespace("container/slot/nautilus_armor")
		);
	}
}
