package net.balamah.voiddim.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class McGenHelper {
	public static NumberProvider getRandomNumberProvider(
		int minimumCount, int maximumCount
	) {
		NumberProvider min = ConstantValue.exactly(minimumCount);
		NumberProvider max = ConstantValue.exactly(maximumCount);

		return new UniformGenerator(min, max);
	}

	public static NumberProvider constantNumber(int count) {
		return ConstantValue.exactly(count);
	}

	public static LootItemConditionalFunction.Builder<?> randomNumber(
		int minimumCount, int maximumCount
	) {
		return SetItemCountFunction.setCount(
			getRandomNumberProvider(minimumCount, maximumCount)
		);
	}

	public static LootPool.Builder getPool(NumberProvider provider) {
		return LootPool.lootPool().setRolls(provider);
	}

	public static LootPool.Builder getPool(NumberProvider provider, float chance) {
		return getPool(provider)
			.when(LootItemRandomChanceCondition.randomChance(chance));
	}

	public static LootPoolEntryContainer.Builder<?> getItemEntry(
		ItemLike item, int minCount, int maxCount
	) {
		return LootItem.lootTableItem(item).apply(randomNumber(minCount, maxCount));
	}

	public static LootPoolEntryContainer.Builder<?> getItemEntry(ItemLike item, int count) {
		return getItemEntry(item, count, count);
	}
}
