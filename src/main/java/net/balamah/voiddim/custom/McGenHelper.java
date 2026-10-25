package net.balamah.voiddim.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.UniformGenerator;

public class McGenHelper {
	public static Holder<ContextIntProvider> getRandomNumberProvider(
		int minimumCount, int maximumCount
	) {
		Holder<ContextIntProvider> min = Holder.direct(new ConstantValue(minimumCount));
		Holder<ContextIntProvider> max = Holder.direct(new ConstantValue(maximumCount));

		return Holder.direct(new UniformGenerator(min, max));
	}

	public static Holder<ContextIntProvider> constantNumber(int count) {
		return Holder.direct(new ConstantValue(count));
	}

	public static LootItemConditionalFunction.Builder<?> randomNumber(
		int minimumCount, int maximumCount
	) {
		return SetItemCountFunction.setCount(
			getRandomNumberProvider(minimumCount, maximumCount)
		);
	}

	public static LootPool.Builder getPool(Holder<ContextIntProvider> provider) {
		return LootPool.lootPool().setRolls(provider);
	}

	public static LootPool.Builder getPool(Holder<ContextIntProvider> provider, float chance) {
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
