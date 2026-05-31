package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.CorruptedCreeperEntity;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.CreeperChargeFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.balamah.voiddim.VoidDimension;

public class CorruptedCreeperRenderer
	extends MobEntityRenderer<CorruptedCreeperEntity, CreeperRenderState, CreeperModel> 
{
	public CorruptedCreeperRenderer(EntityRendererFactory.Context context) {
		super(context, new CreeperModel(context.getPart(EntityModelLayers.CREEPER)), 0.5F);
		this.addLayer(new CreeperChargeFeatureRenderer(this, context.getModelLoader()));
	}

	protected void scale(
		CreeperRenderState creeperEntityRenderState, MatrixStack matrixStack
	) {
		float f = creeperEntityRenderState.swelling;
		float g = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float h = (1.0F + f * 0.4F) * g;
		float i = (1.0F + f * 0.1F) / g;
		matrixStack.scale(h, i, h);
	}

	protected float getAnimationCounter(CreeperRenderState creeperEntityRenderState) {
		float f = creeperEntityRenderState.swelling;
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(CreeperRenderState creeperEntityRenderState) {
		return ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/entity/corrupted_creeper.png"
		);
	}

	@Override
	public CreeperRenderState createRenderState() {
		return new CreeperRenderState();
	}

	@Override
	public void extractRenderState(
		CorruptedCreeperEntity creeperEntity,
		CreeperRenderState creeperEntityRenderState, float f
	) {
		super.extractRenderState(creeperEntity, creeperEntityRenderState, f);
		creeperEntityRenderState.swelling = creeperEntity.getClientFuseTime(f);
		creeperEntityRenderState.isPowered = creeperEntity.shouldRenderOverlay();
	}
}
