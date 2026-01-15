package net.balamah.voiddim.entity.client.renderFeature;

import net.minecraft.client.render.entity.feature.SpiderEyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class CorruptedSpiderEyesFeatureRenderer<T extends SpiderEntityModel>
	extends SpiderEyesFeatureRenderer<T>
{
	public CorruptedSpiderEyesFeatureRenderer(
		FeatureRendererContext<LivingEntityRenderState, T> featureRendererContext
	) {
		super(featureRendererContext);
	}

	@Override
	public RenderLayer getEyesTexture() {
		return RenderLayers.eyes(
			Identifier.of(VoidDimension.MOD_ID, "textures/entity/corrupted_spider_eyes.png")
		);
	}
}
