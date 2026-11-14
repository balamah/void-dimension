package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.FacelessEntity;
import net.balamah.voiddim.VoidDimension;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
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
