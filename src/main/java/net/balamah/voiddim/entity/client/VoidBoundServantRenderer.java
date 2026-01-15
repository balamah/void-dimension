package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.VoidBoundServantEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.util.Identifier;

public class VoidBoundServantRenderer
	extends MobEntityRenderer<VoidBoundServantEntity,
							  VoidBoundServantRenderState,
							  VoidBoundServantModel>
{
	public VoidBoundServantRenderer(EntityRendererFactory.Context context) {
		super(context, new VoidBoundServantModel(context.getPart(VoidBoundServantModel.VOID_BOUND_SERVANT)), 0.75f);

		this.addFeature(
			new HeldItemFeatureRenderer<VoidBoundServantRenderState, VoidBoundServantModel>(this)
		);
	}

	@Override
	public VoidBoundServantRenderState createRenderState() {
		return new VoidBoundServantRenderState();
	}

	@Override
	public Identifier getTexture(VoidBoundServantRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/void_bound_servant.png");
	}

	@Override
	public void updateRenderState(
		VoidBoundServantEntity entity, VoidBoundServantRenderState renderState, float f
	) {
		super.updateRenderState(entity, renderState, f);

		renderState.suicideAnimationState.copyFrom(entity.suicideAnimationState);

		// NOTE: This is armed entity. Line below should be fixed somehow
		ArmedEntityRenderState.updateRenderState(entity, renderState, this.itemModelResolver, 2f);
	}
}
