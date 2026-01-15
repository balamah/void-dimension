package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.CorruptedSpiderEyesFeatureRenderer;
import net.balamah.voiddim.entity.custom.CorruptedSpiderEntity;
import net.balamah.voiddim.VoidDimension;

public class CorruptedSpiderRenderer extends SpiderEntityRenderer<CorruptedSpiderEntity> {
	public CorruptedSpiderRenderer(Context context) {
		super(context, EntityModelLayers.SPIDER);

		this.addFeature(new CorruptedSpiderEyesFeatureRenderer<>(this));
	}

	@Override
	public Identifier getTexture(LivingEntityRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/corrupted_spider.png");
	}
}
