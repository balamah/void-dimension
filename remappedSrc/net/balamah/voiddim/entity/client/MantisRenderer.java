package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.entity.custom.MantisEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class MantisRenderer extends MobEntityRenderer<MantisEntity, BasicRenderState, MantisModel> 
{
	public MantisRenderer(EntityRendererFactory.Context context) {
		super(context, new MantisModel(context.getPart(MantisModel.MANTIS)), 0.75f);
	}

	@Override
	public BasicRenderState createRenderState() {
		return new BasicRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(BasicRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/mantis.png");
	}

	@Override
	public void submit(
		BasicRenderState state,
		MatrixStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
        if (state.isBaby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

		super.submit(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}
}
