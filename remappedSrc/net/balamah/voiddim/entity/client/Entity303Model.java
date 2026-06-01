package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class Entity303Model
	<T extends Entity303RenderState> extends BipedEntityModel<T>
{
	private final KeyframeAnimation lightningInvokeAnimation;
	private final KeyframeAnimation groundCorruptionAnimation;
	private final KeyframeAnimation shockwaveInvokeAnimation;

    public static final EntityModelLayer HEROBRINE =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "herobrine"), "main");

	public Entity303Model(ModelPart root) {
		super(root);

		this.lightningInvokeAnimation = Entity303Animations.LIGHTNING_INVOKE.bake(root);
		this.groundCorruptionAnimation = Entity303Animations.GROUND_CORRUPTION.bake(root);
		this.shockwaveInvokeAnimation = Entity303Animations.SHOCKWAVE_INVOKE.bake(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setupAnim(T state) {
		super.setAngles(state);

		this.lightningInvokeAnimation.apply(state.lightningInvokeAnimation, state.ageInTicks, 1f);
		this.groundCorruptionAnimation.apply(state.groundCorruptionAnimation, state.ageInTicks, 1f);
		this.shockwaveInvokeAnimation.apply(state.shockwaveInvokeAnimation, state.ageInTicks, 1f);
	}
}
