package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.base.AlphaSteveEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class ZombifiedAlphaSteveRenderer
<E extends AlphaSteveEntity, S extends HollowedAlphaSteveRenderState, M extends HollowedAlphaSteveModel<S>>
	extends HollowedAlphaSteveRenderer<E, S, M>
{
	public ZombifiedAlphaSteveRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);
	}

	@Override
	public ResourceLocation getTextureLocation(S state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/zombified_alpha_steve.png");
	}
}
