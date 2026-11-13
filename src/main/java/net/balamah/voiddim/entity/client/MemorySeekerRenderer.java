package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.MemorySeekerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MemorySeekerRenderer extends MobRenderer<
        MemorySeekerEntity,
        MemorySeekerRenderState,
        MemorySeekerModel
> {
    public MemorySeekerRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new MemorySeekerModel(
                        context.bakeLayer(MemorySeekerModel.MEMORY_SEEKER)
                ),
                0.5F
        );
    }

    @Override
    public MemorySeekerRenderState createRenderState() {
        return new MemorySeekerRenderState();
    }

    @Override
    public Identifier getTextureLocation(MemorySeekerRenderState state) {
        return Identifier.fromNamespaceAndPath(
                VoidDimension.MOD_ID,
                "textures/entity/memory_seeker.png"
        );
    }

    @Override
    public void extractRenderState(
            MemorySeekerEntity entity,
            MemorySeekerRenderState renderState,
            float partialTick
    ) {
        super.extractRenderState(entity, renderState, partialTick);

        renderState.walkAnimationState.copyFrom(entity.walkAnimationState);
        renderState.attack1AnimationState.copyFrom(entity.attack1AnimationState);
        renderState.attack2AnimationState.copyFrom(entity.attack2AnimationState);
    }
}
