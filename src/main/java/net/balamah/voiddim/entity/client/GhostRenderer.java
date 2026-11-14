package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.GhostEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class GhostRenderer extends MobRenderer<GhostEntity, GhostRenderState, GhostModel> {
    public GhostRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new GhostModel(context.bakeLayer(GhostModel.GHOST)),
            0.5F
        );
    }

    @Override
    public GhostRenderState createRenderState() {
        return new GhostRenderState();
    }

    @Override
    public void extractRenderState(
        GhostEntity entity,
        GhostRenderState state,
        float partialTick
    ) {
        super.extractRenderState(entity, state, partialTick);

        state.walkAnimationState.copyFrom(entity.walkAnimationState);
    }

	@Override
	public void submit(
		GhostRenderState state,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		float scale = 1.4f;
		matrixStack.scale(scale, scale, scale);

		super.submit(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

    @Override
    public Identifier getTextureLocation(GhostRenderState state) {
        return Identifier.fromNamespaceAndPath(
            VoidDimension.MOD_ID,
            "textures/entity/ghost.png"
        );
    }
}
