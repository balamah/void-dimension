package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.GloomShepherdEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GloomShepherdRenderer extends MobRenderer<
    GloomShepherdEntity,
    GloomShepherdRenderState,
    GloomShepherdModel
> {
    public GloomShepherdRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new GloomShepherdModel(context.bakeLayer(GloomShepherdModel.LAYER_LOCATION)),
            0.5F
        );
    }

    @Override
    public GloomShepherdRenderState createRenderState() {
        return new GloomShepherdRenderState();
    }

    @Override
    public void extractRenderState(
        GloomShepherdEntity entity,
        GloomShepherdRenderState state,
        float partialTick
    ) {
        super.extractRenderState(entity, state, partialTick);

        state.walkAnimationState.copyFrom(entity.walkAnimationState);
        state.summonAnimationState.copyFrom(entity.summonAnimationState);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
    }

    @Override
    public Identifier getTextureLocation(GloomShepherdRenderState state) {
        return Identifier.fromNamespaceAndPath(
            VoidDimension.MOD_ID,
            "textures/entity/gloom_shepherd.png"
        );
    }
}
