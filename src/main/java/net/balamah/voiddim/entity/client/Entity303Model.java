package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.Identifier;

public class Entity303Model
	<T extends Entity303RenderState> extends HumanoidModel<T>
{
	private final KeyframeAnimation lightningInvokeAnimation;
	private final KeyframeAnimation groundCorruptionAnimation;
	private final KeyframeAnimation shockwaveInvokeAnimation;

    public static final ModelLayerLocation HEROBRINE =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "herobrine"), "main");

	public Entity303Model(ModelPart root) {
		super(root);

		this.lightningInvokeAnimation = Entity303Animations.LIGHTNING_INVOKE.bake(root);
		this.groundCorruptionAnimation = Entity303Animations.GROUND_CORRUPTION.bake(root);
		this.shockwaveInvokeAnimation = Entity303Animations.SHOCKWAVE_INVOKE.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);

		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(T state) {
		super.setupAnim(state);

		this.lightningInvokeAnimation.apply(state.lightningInvokeAnimation, state.ageInTicks, 1f);
		this.groundCorruptionAnimation.apply(state.groundCorruptionAnimation, state.ageInTicks, 1f);
		this.shockwaveInvokeAnimation.apply(state.shockwaveInvokeAnimation, state.ageInTicks, 1f);
	}
}
