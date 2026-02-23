package net.balamah.voiddim.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
	public static final FoodComponent RAW_FLESH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.3F).build();
	public static final FoodComponent COOKED_FLESH = new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
}
