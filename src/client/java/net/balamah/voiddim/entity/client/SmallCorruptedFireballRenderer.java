package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.SmallCorruptedFireballEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;

public class SmallCorruptedFireballRenderer
	extends ThrownItemRenderer<SmallCorruptedFireballEntity>
{
	public SmallCorruptedFireballRenderer(Context context) {
		super(context);
	}
	
	public ResourceLocation getTexture(SmallCorruptedFireballEntity entity) {
		return ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/entity/small_corrupted_fireball.png"
		);
	}
}
