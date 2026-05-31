package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.AbstractWerewolfRenderer;
import net.balamah.voiddim.entity.custom.HollowedBeastEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HollowedBeastRenderer extends AbstractWerewolfRenderer<HollowedBeastEntity>
{
	public HollowedBeastRenderer(EntityRendererProvider.Context context) {
		super(context, HollowedBeastModel.HOLLOWED_BEAST, "hollowed_beast");
	}
}

