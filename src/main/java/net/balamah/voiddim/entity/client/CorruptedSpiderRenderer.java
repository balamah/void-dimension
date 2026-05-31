package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.CorruptedSpiderEyesFeatureRenderer;
import net.balamah.voiddim.entity.custom.CorruptedSpiderEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class CorruptedSpiderRenderer extends SpiderRenderer<CorruptedSpiderEntity> {
	public CorruptedSpiderRenderer(Context context) {
		super(context, ModelLayers.SPIDER);

		this.addLayer(new CorruptedSpiderEyesFeatureRenderer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_spider.png");
	}
}
