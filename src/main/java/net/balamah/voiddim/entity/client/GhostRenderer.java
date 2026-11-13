package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.GhostEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GhostRenderer extends MobRenderer<GhostEntity, GhostRenderState, GhostModel> {
    public GhostRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new GhostModel(context.bakeLayer(GhostModel.LAYER_LOCATION)),
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
    public Identifier getTextureLocation(GhostRenderState state) {
        return Identifier.fromNamespaceAndPath(
            VoidDimension.MOD_ID,
            "textures/entity/ghost.png"
        );
    }
}
