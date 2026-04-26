package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;

public class VoidSphereRenderer <T extends AbstractWindCharge>
	extends EntityRenderer<T, EntityRenderState>
{
	private static final Identifier TEXTURE =
		Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/projectiles/void_sphere.png");

	private final VoidSphereModel model;

	public VoidSphereRenderer(EntityRendererProvider.Context context) {
		super(context);

		this.model =
			new VoidSphereModel(context.bakeLayer(VoidSphereModel.VOID_SPHERE));
	}

	@Override
	public void submit(
		EntityRenderState renderState, PoseStack matrices,
		SubmitNodeCollector queue, CameraRenderState cameraState
	) {
		queue.submitModel(
			this.model,
			renderState,
			matrices,
			RenderTypes.breezeWind(TEXTURE, this.getXOffset(renderState.ageInTicks) % 1.0F, 0.0F),
			renderState.lightCoords,
			OverlayTexture.NO_OVERLAY,
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
