package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.Optional;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.KilledTrigger;
import net.minecraft.advancements.criterion.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.balamah.voiddim.world.dimension.ModDimensions;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.tag.ModItemTags;

public class VoidDimensionAdvancementProvider extends FabricAdvancementProvider {
	public VoidDimensionAdvancementProvider(
		FabricPackOutput output, CompletableFuture<Provider> registryLookup
	) {
		super(output, registryLookup);
	}

	@Override
	@SuppressWarnings("unused")
	public void generateAdvancement(
		Provider wrapperLookup, Consumer<AdvancementHolder> consumer
	) {
		final HolderLookup.RegistryLookup<Item> itemLookup = wrapperLookup.lookupOrThrow(Registries.ITEM);
		final HolderLookup.RegistryLookup<EntityType<?>> entityLookup = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);
		final HolderLookup.RegistryLookup<Potion> potionLookup = wrapperLookup.lookupOrThrow(Registries.POTION);

		var voidSalvationPotion = potionLookup.getOrThrow(
			ResourceKey.create(
				Registries.POTION,
				Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_salvation")
			)
		);

		DataComponentExactPredicate voidSalvationPotionDataComponent =
			DataComponentExactPredicate.builder()
				.expect(DataComponents.POTION_CONTENTS, new PotionContents(voidSalvationPotion))
				.build();

		DataComponentMatchers potionMatcher =
			DataComponentMatchers.Builder.components()
				.exact(voidSalvationPotionDataComponent)
				.build();

		ItemPredicate voidSalvationPotionPredicate =
			ItemPredicate.Builder.item()
				.of(itemLookup, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION)
				.withComponents(potionMatcher)
				.build();

		ItemPredicate prayerItemsPredicate =
			ItemPredicate.Builder.item().of(itemLookup, ModItemTags.PRAYER_ITEMS).build();

		AdvancementHolder voidDimensionRoot = this.getAdvancementEntry(
			ModItems.VOIDIUM, "void_dimension",
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "gui/advancements/deepslate"),
			AdvancementType.TASK,
            false, "got_voidium",
            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOIDIUM),
            consumer, "void_dimension/root"
        );

		AdvancementHolder firstPreparations = this.getAdvancementBuilder(
			ModItems.ORTHODOX_CROSS, "first_preparations",
			null, AdvancementType.TASK, false
		)
		.parent(voidDimensionRoot)
		.addCriterion("got_void_salvation_potion", InventoryChangeTrigger.TriggerInstance.hasItems(voidSalvationPotionPredicate))
		.addCriterion("got_prayer_item", InventoryChangeTrigger.TriggerInstance.hasItems(prayerItemsPredicate))
		.requirements(AdvancementRequirements.allOf(
			List.of("got_void_salvation_potion", "got_prayer_item"))
		)
		.save(consumer, this.namespaced("get_saving_items"))
		;

		AdvancementHolder aForsakenPlace = this.getAdvancementEntry(
			Items.DEEPSLATE, "a_forsaken_place", firstPreparations,
			null, AdvancementType.GOAL, false, "got_into_void",
			ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.VOID_WORLD),
			consumer, "get_into_void"
		);

		AdvancementHolder cannibalism = this.getAdvancementEntry(
			ModItems.COOKED_FLESH, "cannibalism", aForsakenPlace,
			null, AdvancementType.TASK, false, "ate_alpha_steve_flesh",
			ConsumeItemTrigger.TriggerInstance.usedItem(itemLookup, ModItems.COOKED_FLESH),
			consumer, "eat_alpha_steve_cooked_flesh"
		);

		AdvancementHolder isThisHowZombiesAreCreated = this.getAdvancementEntry(
			ModItems.SPOILED_FLESH, "is_this_how_zombies_are_created", cannibalism,
			null, AdvancementType.TASK, true, "ate_zombified_steve_flesh",
            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPOILED_FLESH),
			consumer, "eat_zombified_steve_cooked_flesh"
		);

		AdvancementHolder firstSteps = this.getAdvancementEntry(
			ModItems.VOID_SHARD, "first_steps", aForsakenPlace,
			null, AdvancementType.TASK, false, "got_void_shard",
            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOID_SHARD),
			consumer, "get_void_shard"
		);

		AdvancementHolder voidUpgrade = this.getAdvancementBuilder(
			ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, "void_upgrade",
			null, AdvancementType.GOAL, false
		)
		.parent(firstSteps)
		.addCriterion("upgrade_netherite_horse_armor"	, this.getRecipeCraftedCondition("void_horse_armor_smithing"))
		.addCriterion("upgrade_netherite_boots"		, this.getRecipeCraftedCondition("void_boots_smithing"))
		.addCriterion("upgrade_netherite_pickaxe"		, this.getRecipeCraftedCondition("void_pickaxe_smithing"))
		.addCriterion("upgrade_netherite_shovel"		, this.getRecipeCraftedCondition("void_shovel_smithing"))
		.addCriterion("upgrade_netherite_leggings"		, this.getRecipeCraftedCondition("void_leggings_smithing"))
		.addCriterion("upgrade_netherite_axe"			, this.getRecipeCraftedCondition("void_axe_smithing"))
		.addCriterion("upgrade_netherite_chestplate"	, this.getRecipeCraftedCondition("void_chestplate_smithing"))
		.addCriterion("upgrade_netherite_sword"		, this.getRecipeCraftedCondition("void_sword_smithing"))
		.addCriterion("upgrade_netherite_hoe"			, this.getRecipeCraftedCondition("void_hoe_smithing"))
		.addCriterion("upgrade_netherite_helmet"		, this.getRecipeCraftedCondition("void_helmet_smithing"))
		.addCriterion("upgrade_netherite_spear"		, this.getRecipeCraftedCondition("void_spear_smithing"))
		.requirements(AdvancementRequirements.anyOf(
			List.of("upgrade_netherite_horse_armor",
					"upgrade_netherite_boots",
					"upgrade_netherite_pickaxe",
					"upgrade_netherite_shovel",
					"upgrade_netherite_leggings",
					"upgrade_netherite_axe",
					"upgrade_netherite_chestplate",
					"upgrade_netherite_sword",
					"upgrade_netherite_hoe",
					"upgrade_netherite_helmet",
					"upgrade_netherite_spear"))
		)
		.save(consumer, this.namespaced("upgraded_netherite"))
		;

		AdvancementHolder coverMeInVoid = this.getAdvancementEntry(
			ModItems.VOID_CHESTPLATE, "cover_me_in_void", voidUpgrade,
			null, AdvancementType.CHALLENGE, false, "got_void_armor",
			InventoryChangeTrigger.TriggerInstance.hasItems(
				ModItems.VOID_HELMET, ModItems.VOID_CHESTPLATE, ModItems.VOID_LEGGINGS,
				ModItems.VOID_BOOTS
			),
			consumer, "get_void_armor"
		);

		Optional<EntityPredicate> voidHarbingerPredicate =
			this.getEntityPredicate(entityLookup, ModEntities.VOID_HARBINGER);

		Optional<EntityPredicate> entity303Predicate =
			this.getEntityPredicate(entityLookup, ModEntities.ENTITY303);

		Optional<EntityPredicate> shatteredSentinelMasterPredicate =
			this.getEntityPredicate(entityLookup, ModEntities.SHATTERED_SENTINEL_MASTER);

		AdvancementHolder harbingerOfOblivion = this.getAdvancementEntry(
			ModItems.VOID_HARBINGER_SPAWN_EGG, "harbinger_of_oblivion", aForsakenPlace,
			null, AdvancementType.CHALLENGE, false, "killed_void_harbinger",
			KilledTrigger.TriggerInstance.playerKilledEntity(voidHarbingerPredicate),
			consumer, "kill_void_harbinger"
		);

		AdvancementHolder masterOfTheShattered = this.getAdvancementEntry(
			ModItems.SHATTERED_SENTINEL_SPAWN_EGG, "master_of_the_shattered", aForsakenPlace,
			null, AdvancementType.CHALLENGE, false, "killed_shattered_sentinel_master",
			KilledTrigger.TriggerInstance.playerKilledEntity(shatteredSentinelMasterPredicate),
			consumer, "kill_shattered_sentinel_master"
		);

		AdvancementHolder anomalyBeaten = this.getAdvancementEntry(
			ModItems.ENTITY303_SPAWN_EGG, "anomaly_beaten", aForsakenPlace,
			null, AdvancementType.CHALLENGE, false, "killed_entity303",
			KilledTrigger.TriggerInstance.playerKilledEntity(entity303Predicate),
			consumer, "kill_entity303"
		);

		AdvancementHolder exodusPreparation = this.getAdvancementBuilder(
			ModBlocks.BEDROCK_BOMB, "exodus_preparation",
			null, AdvancementType.TASK, false
		)
		.parent(firstSteps)
		.addCriterion("got_bedrock_bomb", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BEDROCK_BOMB))
		.addCriterion("got_flint_and_steel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT_AND_STEEL))
		.addCriterion("got_void_salvation_potion", InventoryChangeTrigger.TriggerInstance.hasItems(voidSalvationPotionPredicate))
		.requirements(AdvancementRequirements.allOf(List.of("got_bedrock_bomb", "got_flint_and_steel", "got_void_salvation_potion")))
		.save(consumer, this.namespaced("get_bedrock_bomb_flint_and_steel"))
			;

		AdvancementHolder exodus = this.getAdvancementBuilder(
			Blocks.NETHERRACK, "exodus",
			null, AdvancementType.GOAL, false
		)
		.parent(exodusPreparation)
		.addCriterion("went_from_void", ChangeDimensionTrigger.TriggerInstance.changedDimensionFrom(ModDimensions.VOID_WORLD))
		.addCriterion("went_to_nether", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(Level.NETHER))
		.requirements(AdvancementRequirements.allOf(List.of("went_from_void", "went_to_nether")))
		.save(consumer, this.namespaced("go_to_nether"))
			;
	}

	protected Advancement.Builder getAdvancementBuilder(
		ItemLike icon, String advancementLocale,
		@Nullable Identifier background,
		AdvancementType type, boolean hidden
	) {
		String titleString = this.getLocaleString(advancementLocale, "title");
		String descriptionString = this.getLocaleString(advancementLocale, "description");

		return Advancement.Builder.advancement()
			.display(
				icon, // The display icon
				Component.translatable(titleString), // The title
				Component.translatable(descriptionString), // The description
				background, // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
				type, // TASK, CHALLENGE, or GOAL
				true, // Show the toast when completing it
				true, // Announce it to chat
				hidden // Hide it in the advancement tab until it's achieved
			)
			;
	}

	protected AdvancementHolder getAdvancementEntry(
		ItemLike icon, String advancementLocale,
		@Nullable AdvancementHolder parent,
		@Nullable Identifier background,
		AdvancementType type, boolean hidden,
		String criteaName, Criterion<?> criterion,
		Consumer<AdvancementHolder> consumer, String id
	) {
		return
			this.getAdvancementBuilder(icon, advancementLocale, background, type, hidden)
			.parent(parent)
			.addCriterion(criteaName, criterion)
			.save(consumer, this.namespaced(id));
	}

	protected AdvancementHolder getAdvancementEntry(
		ItemLike icon, String advancementLocale,
		@Nullable Identifier background,
		AdvancementType type, boolean hidden,
		String criteaName, Criterion<?> criterion,
		Consumer<AdvancementHolder> consumer, String id
	) {
		return
			this.getAdvancementBuilder(icon, advancementLocale, background, type, hidden)
			.addCriterion(criteaName, criterion)
			.save(consumer, this.namespaced(id));
	}

	protected String namespaced(String id) {
		if (id.indexOf(':') >= 0) {
			return id;
		}
		return VoidDimension.MOD_ID + ":" + id;
	}

	/**
	 * @param advancementLocale
	 * @param value Title or Description
	 */
	protected String getLocaleString(String advancementLocale, String value) {
		String baseAdvancementLocaleKey = "advancement.void-dimension";
		String titleKey = "%s.%s." + value;

		return String.format(titleKey, baseAdvancementLocaleKey, advancementLocale);
	}

	protected ResourceKey<Recipe<?>> getRecipeKey(String id) {
		return ResourceKey.create(
			Registries.RECIPE,
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, id)
		);
	}

	protected Criterion<RecipeCraftedTrigger.TriggerInstance>
		getRecipeCraftedCondition(String id)
	{
		return RecipeCraftedTrigger.TriggerInstance.craftedItem(this.getRecipeKey(id));
	}

	protected EntityPredicate.Builder getEntityPredicateBuilder(
		HolderLookup.RegistryLookup<EntityType<?>> entityLookup,
		EntityType<?> entityType
	) {
		return EntityPredicate.Builder.entity().of(entityLookup, entityType);
	}

	protected Optional<EntityPredicate> getEntityPredicate(
		HolderLookup.RegistryLookup<EntityType<?>> entityLookup,
		EntityType<?> entityType
	) {
		return Optional.of(this.getEntityPredicateBuilder(entityLookup, entityType).build());
	}
}
