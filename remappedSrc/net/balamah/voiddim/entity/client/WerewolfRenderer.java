package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.client.base.AbstractWerewolfRenderer;
import net.balamah.voiddim.entity.custom.WerewolfEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class WerewolfRenderer extends AbstractWerewolfRenderer<WerewolfEntity>
{
	public WerewolfRenderer(EntityRendererFactory.Context context) {
		super(context, WerewolfModel.WEREWOLF, "werewolf");

		this.addLayer(new GlowFeatureRenderer<>(this, "textures/entity/werewolf_eyes.png"));
	}
}
