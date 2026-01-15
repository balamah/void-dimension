package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.EvokerFangsEntityRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.EvokerFangsEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class DarkGraspRenderer extends EvokerFangsEntityRenderer {
	public DarkGraspRenderer(Context context) {
		super(context);
	}
	
	public Identifier getTexture(EvokerFangsEntityRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/dark_grasp.png");
	}
}
