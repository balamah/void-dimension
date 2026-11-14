package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.FacelessEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class FacelessRenderer extends MobRenderer<FacelessEntity, FacelessRenderState, FacelessModel> {
    public FacelessRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new FacelessModel(context.bakeLayer(FacelessModel.FACELESS)),
            0.5F
        );
    }

    @Override
    public FacelessRenderState createRenderState() {
        return new FacelessRenderState();
    }

	@Override
	public void submit(
		FacelessRenderState state,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		float scale = 1.1f;
		matrixStack.scale(scale, scale, scale);

		super.submit(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

    @Override
    public void extractRenderState(
        FacelessEntity entity,
        FacelessRenderState state,
        float partialTick
    ) {
        super.extractRenderState(entity, state, partialTick);

        state.walkAnimationState.copyFrom(entity.walkAnimationState);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.sufferingAnimationState.copyFrom(entity.sufferingAnimationState);
    }

    @Override
    public Identifier getTextureLocation(FacelessRenderState state) {
        return Identifier.fromNamespaceAndPath(
            VoidDimension.MOD_ID,
            "textures/entity/faceless.png"
        );
    }
}
