package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.entity.client.base.BasicAnimations;
import net.balamah.voiddim.VoidDimension;
	
public class CorruptedStalkerModel extends EntityModel<BasicRenderState> {
    public static final EntityModelLayer CORRUPTED_STALKER =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_stalker"),
							 "main");

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart body;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	private final KeyframeAnimation walkingAnimation;

	public CorruptedStalkerModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.arms = this.root.getChild("arms");
		this.left_arm = this.arms.getChild("left_arm");
		this.right_arm = this.arms.getChild("right_arm");
		this.body = this.root.getChild("body");
		this.legs = this.root.getChild("legs");
		this.left_leg = this.legs.getChild("left_leg");
		this.right_leg = this.legs.getChild("right_leg");

		this.walkingAnimation = BasicAnimations.WALK.bake(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData head = root.addChild("head",
			ModelPartBuilder.create().uv(0, 22).cuboid(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, -38.0F, 0.0F));

		ModelPartData arms = root.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 28.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -38.0F, -4.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(28, 30).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 28.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -38.0F, 4.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -38.0F, -4.0F, 4.0F, 14.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -24.0F, 1.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(36, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

    @Override
    public void setupAnim(BasicRenderState state) {
        super.setAngles(state);

		this.setHeadAngles(state.yRot, state.xRot);

		this.walkingAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);
    }

	protected void setHeadAngles(float headYaw, float headPitch) {
		// convert to radians
		float yawRad   = headYaw * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		// clamp pitch
		pitchRad = MathHelper.clamp(pitchRad,
									-25F * ((float)Math.PI / 180F),
									45F * ((float)Math.PI / 180F));

		// apply correction so head faces forward instead of sideways
		this.head.yaw   = yawRad - (float)Math.PI / 2F;  // subtract 90°
		this.head.pitch = pitchRad;
	}
}
