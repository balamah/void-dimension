package net.balamah.voiddim.material.armor;

import java.util.Map;

import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;

import net.balamah.voiddim.VoidDimension;

public class VoidArmorMaterial {
	public static final int BASE_DURABILITY = 40;

	public static final RegistryKey<EquipmentAsset> VOID_ARMOR_MATERIAL_KEY =
		RegistryKey.of(
			EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VoidDimension.MOD_ID, "void")
		);

	public static final TagKey<Item> REPAIRS_VOID_ARMOR =
		TagKey.of(RegistryKeys.ITEM, Identifier.of(VoidDimension.MOD_ID, "repairs_void_armor"));

	public static final ArmorMaterial INSTANCE = new ArmorMaterial(
		BASE_DURABILITY,
		Map.of(
				EquipmentType.HELMET, 5,
				EquipmentType.CHESTPLATE, 10,
				EquipmentType.LEGGINGS, 8,
				EquipmentType.BOOTS, 5
		),
		15,
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
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
	public static int durability() {
		return BASE_DURABILITY;
	}
}
