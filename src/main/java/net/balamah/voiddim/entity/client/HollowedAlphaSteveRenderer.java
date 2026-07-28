package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.base.AlphaSteveEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.VoidDimension;

public class HollowedAlphaSteveRenderer
<E extends AlphaSteveEntity, S extends HollowedAlphaSteveRenderState, M extends HollowedAlphaSteveModel<S>>
	extends HumanoidMobRenderer<E, S, M>
{
	public HollowedAlphaSteveRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);
	}

	@Override
	public Identifier getTextureLocation(S state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/alpha_steve.png");
	}

	@Override
	public S createRenderState() {
		return (S) new HollowedAlphaSteveRenderState();
	}
}
