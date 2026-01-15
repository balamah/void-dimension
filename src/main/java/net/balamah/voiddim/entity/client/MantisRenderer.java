package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.entity.custom.MantisEntity;
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
	public Identifier getTexture(BasicRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/mantis.png");
	}

	@Override
	public void render(
		BasicRenderState state, MatrixStack matrixStack,
		OrderedRenderCommandQueue orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
        if (state.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

		super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}
}
