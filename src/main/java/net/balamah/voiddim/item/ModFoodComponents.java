package net.balamah.voiddim.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodComponents {
	public static final FoodProperties RAW_FLESH =
		new FoodProperties.Builder().nutrition(5).saturationModifier(0.1F).build();

	public static final FoodProperties COOKED_FLESH =
		new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();

	public static final FoodProperties SPOILED_FLESH =
		new FoodProperties.Builder().nutrition(4).saturationModifier(0.1F).build();
}
