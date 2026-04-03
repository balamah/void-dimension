package net.balamah.voiddim;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack.RegistryDependentFactory;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.balamah.voiddim.gen.*;

public class VoidDimensionDataGenerator implements DataGeneratorEntrypoint {
	protected RegistryDependentFactory<?>[] providers = {
		VoidDimensionItemTagProvider::new,
		VoidDimensionBlockTagProvider::new,
		VoidDimensionRecipeProvider::new,
		VoidDimensionBlockLootTableProvider::new,
		VoidDimensionChestLootTableProvider::new,
		VoidDimensionEntityLootTableProvider::new,
		VoidDimensionAdvancementProvider::new
	};

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		for (RegistryDependentFactory<?> provider : this.providers) {
			pack.addProvider(provider);
		}
	}
}
