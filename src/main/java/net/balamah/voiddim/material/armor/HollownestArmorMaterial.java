package net.balamah.voiddim.material.armor;

import java.util.Map;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class HollownestArmorMaterial {
	public static final int BASE_DURABILITY = 44;

	public static final ResourceKey<EquipmentAsset> HOLLOWNEST_ARMOR_MATERIAL_KEY =
		ResourceKey.create(
			EquipmentAssets.ROOT_ID,
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "hollownest")
		);

	public static final TagKey<Item> REPAIRS_HOLLOWNEST_ARMOR =
		TagKey.create(
			Registries.ITEM,
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "repairs_hollownest_armor")
		);

	public static final ArmorMaterial INSTANCE = new ArmorMaterial(
		BASE_DURABILITY,
		Map.of(
				ArmorType.HELMET, 4,
				ArmorType.CHESTPLATE, 9,
				ArmorType.LEGGINGS, 7,
				ArmorType.BOOTS, 4
		),
		15,
		SoundEvents.ARMOR_EQUIP_NETHERITE,
		3.5F,
		0.2F,
		REPAIRS_HOLLOWNEST_ARMOR,
		HOLLOWNEST_ARMOR_MATERIAL_KEY
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
