package net.balamah.voiddim.entity.client.base;

import org.jetbrains.annotations.Nullable;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;

public abstract class ProjectileCustomModelRenderer
	<T extends ProjectileEntity, S extends EntityRenderState, M extends EntityModel<? super S>>
	extends EntityRenderer<T, S>
	implements FeatureRendererContext<S, M>
{
	protected final M model;
	protected final float shadow;

	public ProjectileCustomModelRenderer(Context context, M model, float shadow) {
		super(context);

		this.model = model;
		this.shadow = shadow;
	}

	@Override
	public M getModel() {
		return this.model;
	}

	@Override
	public abstract S createRenderState(); 

	public abstract ResourceLocation getTextureLocation(final S state);

	public void submit(final S state, final MatrixStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
		poseStack.push();

		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.translate(0.0F, -1.501F, 0.0F);
		boolean isBodyVisible = this.isBodyVisible(state);
		boolean forceTransparent = !isBodyVisible;
		RenderType renderType = this.getRenderType(state, isBodyVisible, forceTransparent, state.appearsGlowing());
		if (renderType != null) {
			int overlayCoords = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
			int baseColor = forceTransparent ? 654311423 : -1;
			int tintedColor = ARGB.multiply(baseColor, this.getModelTint(state));
			submitNodeCollector.submitModel(this.model, state, poseStack, renderType, state.lightCoords, overlayCoords, tintedColor, null, state.outlineColor, null);
		}

		if (this.shouldRenderLayers(state)) {
			this.model.setupAnim(state);
		}

		poseStack.pop();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	protected boolean isBodyVisible(final S state) {
		return !state.isInvisible;
	}

	protected boolean shouldRenderLayers(final S state) {
		return true;
	}

	protected float getWhiteOverlayProgress(final S state) {
		return 0.0F;
	}

	protected int getModelTint(final S state) {
		return -1;
	}

	public static int getOverlayCoords(
		final EntityRenderState state, final float whiteOverlayProgress
	) {
		return OverlayTexture.packUv(OverlayTexture.getU(whiteOverlayProgress), OverlayTexture.getV(true));
	}

	@Nullable
	protected RenderType getRenderType(final S state, final boolean isBodyVisible, final boolean forceTransparent, final boolean appearGlowing) {
		ResourceLocation texture = this.getTextureLocation(state);
		if (forceTransparent) {
			return RenderTypes.entityTranslucentCullItemTarget(texture);
		} else if (isBodyVisible) {
			return this.model.renderType(texture);
		} else {
			return appearGlowing ? RenderTypes.outline(texture) : null;
		}
	}
}
