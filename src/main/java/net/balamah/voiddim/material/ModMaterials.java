package net.balamah.voiddim.material;

import net.balamah.voiddim.material.armor.VoidArmorMaterial;
import net.balamah.voiddim.tag.ModBlockTags;
import net.minecraft.world.item.ToolMaterial;

public class ModMaterials {
	public static ToolMaterial VOID_TOOL_MATERIAL =
		new ToolMaterial(
			ModBlockTags.INCORRECT_FOR_VOID_TOOL,
			2096, 11.0F, 5.0F, 22, VoidArmorMaterial.REPAIRS_VOID_ARMOR
		);

	public static ToolMaterial HOLLOWNEST_MATERIAL =
		new ToolMaterial(
			ModBlockTags.INCORRECT_FOR_HOLLOWNEST_TOOL,
			2048, 9.0F, 5.0F, 20, VoidArmorMaterial.REPAIRS_VOID_ARMOR
		);
}
