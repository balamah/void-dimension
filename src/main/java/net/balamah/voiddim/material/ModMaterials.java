package net.balamah.voiddim.material;

import net.balamah.voiddim.material.armor.VoidArmorMaterial;
import net.balamah.voiddim.tag.ModBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ModMaterials {

    public static final Tier VOID_TOOL_MATERIAL =
        new Tier() {
            @Override
            public int getUses() {
                return 2096;
            }

            @Override
            public float getSpeed() {
                return 11.0F;
            }

            @Override
            public float getAttackDamageBonus() {
                return 5.0F;
            }

            @Override
            public TagKey<Block> getIncorrectBlocksForDrops() {
                return ModBlockTags.INCORRECT_FOR_VOID_TOOL;
            }

            @Override
            public int getEnchantmentValue() {
                return 22;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return VoidArmorMaterial.REPAIRS_VOID_ARMOR;
            }
        };
}
