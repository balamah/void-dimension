package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.world.dimension.ModDimensions;

public class VoidDimensionAdvancementProvider extends FabricAdvancementProvider {
	public VoidDimensionAdvancementProvider(
		FabricDataOutput output, CompletableFuture<WrapperLookup> registryLookup
	) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(
		WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer
	) {
		final RegistryWrapper.Impl<Item> itemLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);

        AdvancementEntry voidDimensionRoot = this.getAdvancementEntry(
			ModItems.VOIDIUM, "first_preparation",
			Identifier.of(VoidDimension.MOD_ID, "gui/advancements/deepslate"),
			AdvancementFrame.TASK,
            false, "got_voidium",
            InventoryChangedCriterion.Conditions.items(ModItems.VOIDIUM),
            consumer, "void_dimension/root"
        );

		AdvancementEntry aForsakenPlace = this.getAdvancementEntry(
			Items.DEEPSLATE, "a_forsaken_place", voidDimensionRoot,
			null, AdvancementFrame.TASK, false, "got_into_void",
			ChangedDimensionCriterion.Conditions.to(ModDimensions.VOID_WORLD),
			consumer, "get_into_void"
		);

		AdvancementEntry cannibalism = this.getAdvancementEntry(
			ModItems.COOKED_FLESH, "cannibalism", aForsakenPlace,
			null, AdvancementFrame.TASK, false, "ate_alpha_steve_flesh",
			ConsumeItemCriterion.Conditions.item(itemLookup, ModItems.COOKED_FLESH),
			consumer, "eat_alpha_steve_cooked_flesh"
		);

		AdvancementEntry isThisHowZombiesAreCreated = this.getAdvancementEntry(
			ModItems.SPOILED_FLESH, "is_this_how_zombies_are_created", cannibalism,
			null, AdvancementFrame.TASK, true, "ate_zombified_steve_flesh",
            InventoryChangedCriterion.Conditions.items(ModItems.SPOILED_FLESH),
			consumer, "eat_zombified_steve_cooked_flesh"
		);

		AdvancementEntry firstSteps = this.getAdvancementEntry(
			ModItems.VOID_SHARD, "first_steps", aForsakenPlace,
			null, AdvancementFrame.TASK, false, "got_void_shard",
            InventoryChangedCriterion.Conditions.items(ModItems.VOID_SHARD),
			consumer, "get_void_shard"
		);

		AdvancementEntry voidUpgrade = this.getAdvancementEntry(
			ModItems.VOID_SHARD, "void_upgrade", firstSteps,
			null, AdvancementFrame.TASK, false, "upgraded_netherite",
            InventoryChangedCriterion.Conditions.items(ModItems.VOID_SHARD),
			consumer, "upgrade_netherite"
		);
	}

	protected AdvancementEntry getAdvancementEntry(
		ItemConvertible icon, String advancementLocale,
		@Nullable AdvancementEntry parent,
		@Nullable Identifier background,
		AdvancementFrame type, boolean hidden,
		String criteaName, AdvancementCriterion<?> criterion,
		Consumer<AdvancementEntry> consumer, String id
	) {
		String titleString = this.getLocaleString(advancementLocale, "title");
		String descriptionString = this.getLocaleString(advancementLocale, "description");

		return Advancement.Builder.create()
			.parent(parent)
			.display(
				icon, // The display icon
				Text.translatable(titleString), // The title
				Text.translatable(descriptionString), // The description
				background, // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
				type, // TASK, CHALLENGE, or GOAL
				true, // Show the toast when completing it
				true, // Announce it to chat
				hidden // Hide it in the advancement tab until it's achieved
			)
			// criteriaName is the name referenced by other advancements when they want to have "requirements."
			.criterion(criteaName, criterion)
			// Give the advancement an id
			.build(consumer, VoidDimension.MOD_ID + id);
	}

	protected AdvancementEntry getAdvancementEntry(
		ItemConvertible icon, String advancementLocale,
		@Nullable Identifier background,
		AdvancementFrame type, boolean hidden,
		String criteaName, AdvancementCriterion<?> criterion,
		Consumer<AdvancementEntry> consumer, String id
	) {
		String titleString = this.getLocaleString(advancementLocale, "title");
		String descriptionString = this.getLocaleString(advancementLocale, "description");

		return Advancement.Builder.create()
			.display(
				icon, // The display icon
				Text.translatable(titleString), // The title
				Text.translatable(descriptionString), // The description
				background, // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
				type, // TASK, CHALLENGE, or GOAL
				true, // Show the toast when completing it
				true, // Announce it to chat
				hidden // Hide it in the advancement tab until it's achieved
			)
			// criteriaName is the name referenced by other advancements when they want to have "requirements."
			.criterion(criteaName, criterion)
			// Give the advancement an id
			.build(consumer, VoidDimension.MOD_ID + id);
	}

	/**
	/**
	 * @param advancementLocale
	 * @param value Title or Description
	 */
	protected String getLocaleString(String advancementLocale, String value) {
		String baseAdvancementLocaleKey = "advancement.void-dimension";
		String titleKey = "%s.%s." + value;

		return String.format(titleKey, baseAdvancementLocaleKey, advancementLocale);
	}
}
