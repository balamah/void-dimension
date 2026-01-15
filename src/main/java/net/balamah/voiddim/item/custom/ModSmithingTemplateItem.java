package net.balamah.voiddim.item.custom;

import java.util.List;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
	public ModSmithingTemplateItem(
		Text appliesToText, Text ingredientsText,
		Text baseSlotDescriptionText, Text additionsSlotDescriptionText,
		List<Identifier> emptyBaseSlotTextures,
		List<Identifier> emptyAdditionsSlotTextures,
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
			List.of(Identifier.ofVanilla("container/slot/ingot")),
			settings
		);
	}

	private static List<Identifier> getUpgradeEmptyBaseSlotTextures() {
		return List.of(
			Identifier.ofVanilla("container/slot/helmet"),
			Identifier.ofVanilla("container/slot/chestplate"),
			Identifier.ofVanilla("container/slot/leggings"),
			Identifier.ofVanilla("container/slot/boots"),
			Identifier.ofVanilla("container/slot/axe"),
			Identifier.ofVanilla("container/slot/sword"),
			Identifier.ofVanilla("container/slot/spear"),
			Identifier.ofVanilla("container/slot/pickaxe"),
			Identifier.ofVanilla("container/slot/shovel"),
			Identifier.ofVanilla("container/slot/hoe"),
			Identifier.ofVanilla("container/slot/horse_armor"),
			Identifier.ofVanilla("container/slot/nautilus_armor")
		);
	}
}
