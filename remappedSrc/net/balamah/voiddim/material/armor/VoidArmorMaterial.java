package net.balamah.voiddim.material.armor;

import java.util.Map;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class VoidArmorMaterial {
	public static final int BASE_DURABILITY = 40;

	public static final RegistryKey<EquipmentAsset> VOID_ARMOR_MATERIAL_KEY =
		RegistryKey.of(
			EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void")
		);

	public static final TagKey<Item> REPAIRS_VOID_ARMOR =
		TagKey.of(RegistryKeys.ITEM, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "repairs_void_armor"));

	public static final ArmorMaterial INSTANCE = new ArmorMaterial(
		BASE_DURABILITY,
		Map.of(
				ArmorType.HELMET, 5,
				ArmorType.CHESTPLATE, 10,
				ArmorType.LEGGINGS, 8,
				ArmorType.BOOTS, 5
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
