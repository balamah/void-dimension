package net.balamah.voiddim.entity.client.base;

import net.balamah.voiddim.entity.custom.WerewolfEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;

public abstract class AbstractWerewolfRenderer<T extends WerewolfEntity>
	extends MobRenderer<T,
							  AbstractWerewolfRenderState,
							  AbstractWerewolfModel<AbstractWerewolfRenderState>> 
{
	protected String texture;

	public AbstractWerewolfRenderer(EntityRendererProvider.Context context,
									ModelLayerLocation modelLayer, String texture)
	{
		super(
			context,
			new AbstractWerewolfModel<AbstractWerewolfRenderState>(context.bakeLayer(modelLayer)),
			0.75f
		);

		this.texture = texture;
	}

	@Override
	public AbstractWerewolfRenderState createRenderState() {
		return new AbstractWerewolfRenderState();
	}

	@Override
	public Identifier getTextureLocation(AbstractWerewolfRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID,
							 String.format("textures/entity/%s.png", this.texture));
	}

	@Override
	public void submit(
		AbstractWerewolfRenderState state,
		PoseStack matrixStack,
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
