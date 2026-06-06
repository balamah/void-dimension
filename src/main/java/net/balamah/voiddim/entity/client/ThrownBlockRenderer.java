package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.EntityRenderer;

import net.balamah.voiddim.entity.custom.ThrownBlockEntity;

public class ThrownBlockRenderer
	extends EntityRenderer<ThrownBlockEntity, ThrownBlockRenderState>
{
	public final BlockDisplayContext blockDisplayContext = BlockDisplayContext.create();

	protected final BlockModelResolver blockModelResolver;

	public ThrownBlockRenderer(Context context) {
		super(context);

		this.shadowRadius = 0.5F;
		this.blockModelResolver = context.getBlockModelResolver();
	}

	@Override
	public ThrownBlockRenderState createRenderState() {
		return new ThrownBlockRenderState();
	}

	@Override
	public void extractRenderState(
		ThrownBlockEntity entity, ThrownBlockRenderState state, float partialTicks
	) {
		super.extractRenderState(entity, state, partialTicks);

		this.blockModelResolver.update(
			state.blockRenderState,
			entity.getBlockState(),
			this.blockDisplayContext
		);
	}
}
