package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.entity.custom.MantisEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;

public class MantisRenderer extends MobRenderer<MantisEntity, BasicRenderState, MantisModel> 
{
	public MantisRenderer(EntityRendererProvider.Context context) {
		super(context, new MantisModel(context.bakeLayer(MantisModel.MANTIS)), 0.75f);
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
		PoseStack matrixStack,
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
