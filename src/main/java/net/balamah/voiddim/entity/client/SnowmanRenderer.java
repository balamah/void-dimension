package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.entity.custom.SnowmanEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;

public class SnowmanRenderer extends MobRenderer<SnowmanEntity, BasicRenderState, SnowmanModel> 
{
	public SnowmanRenderer(EntityRendererProvider.Context context) {
		super(context, new SnowmanModel(context.bakeLayer(SnowmanModel.SNOWMAN)), 0.75f);
	}

	@Override
	public BasicRenderState createRenderState() {
		return new BasicRenderState();
	}

	@Override
	public Identifier getTextureLocation(BasicRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/snowman.png");
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
