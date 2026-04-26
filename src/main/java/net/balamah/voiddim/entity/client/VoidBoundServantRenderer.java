package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.VoidBoundServantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;

public class VoidBoundServantRenderer
	extends MobRenderer<VoidBoundServantEntity,
							  VoidBoundServantRenderState,
							  VoidBoundServantModel>
{
	public VoidBoundServantRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidBoundServantModel(context.bakeLayer(VoidBoundServantModel.VOID_BOUND_SERVANT)), 0.75f);

		this.addLayer(
			new ItemInHandLayer<VoidBoundServantRenderState, VoidBoundServantModel>(this)
		);
	}

	@Override
	public VoidBoundServantRenderState createRenderState() {
		return new VoidBoundServantRenderState();
	}

	@Override
	public Identifier getTextureLocation(VoidBoundServantRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/void_bound_servant.png");
	}

	@Override
	public void extractRenderState(
		VoidBoundServantEntity entity, VoidBoundServantRenderState renderState, float f
	) {
		super.extractRenderState(entity, renderState, f);

		renderState.suicideAnimationState.copyFrom(entity.suicideAnimationState);

		// NOTE: This is armed entity. Line below should be fixed somehow
		ArmedEntityRenderState.extractArmedEntityRenderState(entity, renderState, this.itemModelResolver, 2f);
	}
}
