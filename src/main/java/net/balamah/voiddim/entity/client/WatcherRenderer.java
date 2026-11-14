package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.WatcherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class WatcherRenderer
	extends MobRenderer<WatcherEntity,
							  WatcherRenderState,
							  WatcherModel>
{
	public WatcherRenderer(EntityRendererProvider.Context context) {
		super(context, new WatcherModel(context.bakeLayer(WatcherModel.WATCHER)), 0.5f);
	}

	@Override
	public WatcherRenderState createRenderState() {
		return new WatcherRenderState();
	}

	@Override
	public Identifier getTextureLocation(WatcherRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/watcher.png");
	}

	@Override
	public void extractRenderState(WatcherEntity entity,
								  WatcherRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.rageBaitAnimationState.copyFrom(entity.rageBaitAnimationState);
	}
}
