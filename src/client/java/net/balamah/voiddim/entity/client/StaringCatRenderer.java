package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.StaringCatEntity;
import net.minecraft.client.model.animal.feline.AbstractFelineModel;
import net.minecraft.client.model.animal.feline.AdultCatModel;
import net.minecraft.client.model.animal.feline.BabyCatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CatCollarLayer;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.balamah.voiddim.VoidDimension;

/*
 * I didn't extend CatEntityRenderer due to its generics,
 * which are <CatEntity, CatEntityRenderState, CatEntityModel>.
 * StaringCatEntity extends StaringEntity, not the CatEntity.
 * Therefore i copied CatEntityRenderer code
 */
@SuppressWarnings("deprecation")
public class StaringCatRenderer
	extends AgeableMobRenderer<StaringCatEntity, CatRenderState, AbstractFelineModel<CatRenderState>>
{
	public StaringCatRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new AdultCatModel(context.bakeLayer(ModelLayers.CAT)),
			new BabyCatModel(context.bakeLayer(ModelLayers.CAT_BABY)),
			0.4F
		);

		this.addLayer(new CatCollarLayer(this, context.getModelSet()));

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/staring_cat.png")
		);
	}

	@Override
	public ResourceLocation getTextureLocation(CatRenderState catEntityRenderState) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/staring_cat.png");
	}

	@Override
	public CatRenderState createRenderState() {
		return new CatRenderState();
	}

	@Override
	public void extractRenderState(StaringCatEntity catEntity, CatRenderState catEntityRenderState, float f) {
		super.extractRenderState(catEntity, catEntityRenderState, f);

		catEntityRenderState.isSitting = catEntity.isInSittingPose();
	}

	@Override
	protected void setupRotations(CatRenderState catEntityRenderState, PoseStack matrixStack, float f, float g) {
		super.setupRotations(catEntityRenderState, matrixStack, f, g);
		float h = catEntityRenderState.lieDownAmount;
		if (h > 0.0F) {
			matrixStack.translate(0.4F * h, 0.15F * h, 0.1F * h);
			matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(h, 0.0F, 90.0F)));
			if (catEntityRenderState.isLyingOnTopOfSleepingPlayer) {
				matrixStack.translate(0.15F * h, 0.0F, 0.0F);
			}
		}
	}
}
