package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.ObsidianGluttonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ObsidianGluttonRenderer
	extends MobRenderer<ObsidianGluttonEntity,
							  ObsidianGluttonRenderState,
							  ObsidianGluttonModel>
{
	public ObsidianGluttonRenderer(EntityRendererProvider.Context context) {
		super(context, new ObsidianGluttonModel(context.bakeLayer(ObsidianGluttonModel.OBSIDIAN_GLUTTON)), 1.0f);
	}

	@Override
	public ObsidianGluttonRenderState createRenderState() {
		return new ObsidianGluttonRenderState();
	}

	@Override
	public Identifier getTextureLocation(ObsidianGluttonRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/obsidian_glutton.png");
	}

	@Override
	public void extractRenderState(ObsidianGluttonEntity entity,
								  ObsidianGluttonRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
		renderState.attack1AnimationState.copyFrom(entity.attack1AnimationState);
		renderState.attack2AnimationState.copyFrom(entity.attack2AnimationState);
	}
}
