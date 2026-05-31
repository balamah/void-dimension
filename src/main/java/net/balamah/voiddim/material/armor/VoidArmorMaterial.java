package net.balamah.voiddim.material.armor;

import java.util.Map;
import java.util.function.Supplier;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
// import net.minecraft.world.item.equipment.ArmorType;
// import net.minecraft.world.item.equipment.EquipmentAsset;
// import net.minecraft.world.item.equipment.EquipmentAssets;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class VoidArmorMaterial {
	public static final int BASE_DURABILITY = 40;

	public static final Holder<ArmorMaterial> GUIDITE = registerMaterial("void",
			// Defense (protection) point values for each armor piece.
			Map.of(
				ArmorItem.Type.HELMET, 5,
				ArmorItem.Type.CHESTPLATE, 10,
				ArmorItem.Type.LEGGINGS, 8,
				ArmorItem.Type.BOOTS, 5
			),
			// Enchantability. For reference, leather has 15, iron has 9, and diamond has 10.
			15,
			SoundEvents.ARMOR_EQUIP_NETHERITE,
			() -> Ingredient.of(ModItems.SUSPICIOUS_SUBSTANCE),
			3.5F,
			0.2F,
			false);
	// :::2

	// :::1
	public static Holder<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, Holder<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
		// Get the supported layers for the armor material
		List<ArmorMaterial.Layer> layers = List.of(
			// The ID of the texture layer, the suffix, and whether the layer is dyeable.
			// We can just pass the armor material ID as the texture layer ID.
			// We have no need for a suffix, so we'll pass an empty string.
			// We'll pass the dyeable boolean we received as the dyeable parameter.
			new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ExampleMod.MOD_ID, id), "", dyeable)
		);

		ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
		// Register the material within the ArmorMaterials registry.
		material = Registry.register(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(ExampleMod.MOD_ID, id), material);

		// The majority of the time, you'll want the RegistryEntry of the material - especially for the ArmorItem constructor.
		return Holder.direct(material);
	}

	// :::1
	public static void initialize() { }




/*
	public static final ResourceKey<EquipmentAsset> VOID_ARMOR_MATERIAL_KEY =
		ResourceKey.create(
			EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void")
		);

	public static final TagKey<Item> REPAIRS_VOID_ARMOR =
		TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "repairs_void_armor"));

	public static final ArmorMaterial INSTANCE = new ArmorMaterial(
		BASE_DURABILITY,
		Map.of(
				ArmorItem.Type.HELMET, 5,
				ArmorItem.Type.CHESTPLATE, 10,
				ArmorItem.Type.LEGGINGS, 8,
				ArmorItem.Type.BOOTS, 5
		),
		15,
		SoundEvents.ARMOR_EQUIP_NETHERITE,
		3.5F,
		0.2F,
		REPAIRS_VOID_ARMOR,
		VOID_ARMOR_MATERIAL_KEY
	);

	/**
	 * Required for armor item registration.
	 * To register armor item, it is required to pass maxDamage for registration
	 * EquipmentType.getMaxDamage(ArmorMaterial.durability()) in method
	 * {@link net.balamah.voiddim.item.ModItems#registerArmor}
	 */
	// public static int durability() {
	// 	return BASE_DURABILITY;
	// }*/
}
