package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.feature.CreeperChargeFeatureRenderer;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.custom.CorruptedCreeperEntity;
import net.balamah.voiddim.VoidDimension;

public class CorruptedCreeperRenderer
	extends MobEntityRenderer<CorruptedCreeperEntity, CreeperEntityRenderState, CreeperEntityModel> 
{
	public CorruptedCreeperRenderer(EntityRendererFactory.Context context) {
		super(context, new CreeperEntityModel(context.getPart(EntityModelLayers.CREEPER)), 0.5F);
		this.addFeature(new CreeperChargeFeatureRenderer(this, context.getEntityModels()));
	}

	protected void scale(
		CreeperEntityRenderState creeperEntityRenderState, MatrixStack matrixStack
	) {
		float f = creeperEntityRenderState.fuseTime;
		float g = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float h = (1.0F + f * 0.4F) * g;
		float i = (1.0F + f * 0.1F) / g;
		matrixStack.scale(h, i, h);
	}

	protected float getAnimationCounter(CreeperEntityRenderState creeperEntityRenderState) {
		float f = creeperEntityRenderState.fuseTime;
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}

	public Identifier getTexture(CreeperEntityRenderState creeperEntityRenderState) {
		return Identifier.of(
			VoidDimension.MOD_ID, "textures/entity/corrupted_creeper.png"
		);
	}

	public CreeperEntityRenderState createRenderState() {
		return new CreeperEntityRenderState();
	}

	public void updateRenderState(
		CorruptedCreeperEntity creeperEntity,
		CreeperEntityRenderState creeperEntityRenderState, float f
	) {
		super.updateRenderState(creeperEntity, creeperEntityRenderState, f);
		creeperEntityRenderState.fuseTime = creeperEntity.getLerpedFuseTime(f);
		creeperEntityRenderState.charged = creeperEntity.isCharged();
	}
}
