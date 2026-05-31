package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.CorruptedCreeperEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;

public class CorruptedCreeperRenderer
	extends MobRenderer<CorruptedCreeperEntity, CreeperRenderState, CreeperModel> 
{
	public CorruptedCreeperRenderer(EntityRendererProvider.Context context) {
		super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
		this.addLayer(new CreeperPowerLayer(this, context.getModelSet()));
	}

	protected void scale(
		CreeperRenderState creeperEntityRenderState, PoseStack matrixStack
	) {
		float f = creeperEntityRenderState.swelling;
		float g = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float h = (1.0F + f * 0.4F) * g;
		float i = (1.0F + f * 0.1F) / g;
		matrixStack.scale(h, i, h);
	}

	protected float getAnimationCounter(CreeperRenderState creeperEntityRenderState) {
		float f = creeperEntityRenderState.swelling;
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
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
		creeperEntityRenderState.swelling = creeperEntity.getSwelling(f);
		creeperEntityRenderState.isPowered = creeperEntity.isPowered();
	}
}
