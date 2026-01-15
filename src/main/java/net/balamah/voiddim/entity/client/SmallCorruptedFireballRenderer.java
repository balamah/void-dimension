package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.SmallCorruptedFireballEntity;

public class SmallCorruptedFireballRenderer
	extends FlyingItemEntityRenderer<SmallCorruptedFireballEntity>
{
	public SmallCorruptedFireballRenderer(Context context) {
		super(context);
	}
	
	public Identifier getTexture(SmallCorruptedFireballEntity entity) {
		return Identifier.of(
			VoidDimension.MOD_ID, "textures/entity/small_corrupted_fireball.png"
		);
	}
}
