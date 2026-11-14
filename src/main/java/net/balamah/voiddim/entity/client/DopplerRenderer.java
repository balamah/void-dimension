package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import net.balamah.voiddim.entity.custom.DopplerEntity;
import net.balamah.voiddim.VoidDimension;

public class DopplerRenderer extends MobRenderer<DopplerEntity, DopplerRenderState, DopplerModel> {
    public DopplerRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new DopplerModel(context.bakeLayer(DopplerModel.DOPPLER)),
            0.5F
        );
    }

    @Override
    public DopplerRenderState createRenderState() {
        return new DopplerRenderState();
    }

    @Override
    public void extractRenderState(
        DopplerEntity entity,
        DopplerRenderState state,
        float partialTicks
    ) {
        super.extractRenderState(entity, state, partialTicks);

        state.transformingAnimationState.copyFrom(
            entity.transformingAnimationState
        );
    }

    @Override
    public Identifier getTextureLocation(DopplerRenderState state) {
		return Identifier.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/entity/doppler.png"
		);
    }
}
