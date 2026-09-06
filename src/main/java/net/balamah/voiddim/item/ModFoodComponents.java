package net.balamah.voiddim.item;

import java.util.List;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodComponents {
	private static List<MobEffectInstance> MASHA_MILK_BUCKET_EFFECTS =
		List.of(
			new MobEffectInstance(MobEffects.LEVITATION, 220, 1),
			new MobEffectInstance(MobEffects.REGENERATION, 220, 3)
		);

	public static final FoodProperties RAW_FLESH =
		new FoodProperties.Builder().nutrition(5).saturationModifier(0.1F).build();

	public static final FoodProperties COOKED_FLESH =
		new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();

	public static final FoodProperties SPOILED_FLESH =
		new FoodProperties.Builder().nutrition(4).saturationModifier(0.1F).build();

	public static final Consumable MASHA_MILK_BUCKET =
		defaultDrink().onConsume(
			new ApplyStatusEffectsConsumeEffect(MASHA_MILK_BUCKET_EFFECTS)
		).build();

	public static final Consumable MALE_GOAT_MILK_BUCKET =
		defaultDrink().onConsume(
			new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.LUCK, 1200, 5))
		).build();

	public static Consumable.Builder defaultDrink() {
		return Consumable.builder()
			.consumeSeconds(1.6F)
			.animation(ItemUseAnimation.DRINK)
			.sound(SoundEvents.GENERIC_DRINK)
			.hasConsumeParticles(false);
	}
}
