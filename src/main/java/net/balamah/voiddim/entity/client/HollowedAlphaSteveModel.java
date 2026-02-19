package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
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
		super.setAngles(state);

		this.walkingAnimation.applyWalking(
			state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2f, 2.5f
		);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
