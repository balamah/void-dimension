package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.tag.ModItemTags;
import net.balamah.voiddim.item.ModItems;
import net.minecraft.world.item.Item;

public class VoidDimensionItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public VoidDimensionItemTagProvider(
		FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		this.builder(ModItemTags.PRAYER_ITEMS)
				.add(this.key(ModItems.LATIN_CROSS))
				.add(this.key(ModItems.ORTHODOX_CROSS))
				.add(this.key(ModItems.WOOL_PRAYER_ROPE))
				.add(this.key(ModItems.WOOD_PRAYER_ROPE))
				.add(this.key(ModItems.SKULL_PRAYER_ROPE));

		this.builder(ModItemTags.CROSS_ITEMS)
			.add(this.key(ModItems.LATIN_CROSS))
			.add(this.key(ModItems.ORTHODOX_CROSS));

		this.builder(ModItemTags.PRAYER_ROPE_ITEMS)
			.add(this.key(ModItems.WOOL_PRAYER_ROPE))
			.add(this.key(ModItems.WOOD_PRAYER_ROPE))
			.add(this.key(ModItems.SKULL_PRAYER_ROPE));

		this.builder(ItemTags.ARMOR_ENCHANTABLE)
			.add(this.key(ModItems.VOID_HELMET))
			.add(this.key(ModItems.VOID_CHESTPLATE))
			.add(this.key(ModItems.VOID_LEGGINGS))
			.add(this.key(ModItems.VOID_BOOTS));

		this.builder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
			.add(this.key(ModItems.VOID_HELMET));

		this.builder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
			.add(this.key(ModItems.VOID_CHESTPLATE));

		this.builder(ItemTags.LEG_ARMOR_ENCHANTABLE)
			.add(this.key(ModItems.VOID_LEGGINGS));

		this.builder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
			.add(this.key(ModItems.VOID_BOOTS));

		this.builder(ItemTags.WEAPON_ENCHANTABLE)
			.add(this.key(ModItems.VOID_SWORD))
			.add(this.key(ModItems.VOID_SPEAR))
			.add(this.key(ModItems.VOID_AXE));

		this.builder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
			.add(this.key(ModItems.VOID_SWORD))
			.add(this.key(ModItems.VOID_SPEAR))
			.add(this.key(ModItems.VOID_AXE));

		this.builder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
			.add(this.key(ModItems.VOID_SWORD))
			.add(this.key(ModItems.VOID_SPEAR))
			.add(this.key(ModItems.VOID_AXE));

		this.builder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
			.add(this.key(ModItems.VOID_SWORD))
			.add(this.key(ModItems.VOID_SPEAR));

		this.builder(ItemTags.SWEEPING_ENCHANTABLE)
			.add(this.key(ModItems.VOID_SWORD));

		this.builder(ItemTags.MINING_ENCHANTABLE)
			.add(this.key(ModItems.VOID_PICKAXE))
			.add(this.key(ModItems.VOID_AXE))
			.add(this.key(ModItems.VOID_SHOVEL));

		this.builder(ItemTags.MINING_LOOT_ENCHANTABLE)
			.add(this.key(ModItems.VOID_PICKAXE))
			.add(this.key(ModItems.VOID_AXE))
			.add(this.key(ModItems.VOID_SHOVEL));

		this.builder(ItemTags.DURABILITY_ENCHANTABLE)
			.add(this.key(ModItems.VOID_HELMET))
			.add(this.key(ModItems.VOID_CHESTPLATE))
			.add(this.key(ModItems.VOID_LEGGINGS))
			.add(this.key(ModItems.VOID_BOOTS))
			.add(this.key(ModItems.VOID_AXE))
			.add(this.key(ModItems.VOID_HOE))
			.add(this.key(ModItems.VOID_PICKAXE))
			.add(this.key(ModItems.VOID_SHOVEL))
			.add(this.key(ModItems.VOID_SWORD))
			.add(this.key(ModItems.VOID_SPEAR));

		this.builder(ItemTags.VANISHING_ENCHANTABLE)
			.add(this.key(ModItems.VOID_HELMET))
			.add(this.key(ModItems.VOID_CHESTPLATE))
			.add(this.key(ModItems.VOID_LEGGINGS))
			.add(this.key(ModItems.VOID_BOOTS))
			.add(this.key(ModItems.VOID_AXE))
			.add(this.key(ModItems.VOID_HOE))
			.add(this.key(ModItems.VOID_PICKAXE))
			.add(this.key(ModItems.VOID_SHOVEL))
			.add(this.key(ModItems.VOID_SWORD))
			.add(this.key(ModItems.VOID_SPEAR));

		this.builder(ItemTags.EQUIPPABLE_ENCHANTABLE)
			.add(this.key(ModItems.VOID_HELMET))
			.add(this.key(ModItems.VOID_CHESTPLATE))
			.add(this.key(ModItems.VOID_LEGGINGS))
			.add(this.key(ModItems.VOID_BOOTS));

		this.builder(ItemTags.LUNGE_ENCHANTABLE)
			.add(this.key(ModItems.VOID_SPEAR));
	}

	private ResourceKey<Item> key(Item item) {
		return BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
	}
}
