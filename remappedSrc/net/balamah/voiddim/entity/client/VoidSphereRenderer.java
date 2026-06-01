package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;

public class VoidSphereRenderer <T extends AbstractWindCharge>
	extends EntityRenderer<T, EntityRenderState>
{
	private static final ResourceLocation TEXTURE =
		ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/projectiles/void_sphere.png");

	private final VoidSphereModel model;

	public VoidSphereRenderer(EntityRendererFactory.Context context) {
		super(context);

		this.model =
			new VoidSphereModel(context.getPart(VoidSphereModel.VOID_SPHERE));
	}

	@Override
	public void submit(
		EntityRenderState renderState, MatrixStack matrices,
		SubmitNodeCollector queue, CameraRenderState cameraState
	) {
		queue.submitModel(
			this.model,
			renderState,
			matrices,
			RenderTypes.breezeWind(TEXTURE, this.getXOffset(renderState.ageInTicks) % 1.0F, 0.0F),
			renderState.lightCoords,
			OverlayTexture.DEFAULT_UV,
			renderState.outlineColor,
			null
		);

		super.submit(renderState, matrices, queue, cameraState);
	}

	protected float getXOffset(float tickDelta) {
		return tickDelta * 0.03F;
	}

	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}
}
