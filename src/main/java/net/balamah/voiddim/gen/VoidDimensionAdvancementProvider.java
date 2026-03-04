package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.item.ItemConvertible;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.recipe.Recipe;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.item.ModItems;

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
		final RegistryWrapper.Impl<EntityType<?>> entityLookup = wrapperLookup.getOrThrow(RegistryKeys.ENTITY_TYPE);

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

		AdvancementEntry voidUpgrade = this.getAdvancementBuilder(
			ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, "void_upgrade",
			null, AdvancementFrame.GOAL, false
		)
		.parent(firstSteps)
		.criterion("upgrade_netherite_horse_armor"	, this.getRecipeCraftedCondition("void_horse_armor_smithing"))
		.criterion("upgrade_netherite_boots"		, this.getRecipeCraftedCondition("void_boots_smithing"))
		.criterion("upgrade_netherite_pickaxe"		, this.getRecipeCraftedCondition("void_pickaxe_smithing"))
		.criterion("upgrade_netherite_shovel"		, this.getRecipeCraftedCondition("void_shovel_smithing"))
		.criterion("upgrade_netherite_leggings"		, this.getRecipeCraftedCondition("void_leggings_smithing"))
		.criterion("upgrade_netherite_axe"			, this.getRecipeCraftedCondition("void_axe_smithing"))
		.criterion("upgrade_netherite_chestplate"	, this.getRecipeCraftedCondition("void_chestplate_smithing"))
		.criterion("upgrade_netherite_sword"		, this.getRecipeCraftedCondition("void_sword_smithing"))
		.criterion("upgrade_netherite_hoe"			, this.getRecipeCraftedCondition("void_hoe_smithing"))
		.criterion("upgrade_netherite_helmet"		, this.getRecipeCraftedCondition("void_helmet_smithing"))
		.criterion("upgrade_netherite_spear"		, this.getRecipeCraftedCondition("void_spear_smithing"))
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
		.build(consumer, VoidDimension.MOD_ID + "upgraded_netherite")
		;

		AdvancementEntry coverMeInVoid = this.getAdvancementEntry(
			ModItems.VOID_CHESTPLATE, "cover_me_in_void", voidUpgrade,
			null, AdvancementFrame.CHALLENGE, false, "got_void_armor",
			InventoryChangedCriterion.Conditions.items(
				ModItems.VOID_HELMET, ModItems.VOID_CHESTPLATE, ModItems.VOID_LEGGINGS,
				ModItems.VOID_BOOTS
			),
			consumer, "get_void_armor"
		);

		Optional<EntityPredicate> voidHarbingerPredicate =
			this.getEntityPredicate(entityLookup, ModEntities.VOID_HARBINGER);

		Optional<EntityPredicate> shatteredSentinelMasterPredicate =
			this.getEntityPredicate(entityLookup, ModEntities.SHATTERED_SENTINEL_MASTER);

		AdvancementEntry harbingerOfOblivion = this.getAdvancementEntry(
			ModItems.VOID_HARBINGER_SPAWN_EGG, "harbinger_of_oblivion", aForsakenPlace,
			null, AdvancementFrame.CHALLENGE, false, "killed_void_harbinger",
			OnKilledCriterion.Conditions.createPlayerKilledEntity(voidHarbingerPredicate),
			consumer, "kill_void_harbinger"
		);

		AdvancementEntry masterOfTheShattered = this.getAdvancementEntry(
			ModItems.SHATTERED_SENTINEL_SPAWN_EGG, "master_of_the_shattered", aForsakenPlace,
			null, AdvancementFrame.CHALLENGE, false, "killed_shattered_sentinel_master",
			OnKilledCriterion.Conditions.createPlayerKilledEntity(shatteredSentinelMasterPredicate),
			consumer, "kill_shattered_sentinel_master"
		);
	}

	protected Advancement.Builder getAdvancementBuilder(
		ItemConvertible icon, String advancementLocale,
		@Nullable Identifier background,
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
		@Nullable Identifier background,
		AdvancementFrame type, boolean hidden,
		String criteaName, AdvancementCriterion<?> criterion,
		Consumer<AdvancementEntry> consumer, String id
	) {
		return
			this.getAdvancementBuilder(icon, advancementLocale, background, type, hidden)
			.parent(parent)
			.criterion(criteaName, criterion)
			.build(consumer, VoidDimension.MOD_ID + id);
	}

	protected AdvancementEntry getAdvancementEntry(
		ItemConvertible icon, String advancementLocale,
		@Nullable Identifier background,
		AdvancementFrame type, boolean hidden,
		String criteaName, AdvancementCriterion<?> criterion,
		Consumer<AdvancementEntry> consumer, String id
	) {
		return
			this.getAdvancementBuilder(icon, advancementLocale, background, type, hidden)
			.criterion(criteaName, criterion)
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

	protected RegistryKey<Recipe<?>> getRecipeKey(String id) {
		return RegistryKey.of(
			RegistryKeys.RECIPE,
			Identifier.of(VoidDimension.MOD_ID, id)
		);
	}

	protected AdvancementCriterion<RecipeCraftedCriterion.Conditions>
		getRecipeCraftedCondition(String id)
	{
		return RecipeCraftedCriterion.Conditions.create(this.getRecipeKey(id));
	}

	protected EntityPredicate.Builder getEntityPredicateBuilder(
		RegistryWrapper.Impl<EntityType<?>> entityLookup,
		EntityType<?> entityType
	) {
		return EntityPredicate.Builder.create().type(entityLookup, entityType);
	}

	protected Optional<EntityPredicate> getEntityPredicate(
		RegistryWrapper.Impl<EntityType<?>> entityLookup,
		EntityType<?> entityType
	) {
		return Optional.of(this.getEntityPredicateBuilder(entityLookup, entityType).build());
	}
}
