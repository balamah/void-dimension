package net.balamah.voiddim.entity.client.renderFeature;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SpiderEyesFeatureRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.ResourceLocation;

public class CorruptedSpiderEyesFeatureRenderer<T extends SpiderModel>
	extends SpiderEyesFeatureRenderer<T>
{
	public CorruptedSpiderEyesFeatureRenderer(
		FeatureRendererContext<LivingEntityRenderState, T> featureRendererContext
	) {
		super(featureRendererContext);
	}

	@Override
	public RenderType renderType() {
		return RenderTypes.eyes(
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_spider_eyes.png")
		);
	}
}
