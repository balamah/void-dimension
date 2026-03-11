package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer;
import net.minecraft.client.render.entity.state.CatEntityRenderState;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.StaringCatEntity;
import net.balamah.voiddim.VoidDimension;

/*
 * I didn't extend CatEntityRenderer due to its generics,
 * which are <CatEntity, CatEntityRenderState, CatEntityModel>.
 * StaringCatEntity extends StaringEntity, not the CatEntity.
 * Therefore i copied CatEntityRenderer code
 */
@SuppressWarnings("deprecation")
public class StaringCatRenderer extends AgeableMobEntityRenderer<StaringCatEntity, CatEntityRenderState, CatEntityModel> {
	public StaringCatRenderer(EntityRendererFactory.Context context) {
		super(
			context, new CatEntityModel(context.getPart(EntityModelLayers.CAT)),
			new CatEntityModel(context.getPart(EntityModelLayers.CAT_BABY)), 0.4F
		);

		this.addFeature(new CatCollarFeatureRenderer(this, context.getEntityModels()));

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/staring_cat_glow.png")
		);
	}

	public Identifier getTexture(CatEntityRenderState catEntityRenderState) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/staring_cat.png");
	}

	public CatEntityRenderState createRenderState() {
		return new CatEntityRenderState();
	}

	public void updateRenderState(StaringCatEntity catEntity, CatEntityRenderState catEntityRenderState, float f) {
		super.updateRenderState(catEntity, catEntityRenderState, f);

		catEntityRenderState.inSittingPose = catEntity.isInSittingPose();
	}

	protected void setupTransforms(CatEntityRenderState catEntityRenderState, MatrixStack matrixStack, float f, float g) {
		super.setupTransforms(catEntityRenderState, matrixStack, f, g);
		float h = catEntityRenderState.sleepAnimationProgress;
		if (h > 0.0F) {
			matrixStack.translate(0.4F * h, 0.15F * h, 0.1F * h);
			matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerpAngleDegrees(h, 0.0F, 90.0F)));
			if (catEntityRenderState.nearSleepingPlayer) {
				matrixStack.translate(0.15F * h, 0.0F, 0.0F);
			}
		}
	}
}
