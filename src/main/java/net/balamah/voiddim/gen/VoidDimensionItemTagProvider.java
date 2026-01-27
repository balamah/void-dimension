package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;

import net.balamah.voiddim.tag.ModItemTags;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionItemTagProvider extends FabricTagProvider<Item> {
	public VoidDimensionItemTagProvider(
		FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
	) {
		super(output, RegistryKeys.ITEM, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		this.getTagBuilder(ModItemTags.PRAYER_ITEMS)
				.add(this.getItemIdentifier(ModItems.LATIN_CROSS))
				.add(this.getItemIdentifier(ModItems.ORTHODOX_CROSS))
				.add(this.getItemIdentifier(ModItems.WOOL_PRAYER_ROPE))
				.add(this.getItemIdentifier(ModItems.WOOD_PRAYER_ROPE))
				.add(this.getItemIdentifier(ModItems.SKULL_PRAYER_ROPE));

		this.getTagBuilder(ModItemTags.CROSS_ITEMS)
			.add(this.getItemIdentifier(ModItems.LATIN_CROSS))
			.add(this.getItemIdentifier(ModItems.ORTHODOX_CROSS));

		this.getTagBuilder(ModItemTags.PRAYER_ROPE_ITEMS)
			.add(this.getItemIdentifier(ModItems.WOOL_PRAYER_ROPE))
			.add(this.getItemIdentifier(ModItems.WOOD_PRAYER_ROPE))
			.add(this.getItemIdentifier(ModItems.SKULL_PRAYER_ROPE));

		this.getTagBuilder(ItemTags.ARMOR_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_HELMET))
			.add(this.getItemIdentifier(ModItems.VOID_CHESTPLATE))
			.add(this.getItemIdentifier(ModItems.VOID_LEGGINGS))
			.add(this.getItemIdentifier(ModItems.VOID_BOOTS));

		this.getTagBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_HELMET));

		this.getTagBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_CHESTPLATE));

		this.getTagBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_LEGGINGS));

		this.getTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_BOOTS));

		this.getTagBuilder(ItemTags.WEAPON_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_SWORD))
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR))
			.add(this.getItemIdentifier(ModItems.VOID_AXE));

		this.getTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_SWORD))
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR))
			.add(this.getItemIdentifier(ModItems.VOID_AXE));

		this.getTagBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_SWORD))
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR))
			.add(this.getItemIdentifier(ModItems.VOID_AXE));

		this.getTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_SWORD))
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR));

		this.getTagBuilder(ItemTags.SWEEPING_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_SWORD));

		this.getTagBuilder(ItemTags.MINING_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_PICKAXE))
			.add(this.getItemIdentifier(ModItems.VOID_AXE))
			.add(this.getItemIdentifier(ModItems.VOID_SHOVEL));

		this.getTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_PICKAXE))
			.add(this.getItemIdentifier(ModItems.VOID_AXE))
			.add(this.getItemIdentifier(ModItems.VOID_SHOVEL));

		this.getTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_HELMET))
			.add(this.getItemIdentifier(ModItems.VOID_CHESTPLATE))
			.add(this.getItemIdentifier(ModItems.VOID_LEGGINGS))
			.add(this.getItemIdentifier(ModItems.VOID_BOOTS))
			.add(this.getItemIdentifier(ModItems.VOID_AXE))
			.add(this.getItemIdentifier(ModItems.VOID_HOE))
			.add(this.getItemIdentifier(ModItems.VOID_PICKAXE))
			.add(this.getItemIdentifier(ModItems.VOID_SHOVEL))
			.add(this.getItemIdentifier(ModItems.VOID_SWORD))
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR));

		this.getTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_HELMET))
			.add(this.getItemIdentifier(ModItems.VOID_CHESTPLATE))
			.add(this.getItemIdentifier(ModItems.VOID_LEGGINGS))
			.add(this.getItemIdentifier(ModItems.VOID_BOOTS))
			.add(this.getItemIdentifier(ModItems.VOID_AXE))
			.add(this.getItemIdentifier(ModItems.VOID_HOE))
			.add(this.getItemIdentifier(ModItems.VOID_PICKAXE))
			.add(this.getItemIdentifier(ModItems.VOID_SHOVEL))
			.add(this.getItemIdentifier(ModItems.VOID_SWORD))
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR));

		this.getTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_HELMET))
			.add(this.getItemIdentifier(ModItems.VOID_CHESTPLATE))
			.add(this.getItemIdentifier(ModItems.VOID_LEGGINGS))
			.add(this.getItemIdentifier(ModItems.VOID_BOOTS));

		this.getTagBuilder(ItemTags.LUNGE_ENCHANTABLE)
			.add(this.getItemIdentifier(ModItems.VOID_SPEAR));
	}

	protected Identifier getItemIdentifier(Item item) {
		return Registries.ITEM.getId(item);
	}
}
