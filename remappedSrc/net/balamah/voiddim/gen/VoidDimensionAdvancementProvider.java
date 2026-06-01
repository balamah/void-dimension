package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.Optional;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.KilledTrigger;
import net.minecraft.advancements.criterion.RecipeCraftedTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.balamah.voiddim.world.dimension.ModDimensions;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.item.ModItems;
import net.balamah.voiddim.tag.ModItemTags;

public class VoidDimensionAdvancementProvider extends FabricAdvancementProvider {
	public VoidDimensionAdvancementProvider(
		FabricPackOutput output, CompletableFuture<WrapperLookup> registryLookup
	) {
		super(output, registryLookup);
	}

	@Override
	@SuppressWarnings("unused")
	public void generateAdvancement(
		WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer
	) {
		final RegistryWrapper.Impl<Item> itemLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM);
		final RegistryWrapper.Impl<EntityType<?>> entityLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.ENTITY_TYPE);
		final RegistryWrapper.Impl<Potion> potionLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.POTION);

		var voidSalvationPotion = potionLookup.getOrThrow(
			RegistryKey.of(
				RegistryKeys.POTION,
				ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_salvation")
			)
		);

		DataComponentExactPredicate voidSalvationPotionDataComponent =
			DataComponentExactPredicate.builder()
				.expect(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(voidSalvationPotion))
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

		AdvancementEntry voidDimensionRoot = this.getAdvancementEntry(
			ModItems.VOIDIUM, "void_dimension",
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "gui/advancements/deepslate"),
			AdvancementFrame.TASK,
            false, "got_voidium",
            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOIDIUM),
            consumer, "void_dimension/root"
        );

		AdvancementEntry firstPreparations = this.getAdvancementBuilder(
			ModItems.ORTHODOX_CROSS, "first_preparations",
			null, AdvancementFrame.TASK, false
		)
		.parent(voidDimensionRoot)
		.addCriterion("got_void_salvation_potion", InventoryChangeTrigger.TriggerInstance.hasItems(voidSalvationPotionPredicate))
		.addCriterion("got_prayer_item", InventoryChangeTrigger.TriggerInstance.hasItems(prayerItemsPredicate))
		.requirements(AdvancementRequirements.allOf(
			List.of("got_void_salvation_potion", "got_prayer_item"))
		)
		.save(consumer, this.namespaced("get_saving_items"))
		;

		AdvancementEntry aForsakenPlace = this.getAdvancementEntry(
			Items.DEEPSLATE, "a_forsaken_place", firstPreparations,
			null, AdvancementFrame.GOAL, false, "got_into_void",
			ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.VOID_WORLD),
			consumer, "get_into_void"
		);

		AdvancementEntry cannibalism = this.getAdvancementEntry(
			ModItems.COOKED_FLESH, "cannibalism", aForsakenPlace,
			null, AdvancementFrame.TASK, false, "ate_alpha_steve_flesh",
			ConsumeItemTrigger.TriggerInstance.usedItem(itemLookup, ModItems.COOKED_FLESH),
			consumer, "eat_alpha_steve_cooked_flesh"
		);

		AdvancementEntry isThisHowZombiesAreCreated = this.getAdvancementEntry(
			ModItems.SPOILED_FLESH, "is_this_how_zombies_are_created", cannibalism,
			null, AdvancementFrame.TASK, true, "ate_zombified_steve_flesh",
            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPOILED_FLESH),
			consumer, "eat_zombified_steve_cooked_flesh"
		);

		AdvancementEntry firstSteps = this.getAdvancementEntry(
			ModItems.VOID_SHARD, "first_steps", aForsakenPlace,
			null, AdvancementFrame.TASK, false, "got_void_shard",
            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOID_SHARD),
			consumer, "get_void_shard"
		);

		AdvancementEntry voidUpgrade = this.getAdvancementBuilder(
			ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, "void_upgrade",
			null, AdvancementFrame.GOAL, false
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

		AdvancementEntry coverMeInVoid = this.getAdvancementEntry(
			ModItems.VOID_CHESTPLATE, "cover_me_in_void", voidUpgrade,
			null, AdvancementFrame.CHALLENGE, false, "got_void_armor",
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

		AdvancementEntry harbingerOfOblivion = this.getAdvancementEntry(
			ModItems.VOID_HARBINGER_SPAWN_EGG, "harbinger_of_oblivion", aForsakenPlace,
			null, AdvancementFrame.CHALLENGE, false, "killed_void_harbinger",
			KilledTrigger.TriggerInstance.playerKilledEntity(voidHarbingerPredicate),
			consumer, "kill_void_harbinger"
		);

		AdvancementEntry masterOfTheShattered = this.getAdvancementEntry(
			ModItems.SHATTERED_SENTINEL_SPAWN_EGG, "master_of_the_shattered", aForsakenPlace,
			null, AdvancementFrame.CHALLENGE, false, "killed_shattered_sentinel_master",
			KilledTrigger.TriggerInstance.playerKilledEntity(shatteredSentinelMasterPredicate),
			consumer, "kill_shattered_sentinel_master"
		);

		AdvancementEntry anomalyBeaten = this.getAdvancementEntry(
			ModItems.ENTITY303_SPAWN_EGG, "anomaly_beaten", aForsakenPlace,
			null, AdvancementFrame.CHALLENGE, false, "killed_entity303",
			KilledTrigger.TriggerInstance.playerKilledEntity(entity303Predicate),
			consumer, "kill_entity303"
		);

		AdvancementEntry exodusPreparation = this.getAdvancementBuilder(
			ModBlocks.BEDROCK_BOMB, "exodus_preparation",
			null, AdvancementFrame.TASK, false
		)
		.parent(firstSteps)
		.addCriterion("got_bedrock_bomb", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BEDROCK_BOMB))
		.addCriterion("got_flint_and_steel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT_AND_STEEL))
		.addCriterion("got_void_salvation_potion", InventoryChangeTrigger.TriggerInstance.hasItems(voidSalvationPotionPredicate))
		.requirements(AdvancementRequirements.allOf(List.of("got_bedrock_bomb", "got_flint_and_steel", "got_void_salvation_potion")))
		.save(consumer, this.namespaced("get_bedrock_bomb_flint_and_steel"))
			;

		AdvancementEntry exodus = this.getAdvancementBuilder(
			Blocks.NETHERRACK, "exodus",
			null, AdvancementFrame.GOAL, false
		)
		.parent(exodusPreparation)
		.addCriterion("went_from_void", ChangeDimensionTrigger.TriggerInstance.changedDimensionFrom(ModDimensions.VOID_WORLD))
		.addCriterion("went_to_nether", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(World.NETHER))
		.requirements(AdvancementRequirements.allOf(List.of("went_from_void", "went_to_nether")))
		.save(consumer, this.namespaced("go_to_nether"))
			;
	}

	protected Advancement.Builder getAdvancementBuilder(
		ItemConvertible icon, String advancementLocale,
		@Nullable ResourceLocation background,
		AdvancementFrame type, boolean hidden
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
			;
	}

	protected AdvancementEntry getAdvancementEntry(
		ItemConvertible icon, String advancementLocale,
		@Nullable AdvancementEntry parent,
		@Nullable ResourceLocation background,
		AdvancementFrame type, boolean hidden,
		String criteaName, AdvancementCriterion<?> criterion,
		Consumer<AdvancementEntry> consumer, String id
	) {
		return
			this.getAdvancementBuilder(icon, advancementLocale, background, type, hidden)
			.parent(parent)
			.addCriterion(criteaName, criterion)
			.save(consumer, this.namespaced(id));
	}

	protected AdvancementEntry getAdvancementEntry(
		ItemConvertible icon, String advancementLocale,
		@Nullable ResourceLocation background,
		AdvancementFrame type, boolean hidden,
		String criteaName, AdvancementCriterion<?> criterion,
		Consumer<AdvancementEntry> consumer, String id
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

	protected RegistryKey<Recipe<?>> getRecipeKey(String id) {
		return RegistryKey.of(
			RegistryKeys.RECIPE,
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, id)
		);
	}

	protected AdvancementCriterion<RecipeCraftedTrigger.TriggerInstance>
		getRecipeCraftedCondition(String id)
	{
		return RecipeCraftedTrigger.TriggerInstance.craftedItem(this.getRecipeKey(id));
	}

	protected EntityPredicate.Builder getEntityPredicateBuilder(
		RegistryWrapper.Impl<EntityType<?>> entityLookup,
		EntityType<?> entityType
	) {
		return EntityPredicate.Builder.entity().of(entityLookup, entityType);
	}

	protected Optional<EntityPredicate> getEntityPredicate(
		RegistryWrapper.Impl<EntityType<?>> entityLookup,
		EntityType<?> entityType
	) {
		return Optional.of(this.getEntityPredicateBuilder(entityLookup, entityType).build());
	}
}
