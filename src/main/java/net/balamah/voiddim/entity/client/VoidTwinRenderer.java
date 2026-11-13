package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.VoidTwinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VoidTwinRenderer
	extends MobRenderer<VoidTwinEntity,
							  VoidTwinRenderState,
							  VoidTwinModel>
{
	public VoidTwinRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidTwinModel(context.bakeLayer(VoidTwinModel.VOID_TWIN)), 0.9f);
	}

	@Override
	public VoidTwinRenderState createRenderState() {
		return new VoidTwinRenderState();
	}

	@Override
	public Identifier getTextureLocation(VoidTwinRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/void_twin.png");
	}

	@Override
	public void extractRenderState(VoidTwinEntity entity,
								  VoidTwinRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.attack1AnimationState.copyFrom(entity.attack1AnimationState);
		renderState.attack2AnimationState.copyFrom(entity.attack2AnimationState);
		renderState.attack3AnimationState.copyFrom(entity.attack3AnimationState);
	}
}
