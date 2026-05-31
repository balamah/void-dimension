package net.balamah.voiddim.custom;

import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class McGenHelper {
	public static LootNumberProvider getRandomNumberProvider(
		int minimumCount, int maximumCount
	) {
		LootNumberProvider min = ConstantLootNumberProvider.create(minimumCount);
		LootNumberProvider max = ConstantLootNumberProvider.create(maximumCount);

		return new UniformLootNumberProvider(min, max);
	}

	public static LootNumberProvider constantNumber(int count) {
		return ConstantLootNumberProvider.create(count);
	}

	public static ConditionalLootFunction.Builder<?> randomNumber(
		int minimumCount, int maximumCount
	) {
		return SetCountLootFunction.builder(
			getRandomNumberProvider(minimumCount, maximumCount)
		);
	}

	public static LootPool.Builder getPool(LootNumberProvider provider) {
		return LootPool.builder().rolls(provider);
	}

	public static LootPool.Builder getPool(LootNumberProvider provider, float chance) {
		return getPool(provider)
			.conditionally(RandomChanceLootCondition.builder(chance));
	}

	public static LootPoolEntry.Builder<?> getItemEntry(
		ItemConvertible item, int minCount, int maxCount
	) {
		return ItemEntry.builder(item).apply(randomNumber(minCount, maxCount));
	}

	public static LootPoolEntry.Builder<?> getItemEntry(ItemConvertible item, int count) {
		return getItemEntry(item, count, count);
	}
}
