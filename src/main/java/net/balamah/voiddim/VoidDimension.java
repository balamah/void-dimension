package net.balamah.voiddim;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.api.ModInitializer;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.world.ModWorldGeneration;
import net.balamah.voiddim.data.loot.ModLootTables;
import net.balamah.voiddim.item.ModItemGroups;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.potion.ModPotions;
import net.balamah.voiddim.tag.ModBlockTags;
import net.balamah.voiddim.tag.ModItemTags;
import net.balamah.voiddim.event.ModEvents;
import net.balamah.voiddim.sound.ModSounds;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;

public class VoidDimension implements ModInitializer {
	public static final String MOD_ID = "void-dimension";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Registering mod components");

		ModSounds.registerModSounds();
		ModItemTags.registerModItemTags();
		ModBlockTags.registerModBlockTags();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();
		ModEffects.registerModEffects();
		ModPotions.registerModPotions();
		ModEntities.registerModEntities();
		ModEntities.registerSpawnRestrictions();
		ModDimensions.registerModDimensions();
		ModEvents.registerModEvents();
		ModLootTables.registerModLootTables();
		ModWorldGeneration.registerModWorldGeneration();

		// Register neccessary elements on the server side
		ModParticleTypes.registerParticlesServer();
		ModEntities.registerEntityAttributes();

		if (!FabricLoader.getInstance().isModLoaded("sodium")) {
			LOGGER.warn("!!! Sodium mod is recommended for best performance !!!");
		}
	}
}
