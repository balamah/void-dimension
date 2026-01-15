package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.balamah.voiddim.tag.ModItemTags;
import net.balamah.voiddim.item.ModItems;
import net.minecraft.item.Item;

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
	}

	protected Identifier getItemIdentifier(Item item) {
		return Registries.ITEM.getId(item);
	}
}
