package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.custom.VoidMawEntity;
import net.balamah.voiddim.VoidDimension;

public class VoidMawRenderer extends MobEntityRenderer<VoidMawEntity, VoidMawRenderState, VoidMawModel> 
{
	public VoidMawRenderer(EntityRendererFactory.Context context) {
		super(context, new VoidMawModel(context.getPart(VoidMawModel.VOID_MAW)), 0.75f);
	}

	@Override
	public VoidMawRenderState createRenderState() {
		return new VoidMawRenderState();
	}

	@Override
	public Identifier getTexture(VoidMawRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/void_maw.png");
	}

	@Override
	public void updateRenderState(VoidMawEntity entity,
								  VoidMawRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);

		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
	}
}
