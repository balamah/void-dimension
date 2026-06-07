package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.tag.ModItemTags;

public class VoidDimensionRecipeProvider extends FabricRecipeProvider {
	public VoidDimensionRecipeProvider(
		FabricPackOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "VoidDimensionRecipeProvider";
	}

	@Override
	protected RecipeProvider createRecipeProvider(
		HolderLookup.Provider registryLookup, RecipeOutput exporter
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
					.unlockedBy(getHasName(ModItems.LATIN_CROSS),
							   this.has(ModItems.LATIN_CROSS))
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
					.unlockedBy(getHasName(ModItems.VOID_INGOT),
							   this.has(ModItems.VOID_INGOT))
					.save(this.output);

				this.shapeless(RecipeCategory.MISC, ModItems.VOID_INGOT, 9)
					.requires(ModBlocks.VOID_SHARD_BLOCK)
					.unlockedBy(getHasName(ModBlocks.VOID_SHARD_BLOCK),
							   this.has(ModBlocks.VOID_SHARD_BLOCK))
					.save(this.output);

				this.shaped(RecipeCategory.MISC, ModBlocks.WIND_MANIPULATION_BLOCK, 1)
					.pattern("121")
					.pattern("121")
					.pattern("111")
					.define('1', Items.DEEPSLATE)
					.define('2', ModItems.VOID_SHARD)
					.unlockedBy(getHasName(ModItems.VOID_SHARD),
							   this.has(ModItems.VOID_SHARD))
					.save(this.output);

				this.shapeless(RecipeCategory.MISC, ModItems.CORRUPTED_TORCH, 1)
					.requires(Items.TORCH)
					.requires(ModItems.VOIDIUM)
					.unlockedBy(getHasName(ModItems.VOIDIUM),
							   this.has(ModItems.VOIDIUM))
					.save(output);

				this.shapeless(RecipeCategory.MISC, ModBlocks.VOID_FLOWER, 1)
					.requires(Items.POPPY)
					.requires(ModItems.VOIDIUM)
					.unlockedBy(getHasName(ModItems.VOIDIUM),
							   this.has(ModItems.VOIDIUM))
						.save(output);

				this.shaped(RecipeCategory.MISC, ModItems.VOID_EXPLOSION_CORE, 1)
					.pattern("111")
					.pattern("121")
					.pattern("111")
					.define('1', Items.TNT)
					.define('2', ModItems.VOIDIUM)
					.unlockedBy(getHasName(ModItems.VOIDIUM),
							this.has(ModItems.VOIDIUM))
					.save(output);

				this.shaped(RecipeCategory.MISC, ModItems.VOID_EXPLOSION_UPGRADE, 1)
					.pattern("111")
					.pattern("121")
					.pattern("111")
					.define('1', Items.NETHERITE_INGOT)
					.define('2', ModItems.VOID_EXPLOSION_CORE)
					.unlockedBy(getHasName(ModItems.VOID_EXPLOSION_CORE),
							this.has(ModItems.VOID_EXPLOSION_CORE))
					.save(output);

				this.shaped(RecipeCategory.MISC, ModBlocks.BEDROCK_BOMB, 1)
					.pattern("121")
					.pattern("232")
					.pattern("121")
					.define('1', Items.OBSIDIAN)
					.define('2', Items.TNT)
					.define('3', ModItems.VOID_EXPLOSION_UPGRADE)
					.unlockedBy(getHasName(ModItems.VOID_EXPLOSION_UPGRADE),
							this.has(ModItems.VOID_EXPLOSION_UPGRADE))
					.save(output);
			}

			protected ShapedRecipeBuilder getPrayerRopeRecipe(
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
					.unlockedBy(getHasName(Items.STRING),
							   this.has(Items.STRING));
			}

			protected void createPrayerRopeRecipe(Item result, TagKey<Item> material) {
				this.getPrayerRopeRecipe(result, material).save(this.output);
			}

			protected ShapedRecipeBuilder getSmithingTemplateRecipe(
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
					.unlockedBy(getHasName(base), this.has(base));
			}

			protected void createSmithingTemplateRecipe(Item result, Item base, Item upperItem) {
				this.getSmithingTemplateRecipe(result, base, upperItem).save(this.output);
			}
		};
	}
}
