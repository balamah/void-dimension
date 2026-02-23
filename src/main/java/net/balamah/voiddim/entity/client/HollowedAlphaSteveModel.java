package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class HollowedAlphaSteveModel
	<T extends BipedEntityRenderState> extends BipedEntityModel<T>
{
    public static final EntityModelLayer HOLLOWED_ALPHA_STEVE =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "hollowed_alpha_steve"), "main");

	private final Animation walkingAnimation;

	public HollowedAlphaSteveModel(ModelPart root) {
		super(root);

		this.walkingAnimation = HollowedAlphaSteveAnimations.WALK.createAnimation(root);
	}

	@Override
	public void setAngles(T state) {
		// If entity doesn't walk, don't apply modifications
		if (state.limbSwingAmplitude < 0.1F) {
			super.setAngles(state);
			return;
		}

		super.setAngles(state);

		this.walkingAnimation.applyWalking(
			state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2f, 2.5f
		);

		// The below code was taken from OldWalkingAnimation and modified
		// https://gitlab.com/TecnaGamer/OldWalkingAnimation/-/raw/main/src/main/java/tecna/oldwalkinganimation/mixin/BipedEntityModelMixin.java
		float limbSwing = state.limbSwingAnimationProgress;
		float limbSwingAmount = state.limbSwingAmplitude;

		float l = 1.0F;

		if (l < 1.0F) {
			l = 1.0F;
		}

		// Cancel vanilla scaling effect
		this.rightArm.pitch += -MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI)
			* 2.0F * limbSwingAmount * 0.5F / l;

		this.leftArm.pitch += -MathHelper.cos(limbSwing * 0.6662F)
			* 2.0F * limbSwingAmount * 0.5F / l;

		this.rightLeg.pitch += -MathHelper.cos(limbSwing * 0.6662F)
			* 1.4F * limbSwingAmount / l;

		this.leftLeg.pitch += -MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI)
			* 1.4F * limbSwingAmount / l;


		// Custom movement animation
		float var8 = limbSwing;
		float isMoving = limbSwingAmount;

		boolean tpose = true; // replace with config
		boolean arms = true;
		float tposeAngle = 1.0F;

		if (tpose) {
			if (arms) {
				this.leftArm.roll += -tposeAngle - (-tposeAngle * isMoving);
				this.rightArm.roll +=  tposeAngle - ( tposeAngle * isMoving);
			} else {
				this.leftArm.roll += -tposeAngle;
				this.rightArm.roll +=  tposeAngle;
			}
		}

		if (arms) {
			this.rightArm.roll += (MathHelper.cos(var8 * 0.2312F) + 1.0F) * isMoving;
			this.leftArm.roll  += (MathHelper.cos(var8 * 0.2812F) - 1.0F) * isMoving;
		}

		this.rightArm.pitch += MathHelper.cos(var8 * 0.6662F + (float)Math.PI)
				* 2.0F * isMoving;

		this.leftArm.pitch += MathHelper.cos(var8 * 0.6662F)
				* 2.0F * isMoving;

		this.rightLeg.pitch += MathHelper.cos(var8 * 0.6662F)
				* 1.4F * isMoving;

		this.leftLeg.pitch += MathHelper.cos(var8 * 0.6662F + (float)Math.PI)
				* 1.4F * isMoving;

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
