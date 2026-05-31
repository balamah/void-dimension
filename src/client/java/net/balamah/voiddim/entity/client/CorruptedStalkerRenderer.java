package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.CorruptedStalkerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.VoidDimension;

public class CorruptedStalkerRenderer
	extends MobRenderer<CorruptedStalkerEntity, BasicRenderState,
								  CorruptedStalkerModel> 
{
	public CorruptedStalkerRenderer(EntityRendererProvider.Context context) {
		super(context, new CorruptedStalkerModel(context.bakeLayer(CorruptedStalkerModel.CORRUPTED_STALKER)), 0.75f);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/corrupted_stalker_eye.png")
		);
	}

	@Override
	public BasicRenderState createRenderState() {
		return new BasicRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(BasicRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_stalker.png");
	}
}
