package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.tag.ModItemTags;

public class VoidDimensionRecipeProvider extends FabricRecipeProvider {
	public VoidDimensionRecipeProvider(
		FabricPackOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "VoidDimensionRecipeProvider";
	}

	@Override
	protected RecipeProvider createRecipeProvider(
		RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter
	) {
		return new RecipeProvider(registryLookup, exporter) {
			@Override
			public void buildRecipes() {
				this.shaped(RecipeCategory.MISC, ModItems.ORTHODOX_CROSS, 1)
					.pattern("1")
					.pattern("2")
					.pattern("1")
					.define('1', ItemTags.WOODEN_BUTTONS)
					.define('2', ModItems.LATIN_CROSS)
					.group("multi_bench")
					.unlockedBy(hasItem(ModItems.LATIN_CROSS),
							   this.conditionsFromItem(ModItems.LATIN_CROSS))
					.save(this.output);

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

				this.shaped(RecipeCategory.MISC, ModBlocks.VOID_SHARD_BLOCK, 1)
					.pattern("111")
					.pattern("111")
					.pattern("111")
					.define('1', ModItems.VOID_INGOT)
					.unlockedBy(hasItem(ModItems.VOID_INGOT),
							   this.conditionsFromItem(ModItems.VOID_INGOT))
					.save(this.output);

				this.shapeless(RecipeCategory.MISC, ModItems.VOID_INGOT, 9)
					.requires(ModBlocks.VOID_SHARD_BLOCK)
					.unlockedBy(hasItem(ModBlocks.VOID_SHARD_BLOCK),
							   this.conditionsFromItem(ModBlocks.VOID_SHARD_BLOCK))
					.save(this.output);

				this.shaped(RecipeCategory.MISC, ModBlocks.WIND_MANIPULATION_BLOCK, 1)
					.pattern("121")
					.pattern("121")
					.pattern("111")
					.define('1', Items.DEEPSLATE)
					.define('2', ModItems.VOID_SHARD)
					.unlockedBy(hasItem(ModItems.VOID_SHARD),
							   this.conditionsFromItem(ModItems.VOID_SHARD))
					.save(this.output);

				this.shapeless(RecipeCategory.MISC, ModItems.CORRUPTED_TORCH, 1)
					.requires(Items.TORCH)
					.requires(ModItems.VOIDIUM)
					.unlockedBy(hasItem(ModItems.VOIDIUM),
							   this.conditionsFromItem(ModItems.VOIDIUM))
					.save(output);

				this.shapeless(RecipeCategory.MISC, ModBlocks.VOID_FLOWER, 1)
					.requires(Items.POPPY)
					.requires(ModItems.VOIDIUM)
					.unlockedBy(hasItem(ModItems.VOIDIUM),
							   this.conditionsFromItem(ModItems.VOIDIUM))
						.save(output);

				this.shaped(RecipeCategory.MISC, ModBlocks.BEDROCK_BOMB, 1)
					.pattern("111")
					.pattern("121")
					.pattern("111")
					.define('1', Items.TNT)
					.define('2', ModItems.VOIDIUM)
					.unlockedBy(hasItem(ModItems.VOIDIUM),
							   this.conditionsFromItem(ModItems.VOIDIUM))
					.save(output);
			}

			protected ShapedRecipeJsonBuilder getPrayerRopeRecipe(
				Item result, TagKey<Item> material
			) {
				return this.shaped(RecipeCategory.MISC, result, 1)
					.pattern("112")
					.pattern("121")
					.pattern("213")
					.define('1', Items.STRING)
					.define('2', material)
					.define('3', ModItemTags.CROSS_ITEMS)
					.group("multi_bench")
					.unlockedBy(hasItem(Items.STRING),
							   this.conditionsFromItem(Items.STRING));
			}

			protected void createPrayerRopeRecipe(Item result, TagKey<Item> material) {
				this.getPrayerRopeRecipe(result, material).offerTo(this.output);
			}

			protected ShapedRecipeJsonBuilder getSmithingTemplateRecipe(
				Item result, Item base, Item upperItem
			) {
				return this.shaped(RecipeCategory.MISC, result, 1)
					.pattern("131")
					.pattern("121")
					.pattern("111")
					.define('1', Items.DIAMOND)
					.define('2', base)
					.define('3', upperItem)
					.group("multi_bench")
					.unlockedBy(hasItem(base), this.conditionsFromItem(base));
			}

			protected void createSmithingTemplateRecipe(Item result, Item base, Item upperItem) {
				this.getSmithingTemplateRecipe(result, base, upperItem).offerTo(this.output);
			}
		};
	}
}
