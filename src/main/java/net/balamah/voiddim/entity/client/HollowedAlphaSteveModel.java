package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class HollowedAlphaSteveModel
	<T extends HumanoidRenderState> extends HumanoidModel<T>
{
    public static final ModelLayerLocation HOLLOWED_ALPHA_STEVE =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "hollowed_alpha_steve"), "main");

    public static final ModelLayerLocation ZOMBIFIED_ALPHA_STEVE =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "zombified_alpha_steve"), "main");

    public static final ModelLayerLocation NULL =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "null_entity"), "main");

	private final KeyframeAnimation walkingAnimation;

	public HollowedAlphaSteveModel(ModelPart root) {
		super(root);

		this.walkingAnimation = AlphaSteveAnimations.WALK.bake(root);
	}

	@Override
	public void setupAnim(T state) {
		// If entity doesn't walk, don't apply modifications
		if (state.walkAnimationSpeed < 0.1F) {
			super.setupAnim(state);
			return;
		}

		super.setupAnim(state);

		this.walkingAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		// The below code was taken from OldWalkingAnimation and modified
		// https://gitlab.com/TecnaGamer/OldWalkingAnimation/-/raw/main/src/main/java/tecna/oldwalkinganimation/mixin/BipedEntityModelMixin.java
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;

		float l = 1.0F;

		if (l < 1.0F) {
			l = 1.0F;
		}

		// Cancel vanilla scaling effect
		this.rightArm.xRot += -Mth.cos(limbSwing * 0.6662F + (float)Math.PI)
			* 2.0F * limbSwingAmount * 0.5F / l;

		this.leftArm.xRot += -Mth.cos(limbSwing * 0.6662F)
			* 2.0F * limbSwingAmount * 0.5F / l;

		this.rightLeg.xRot += -Mth.cos(limbSwing * 0.6662F)
			* 1.4F * limbSwingAmount / l;

		this.leftLeg.xRot += -Mth.cos(limbSwing * 0.6662F + (float)Math.PI)
			* 1.4F * limbSwingAmount / l;


		// Custom movement animation
		float var8 = limbSwing;
		float isMoving = limbSwingAmount;

		boolean tpose = true; // replace with config
		boolean arms = true;
		float tposeAngle = 1.0F;

		if (tpose) {
			if (arms) {
				this.leftArm.zRot += -tposeAngle - (-tposeAngle * isMoving);
				this.rightArm.zRot +=  tposeAngle - ( tposeAngle * isMoving);
			} else {
				this.leftArm.zRot += -tposeAngle;
				this.rightArm.zRot +=  tposeAngle;
			}
		}

		if (arms) {
			this.rightArm.zRot += (Mth.cos(var8 * 0.2312F) + 1.0F) * isMoving;
			this.leftArm.zRot  += (Mth.cos(var8 * 0.2812F) - 1.0F) * isMoving;
		}

		this.rightArm.xRot += Mth.cos(var8 * 0.6662F + (float)Math.PI)
				* 2.0F * isMoving;

		this.leftArm.xRot += Mth.cos(var8 * 0.6662F)
				* 2.0F * isMoving;

		this.rightLeg.xRot += Mth.cos(var8 * 0.6662F)
				* 1.4F * isMoving;

		this.leftLeg.xRot += Mth.cos(var8 * 0.6662F + (float)Math.PI)
				* 1.4F * isMoving;

	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);

		return LayerDefinition.create(modelData, 64, 64);
	}
}
