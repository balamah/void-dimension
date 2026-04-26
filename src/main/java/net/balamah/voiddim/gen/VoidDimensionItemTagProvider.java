package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.tag.ModItemTags;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public VoidDimensionItemTagProvider(
		FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		this.valueLookupBuilder(ModItemTags.PRAYER_ITEMS)
				.add(ModItems.LATIN_CROSS)
				.add(ModItems.ORTHODOX_CROSS)
				.add(ModItems.WOOL_PRAYER_ROPE)
				.add(ModItems.WOOD_PRAYER_ROPE)
				.add(ModItems.SKULL_PRAYER_ROPE);

		this.valueLookupBuilder(ModItemTags.CROSS_ITEMS)
			.add(ModItems.LATIN_CROSS)
			.add(ModItems.ORTHODOX_CROSS);

		this.valueLookupBuilder(ModItemTags.PRAYER_ROPE_ITEMS)
			.add(ModItems.WOOL_PRAYER_ROPE)
			.add(ModItems.WOOD_PRAYER_ROPE)
			.add(ModItems.SKULL_PRAYER_ROPE);

		this.valueLookupBuilder(ItemTags.ARMOR_ENCHANTABLE)
			.add(ModItems.VOID_HELMET)
			.add(ModItems.VOID_CHESTPLATE)
			.add(ModItems.VOID_LEGGINGS)
			.add(ModItems.VOID_BOOTS);

		this.valueLookupBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
			.add(ModItems.VOID_HELMET);

		this.valueLookupBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
			.add(ModItems.VOID_CHESTPLATE);

		this.valueLookupBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
			.add(ModItems.VOID_LEGGINGS);

		this.valueLookupBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
			.add(ModItems.VOID_BOOTS);

		this.valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
			.add(ModItems.VOID_SWORD)
			.add(ModItems.VOID_SPEAR)
			.add(ModItems.VOID_AXE);

		this.valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
			.add(ModItems.VOID_SWORD)
			.add(ModItems.VOID_SPEAR)
			.add(ModItems.VOID_AXE);

		this.valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
			.add(ModItems.VOID_SWORD)
			.add(ModItems.VOID_SPEAR)
			.add(ModItems.VOID_AXE);

		this.valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
			.add(ModItems.VOID_SWORD)
			.add(ModItems.VOID_SPEAR);

		this.valueLookupBuilder(ItemTags.SWEEPING_ENCHANTABLE)
			.add(ModItems.VOID_SWORD);

		this.valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
			.add(ModItems.VOID_PICKAXE)
			.add(ModItems.VOID_AXE)
			.add(ModItems.VOID_SHOVEL);

		this.valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
			.add(ModItems.VOID_PICKAXE)
			.add(ModItems.VOID_AXE)
			.add(ModItems.VOID_SHOVEL);

		this.valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
			.add(ModItems.VOID_HELMET)
			.add(ModItems.VOID_CHESTPLATE)
			.add(ModItems.VOID_LEGGINGS)
			.add(ModItems.VOID_BOOTS)
			.add(ModItems.VOID_AXE)
			.add(ModItems.VOID_HOE)
			.add(ModItems.VOID_PICKAXE)
			.add(ModItems.VOID_SHOVEL)
			.add(ModItems.VOID_SWORD)
			.add(ModItems.VOID_SPEAR);

		this.valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
			.add(ModItems.VOID_HELMET)
			.add(ModItems.VOID_CHESTPLATE)
			.add(ModItems.VOID_LEGGINGS)
			.add(ModItems.VOID_BOOTS)
			.add(ModItems.VOID_AXE)
			.add(ModItems.VOID_HOE)
			.add(ModItems.VOID_PICKAXE)
			.add(ModItems.VOID_SHOVEL)
			.add(ModItems.VOID_SWORD)
			.add(ModItems.VOID_SPEAR);

		this.valueLookupBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
			.add(ModItems.VOID_HELMET)
			.add(ModItems.VOID_CHESTPLATE)
			.add(ModItems.VOID_LEGGINGS)
			.add(ModItems.VOID_BOOTS);

		this.valueLookupBuilder(ItemTags.LUNGE_ENCHANTABLE)
			.add(ModItems.VOID_SPEAR);
	}
}
