package net.balamah.voiddim.entity.client.base;

import net.balamah.voiddim.entity.custom.WerewolfEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public abstract class AbstractWerewolfRenderer<T extends WerewolfEntity>
	extends MobEntityRenderer<T,
							  AbstractWerewolfRenderState,
							  AbstractWerewolfModel<AbstractWerewolfRenderState>> 
{
	protected String texture;

	public AbstractWerewolfRenderer(EntityRendererFactory.Context context,
									EntityModelLayer modelLayer, String texture)
	{
		super(
			context,
			new AbstractWerewolfModel<AbstractWerewolfRenderState>(context.getPart(modelLayer)),
			0.75f
		);

		this.texture = texture;
	}

	@Override
	public AbstractWerewolfRenderState createRenderState() {
		return new AbstractWerewolfRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractWerewolfRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID,
							 String.format("textures/entity/%s.png", this.texture));
	}

	@Override
	public void submit(
		AbstractWerewolfRenderState state,
		MatrixStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.scale(1.2f, 1.2f, 1.2f);

		super.submit(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

	@Override
	public void extractRenderState(T entity, AbstractWerewolfRenderState renderState, float f) {
		super.extractRenderState(entity, renderState, f);

		renderState.attackHitAnimationState.copyFrom(entity.attackHitAnimationState);
		renderState.attackBiteAnimationState.copyFrom(entity.attackBiteAnimationState);
	}
}
