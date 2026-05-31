package net.balamah.voiddim.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class FallbackEntityRenderer<T extends Entity> extends EntityRenderer<T> {
	private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/misc/unknown_pack.png");

	public FallbackEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
}
