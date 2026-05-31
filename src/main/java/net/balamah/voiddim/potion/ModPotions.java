package net.balamah.voiddim.potion;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotions {

    public static final Potion VOID_SALVATION_POTION =
        register("void_salvation", ModEffects.VOID_SALVATION, 1200, 0);

    public static void registerModPotions() {
        VoidDimension.LOGGER.info("Registering mod potions for " + VoidDimension.MOD_ID);

        buildRecipe(Potions.AWKWARD, ModItems.VOIDIUM, VOID_SALVATION_POTION);
        buildRecipe(Potions.AWKWARD, ModItems.VOID_SHARD, VOID_SALVATION_POTION);
    }

    protected static Potion getPotion(
        String name, Holder<MobEffect> effect, int duration, int amplifier
    ) {
        return new Potion(name, new MobEffectInstance(effect, duration, amplifier));
    }

    protected static Potion register(
        String name, Holder<MobEffect> effect, int duration, int amplifier
    ) {
        return Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name),
            getPotion(name, effect, duration, amplifier)
        );
    }

    protected static void buildRecipe(
        Holder<Potion> input,
        Item ingredient,
        Potion output
    ) {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                input,
                Ingredient.of(ingredient),
                BuiltInRegistries.POTION.wrapAsHolder(output)
            );
        });
    }
}
