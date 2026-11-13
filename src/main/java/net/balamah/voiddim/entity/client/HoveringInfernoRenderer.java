package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.HoveringInfernoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class HoveringInfernoRenderer extends MobRenderer<
        HoveringInfernoEntity,
        HoveringInfernoRenderState,
        HoveringInfernoModel
> {
    public HoveringInfernoRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new HoveringInfernoModel(context.bakeLayer(HoveringInfernoModel.HOVERING_INFERNO)),
            0.5F
        );
    }

    @Override
    public HoveringInfernoRenderState createRenderState() {
        return new HoveringInfernoRenderState();
    }

    @Override
    public Identifier getTextureLocation(HoveringInfernoRenderState state) {
        return Identifier.fromNamespaceAndPath(
            VoidDimension.MOD_ID,
            "textures/entity/hovering_inferno.png"
        );
    }

    @Override
    public void extractRenderState(
        HoveringInfernoEntity entity,
        HoveringInfernoRenderState renderState,
        float partialTick
    ) {
        super.extractRenderState(entity, renderState, partialTick);

        renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
        renderState.phase2TransitionAnimationState.copyFrom(entity.phase2TransitionAnimationState);
        renderState.phase3TransitionAnimationState.copyFrom(entity.phase3TransitionAnimationState);
        renderState.phase4TransitionAnimationState.copyFrom(entity.phase4TransitionAnimationState);
        renderState.phase5TransitionAnimationState.copyFrom(entity.phase5TransitionAnimationState);
        renderState.stunAnimationState.copyFrom(entity.stunAnimationState);
        renderState.recoverAnimationState.copyFrom(entity.recoverAnimationState);
    }
}
