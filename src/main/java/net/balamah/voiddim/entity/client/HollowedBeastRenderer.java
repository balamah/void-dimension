package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;

import net.balamah.voiddim.entity.client.base.AbstractWerewolfRenderer;
import net.balamah.voiddim.entity.custom.HollowedBeastEntity;

public class HollowedBeastRenderer extends AbstractWerewolfRenderer<HollowedBeastEntity>
{
	public HollowedBeastRenderer(EntityRendererFactory.Context context) {
		super(context, HollowedBeastModel.HOLLOWED_BEAST, "hollowed_beast");
	}
}

