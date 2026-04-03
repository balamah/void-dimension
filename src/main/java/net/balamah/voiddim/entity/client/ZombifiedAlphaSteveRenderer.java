package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.util.Identifier;
   
import net.balamah.voiddim.entity.custom.base.AlphaSteveEntity;
import net.balamah.voiddim.VoidDimension;

public class ZombifiedAlphaSteveRenderer
<E extends AlphaSteveEntity, S extends HollowedAlphaSteveRenderState, M extends HollowedAlphaSteveModel<S>>
	extends HollowedAlphaSteveRenderer<E, S, M>
{
	public ZombifiedAlphaSteveRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);
	}

	@Override
	public Identifier getTexture(S state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/zombified_alpha_steve.png");
	}
}
