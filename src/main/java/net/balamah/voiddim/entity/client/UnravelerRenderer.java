package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.UnravelerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class UnravelerRenderer
	extends MobRenderer<UnravelerEntity,
							  UnravelerRenderState,
							  UnravelerModel>
{
	public UnravelerRenderer(EntityRendererProvider.Context context) {
		super(context, new UnravelerModel(context.bakeLayer(UnravelerModel.UNRAVELER)), 1.0f);
	}

	@Override
	public UnravelerRenderState createRenderState() {
		return new UnravelerRenderState();
	}

	@Override
	public Identifier getTextureLocation(UnravelerRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/unraveler.png");
	}

	@Override
	public void extractRenderState(UnravelerEntity entity,
								  UnravelerRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.terrainShift1AnimationState.copyFrom(entity.terrainShift1AnimationState);
		renderState.terrainShift2AnimationState.copyFrom(entity.terrainShift2AnimationState);
		renderState.attack1AnimationState.copyFrom(entity.attack1AnimationState);
		renderState.attack2AnimationState.copyFrom(entity.attack2AnimationState);
		renderState.attack3AnimationState.copyFrom(entity.attack3AnimationState);
		renderState.attack4AnimationState.copyFrom(entity.attack4AnimationState);
	}
}
