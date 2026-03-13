package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class HerobrineModel
	<T extends HerobrineRenderState> extends BipedEntityModel<T>
{
	private final Animation lightningInvokeAnimation;
	private final Animation groundCorruptionAnimation;
	private final Animation shockwaveInvokeAnimation;

    public static final EntityModelLayer HEROBRINE =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "herobrine"), "main");

	public HerobrineModel(ModelPart root) {
		super(root);

		this.lightningInvokeAnimation = HerobrineAnimations.LIGHTNING_INVOKE.createAnimation(root);
		this.groundCorruptionAnimation = HerobrineAnimations.GROUND_CORRUPTION.createAnimation(root);
		this.shockwaveInvokeAnimation = HerobrineAnimations.SHOCKWAVE_INVOKE.createAnimation(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T state) {
		super.setAngles(state);

		this.lightningInvokeAnimation.apply(state.lightningInvokeAnimation, state.age, 1f);
		this.groundCorruptionAnimation.apply(state.groundCorruptionAnimation, state.age, 1f);
		this.shockwaveInvokeAnimation.apply(state.shockwaveInvokeAnimation, state.age, 1f);
	}
}
