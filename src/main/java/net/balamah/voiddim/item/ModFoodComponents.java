package net.balamah.voiddim.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
	public static final FoodComponent RAW_FLESH =
		new FoodComponent.Builder().nutrition(5).saturationModifier(0.1F).build();

	public static final FoodComponent COOKED_FLESH =
		new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();

	public static final FoodComponent SPOILED_FLESH =
		new FoodComponent.Builder().nutrition(4).saturationModifier(0.1F).build();
}
