package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.VoidMawEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.VoidDimension;

public class VoidMawRenderer extends MobRenderer<VoidMawEntity, VoidMawRenderState, VoidMawModel> 
{
	public VoidMawRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidMawModel(context.bakeLayer(VoidMawModel.VOID_MAW)), 0.75f);
	}

	@Override
	public VoidMawRenderState createRenderState() {
		return new VoidMawRenderState();
	}

	@Override
	public Identifier getTextureLocation(VoidMawRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/void_maw.png");
	}

	@Override
	public void extractRenderState(VoidMawEntity entity,
								  VoidMawRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
	}
}
