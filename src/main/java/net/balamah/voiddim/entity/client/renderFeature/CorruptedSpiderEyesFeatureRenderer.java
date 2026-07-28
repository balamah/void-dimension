package net.balamah.voiddim.entity.client.renderFeature;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class CorruptedSpiderEyesFeatureRenderer<T extends SpiderModel>
	extends SpiderEyesLayer<T>
{
	public CorruptedSpiderEyesFeatureRenderer(
		RenderLayerParent<LivingEntityRenderState, T> featureRendererContext
	) {
		super(featureRendererContext);
	}

	@Override
	public RenderType renderType() {
		return RenderTypes.eyes(
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_spider_eyes.png")
		);
	}
}
