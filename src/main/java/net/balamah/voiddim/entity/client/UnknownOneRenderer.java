package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.UnknownOneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class UnknownOneRenderer
	extends MobRenderer<UnknownOneEntity,
							  UnknownOneRenderState,
							  UnknownOneModel>
{
	public UnknownOneRenderer(EntityRendererProvider.Context context) {
		super(context, new UnknownOneModel(context.bakeLayer(UnknownOneModel.UNKNOWN_ONE)), 0.5f);
	}

	@Override
	public UnknownOneRenderState createRenderState() {
		return new UnknownOneRenderState();
	}

	@Override
	public Identifier getTextureLocation(UnknownOneRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/unknown_one.png");
	}

	@Override
	public void extractRenderState(UnknownOneEntity entity, UnknownOneRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.rageBaitAnimationState.copyFrom(entity.rageBaitAnimationState);
	}
}
