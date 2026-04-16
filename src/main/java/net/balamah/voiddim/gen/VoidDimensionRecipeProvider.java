package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Items;
import net.minecraft.item.Item;

import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.tag.ModItemTags;

public class VoidDimensionRecipeProvider extends FabricRecipeProvider {
	public VoidDimensionRecipeProvider(
		FabricDataOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "VoidDimensionRecipeProvider";
	}

	@Override
	protected RecipeGenerator getRecipeGenerator(
		RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter
	) {
		return new RecipeGenerator(registryLookup, exporter) {
			@Override
			public void generate() {
				this.createShaped(RecipeCategory.MISC, ModItems.ORTHODOX_CROSS, 1)
					.pattern("1")
					.pattern("2")
					.pattern("1")
					.input('1', ItemTags.WOODEN_BUTTONS)
					.input('2', ModItems.LATIN_CROSS)
					.group("multi_bench")
					.criterion(hasItem(ModItems.LATIN_CROSS),
							   this.conditionsFromItem(ModItems.LATIN_CROSS))
					.offerTo(this.exporter);

				this.createPrayerRopeRecipe(ModItems.WOOL_PRAYER_ROPE, ItemTags.WOOL);
				this.createPrayerRopeRecipe(ModItems.WOOD_PRAYER_ROPE, ItemTags.WOODEN_BUTTONS);
				this.createPrayerRopeRecipe(ModItems.SKULL_PRAYER_ROPE, ItemTags.SKULLS);

				this.createSmithingTemplateRecipe(
					ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, ModItems.VOID_INGOT,
					Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE
				);

				/*
				 * Netherite upgrade is a horrible change.
				 * The fact that this item is uncraftable and can be ONLY found in rare
				 * dungeons is just insane, for it is  painful to find on survival servers.
				 * It should be crafted
				 */
				this.createSmithingTemplateRecipe(
					Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERRACK,
					Items.NETHERRACK
				);

				this.createShaped(RecipeCategory.MISC, ModBlocks.VOID_SHARD_BLOCK, 1)
					.pattern("111")
					.pattern("111")
					.pattern("111")
					.input('1', ModItems.VOID_INGOT)
					.criterion(hasItem(ModItems.VOID_INGOT),
							   this.conditionsFromItem(ModItems.VOID_INGOT))
					.offerTo(this.exporter);

				this.createShapeless(RecipeCategory.MISC, ModItems.VOID_INGOT, 9)
					.input(ModBlocks.VOID_SHARD_BLOCK)
					.criterion(hasItem(ModBlocks.VOID_SHARD_BLOCK),
							   this.conditionsFromItem(ModBlocks.VOID_SHARD_BLOCK))
					.offerTo(this.exporter);

				this.createShaped(RecipeCategory.MISC, ModBlocks.WIND_MANIPULATION_BLOCK, 1)
					.pattern("121")
					.pattern("121")
					.pattern("111")
					.input('1', Items.DEEPSLATE)
					.input('2', ModItems.VOID_SHARD)
					.criterion(hasItem(ModItems.VOID_SHARD),
							   this.conditionsFromItem(ModItems.VOID_SHARD))
					.offerTo(this.exporter);

				this.createShapeless(RecipeCategory.MISC, ModItems.CORRUPTED_TORCH, 1)
					.input(Items.TORCH)
					.input(ModItems.VOIDIUM)
					.criterion(hasItem(ModItems.VOIDIUM),
							   this.conditionsFromItem(ModItems.VOIDIUM))
					.offerTo(exporter);

				this.createShapeless(RecipeCategory.MISC, ModBlocks.VOID_FLOWER, 1)
					.input(Items.POPPY)
					.input(ModItems.VOIDIUM)
					.criterion(hasItem(ModItems.VOIDIUM),
							   this.conditionsFromItem(ModItems.VOIDIUM))
						.offerTo(exporter);

				this.createShaped(RecipeCategory.MISC, ModBlocks.BEDROCK_BOMB, 1)
					.pattern("111")
					.pattern("121")
					.pattern("111")
					.input('1', Items.TNT)
					.input('2', ModItems.VOIDIUM)
					.criterion(hasItem(ModItems.VOIDIUM),
							   this.conditionsFromItem(ModItems.VOIDIUM))
					.offerTo(exporter);
			}

			protected ShapedRecipeJsonBuilder getPrayerRopeRecipe(
				Item result, TagKey<Item> material
			) {
				return this.createShaped(RecipeCategory.MISC, result, 1)
					.pattern("112")
					.pattern("121")
					.pattern("213")
					.input('1', Items.STRING)
					.input('2', material)
					.input('3', ModItemTags.CROSS_ITEMS)
					.group("multi_bench")
					.criterion(hasItem(Items.STRING),
							   this.conditionsFromItem(Items.STRING));
			}

			protected void createPrayerRopeRecipe(Item result, TagKey<Item> material) {
				this.getPrayerRopeRecipe(result, material).offerTo(this.exporter);
			}

			protected ShapedRecipeJsonBuilder getSmithingTemplateRecipe(
				Item result, Item base, Item upperItem
			) {
				return this.createShaped(RecipeCategory.MISC, result, 1)
					.pattern("131")
					.pattern("121")
					.pattern("111")
					.input('1', Items.DIAMOND)
					.input('2', base)
					.input('3', upperItem)
					.group("multi_bench")
					.criterion(hasItem(base), this.conditionsFromItem(base));
			}

			protected void createSmithingTemplateRecipe(Item result, Item base, Item upperItem) {
				this.getSmithingTemplateRecipe(result, base, upperItem).offerTo(this.exporter);
			}
		};
	}
}
