package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.client.base.BasicRenderState;

public class MashaModel extends EntityModel<BasicRenderState> {
    public static final ModelLayerLocation MASHA =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "masha"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart tongue;
	private final ModelPart ears;
	private final ModelPart right_ear;
	private final ModelPart left_ear;
	private final ModelPart hair;
	private final ModelPart hat;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart right_mitten;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart left_mitten;
	private final ModelPart chest;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_thigh;
	private final ModelPart right_calf;
	private final ModelPart right_boot;
	private final ModelPart left_leg;
	private final ModelPart left_thigh2;
	private final ModelPart left_calf3;
	private final ModelPart right_boot3;

	private final KeyframeAnimation walkAnimation;

	public MashaModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.tongue = this.head.getChild("tongue");
		this.ears = this.head.getChild("ears");
		this.right_ear = this.ears.getChild("right_ear");
		this.left_ear = this.ears.getChild("left_ear");
		this.hair = this.head.getChild("hair");
		this.hat = this.head.getChild("hat");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.right_mitten = this.right_elbow.getChild("right_mitten");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.left_mitten = this.left_elbow.getChild("left_mitten");
		this.chest = this.body.getChild("chest");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_thigh = this.right_leg.getChild("right_thigh");
		this.right_calf = this.right_leg.getChild("right_calf");
		this.right_boot = this.right_calf.getChild("right_boot");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_thigh2 = this.left_leg.getChild("left_thigh2");
		this.left_calf3 = this.left_leg.getChild("left_calf3");
		this.right_boot3 = this.left_calf3.getChild("right_boot3");

		this.walkAnimation = ShatteredSentinelMasterAnimations.WALK.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition root = meshDefinition.getRoot();

		PartDefinition rootPart = root.addOrReplaceChild(
				"root",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		PartDefinition body = rootPart.addOrReplaceChild(
				"body",
				CubeListBuilder.create()
						.texOffs(38, 38)
						.addBox(
								-5.5F, -2.0F, -2.5F,
								11.0F, 3.0F, 5.0F,
								CubeDeformation.NONE
						)
						.texOffs(38, 14)
						.addBox(
								-5.5F, -8.0F, -3.25F,
								11.0F, 6.0F, 6.0F,
								CubeDeformation.NONE
						)
						.texOffs(50, 55)
						.addBox(
								-4.0F, -17.0F, -2.75F,
								8.0F, 2.0F, 5.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, -16.5F, 0.75F)
		);

		PartDefinition head = body.addOrReplaceChild(
				"head",
				CubeListBuilder.create()
						.texOffs(0, 14)
						.addBox(
								-5.0F, -6.0F, -4.25F,
								10.0F, 6.0F, 9.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, -17.0F, -0.5F)
		);

		PartDefinition eyes = head.addOrReplaceChild(
				"eyes",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 31.0F, 0.0F)
		);

		eyes.addOrReplaceChild(
				"cube_r1",
				CubeListBuilder.create()
						.texOffs(34, 66)
						.addBox(
								-1.0F, -2.0F, -1.0F,
								2.0F, 2.0F, 2.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						4.5F, -33.0F, -3.75F,
						0.0F, -0.5236F, 0.0F
				)
		);

		eyes.addOrReplaceChild(
				"cube_r2",
				CubeListBuilder.create()
						.texOffs(28, 42)
						.addBox(
								-1.0F, -2.0F, -1.0F,
								2.0F, 2.0F, 2.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						-4.25F, -34.0F, -3.75F,
						0.0F, 0.3927F, 0.0F
				)
		);

		PartDefinition tongue = head.addOrReplaceChild(
				"tongue",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 31.0F, 0.0F)
		);

		tongue.addOrReplaceChild(
				"cube_r3",
				CubeListBuilder.create()
						.texOffs(74, 62)
						.addBox(
								-1.0F, -1.0F, -5.0F,
								3.0F, 1.0F, 4.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						-2.0F, -32.75F, -2.0F,
						0.6545F, 0.0F, 0.0F
				)
		);

		PartDefinition ears = head.addOrReplaceChild(
				"ears",
				CubeListBuilder.create(),
				PartPose.offset(4.75F, -12.5F, 4.0F)
		);

		ears.addOrReplaceChild(
				"right_ear",
				CubeListBuilder.create()
						.texOffs(64, 9)
						.addBox(
								-4.0F, 0.0F, -1.0F,
								4.0F, 1.0F, 4.0F,
								CubeDeformation.NONE
						)
						.texOffs(72, 19)
						.addBox(
								-6.0F, 0.0F, -1.5F,
								2.0F, 1.0F, 5.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						-9.75F, 6.5F, -4.75F,
						0.0F, 0.0F, -0.7418F
				)
		);

		ears.addOrReplaceChild(
				"left_ear",
				CubeListBuilder.create()
						.texOffs(72, 14)
						.addBox(
								0.0F, 0.0F, -1.0F,
								4.0F, 1.0F, 4.0F,
								CubeDeformation.NONE
						)
						.texOffs(34, 74)
						.addBox(
								4.0F, 0.0F, -1.5F,
								2.0F, 1.0F, 5.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						0.25F, 6.5F, -4.75F,
						0.0F, 0.0F, 0.7418F
				)
		);

		PartDefinition hair = head.addOrReplaceChild(
				"hair",
				CubeListBuilder.create()
						.texOffs(44, 62)
						.addBox(
								-6.0F, -1.0F, 0.0F,
								12.0F, 3.0F, 0.0F,
								CubeDeformation.NONE
						)
						.texOffs(34, 70)
						.addBox(
								2.0F, 2.0F, -0.25F,
								4.0F, 3.0F, 0.0F,
								CubeDeformation.NONE
						)
						.texOffs(74, 67)
						.addBox(
								-6.0F, 2.0F, -0.25F,
								4.0F, 3.0F, 0.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						0.0F, -5.0F, 5.5F,
						0.1745F, 0.0F, 0.0F
				)
		);

		hair.addOrReplaceChild(
				"cube_r4",
				CubeListBuilder.create()
						.texOffs(74, 73)
						.addBox(
								-6.0F, -2.0F, -1.0F,
								4.0F, 3.0F, 0.0F,
								new CubeDeformation(-0.001F)
						),
				PartPose.offsetAndRotation(
						-2.75F, 2.75F, 0.75F,
						0.0F, 0.0F, 0.1745F
				)
		);

		hair.addOrReplaceChild(
				"cube_r5",
				CubeListBuilder.create()
						.texOffs(74, 70)
						.addBox(
								2.0F, -2.0F, -1.0F,
								4.0F, 3.0F, 0.0F,
								new CubeDeformation(-0.001F)
						),
				PartPose.offsetAndRotation(
						2.75F, 2.75F, 0.75F,
						0.0F, 0.0F, -0.1745F
				)
		);

		PartDefinition hat = head.addOrReplaceChild(
				"hat",
				CubeListBuilder.create()
						.texOffs(0, 29)
						.addBox(
								-5.0F, -8.0F, -6.0F,
								10.0F, 4.0F, 9.0F,
								CubeDeformation.NONE
						)
						.texOffs(0, 0)
						.addBox(
								-5.5F, -4.0F, -6.5F,
								11.0F, 4.0F, 10.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, -6.0F, 1.75F)
		);

		hat.addOrReplaceChild(
				"cube_r6",
				CubeListBuilder.create()
						.texOffs(0, 67)
						.addBox(
								-3.0F, -3.0F, -2.0F,
								4.0F, 4.0F, 4.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						4.75F, -6.5F, 2.25F,
						-0.3491F, -0.3054F, 0.3491F
				)
		);

		PartDefinition arms = body.addOrReplaceChild(
				"arms",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, -15.0F, -1.0F)
		);

		PartDefinition rightArm = arms.addOrReplaceChild(
				"right_arm",
				CubeListBuilder.create()
						.texOffs(70, 36)
						.addBox(
								-1.0F, 0.0F, -2.0F,
								3.0F, 7.0F, 3.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						-7.0F, 0.0F, 1.0F,
						0.0F, 0.0F, 0.1309F
				)
		);

		PartDefinition rightElbow = rightArm.addOrReplaceChild(
				"right_elbow",
				CubeListBuilder.create()
						.texOffs(60, 65)
						.addBox(
								-1.0F, -0.5F, -2.25F,
								3.0F, 6.5F, 4.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, 7.5F, -0.25F)
		);

		rightElbow.addOrReplaceChild(
				"right_mitten",
				CubeListBuilder.create()
						.texOffs(48, 74)
						.addBox(
								-1.0F, 7.0F, -1.5F,
								1.0F, 5.0F, 4.0F,
								CubeDeformation.NONE
						)
						.texOffs(44, 58)
						.addBox(
								-1.0F, 7.0F, -3.5F,
								1.0F, 2.0F, 2.0F,
								CubeDeformation.NONE
						)
						.texOffs(24, 50)
						.addBox(
								-1.0F, 0.0F, -0.5F,
								1.0F, 7.0F, 1.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(1.0F, 6.0F, -0.25F)
		);

		PartDefinition leftArm = arms.addOrReplaceChild(
				"left_arm",
				CubeListBuilder.create()
						.texOffs(68, 26)
						.addBox(
								0.1327F, -0.025F, -2.0F,
								3.0F, 7.0F, 3.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						4.75F, 0.25F, 1.0F,
						0.0F, 0.0F, -0.1309F
				)
		);

		PartDefinition leftElbow = leftArm.addOrReplaceChild(
				"left_elbow",
				CubeListBuilder.create()
						.texOffs(20, 66)
						.addBox(
								-15.0F, -0.5F, -2.25F,
								3.0F, 6.5F, 4.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(15.1327F, 7.475F, -0.25F)
		);

		leftElbow.addOrReplaceChild(
				"left_mitten",
				CubeListBuilder.create()
						.texOffs(74, 46)
						.addBox(
								0.0F, 7.0F, -1.5F,
								1.0F, 5.0F, 4.0F,
								CubeDeformation.NONE
						)
						.texOffs(0, 75)
						.addBox(
								0.0F, 7.0F, -3.5F,
								1.0F, 2.0F, 2.0F,
								CubeDeformation.NONE
						)
						.texOffs(16, 67)
						.addBox(
								0.0F, 0.0F, -0.5F,
								1.0F, 7.0F, 1.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(-14.0F, 6.0F, -0.25F)
		);

		PartDefinition chest = body.addOrReplaceChild(
				"chest",
				CubeListBuilder.create()
						.texOffs(38, 26)
						.addBox(
								-5.0F, -13.0F, -4.0F,
								10.0F, 7.0F, 5.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, -2.0F, 1.25F)
		);

		chest.addOrReplaceChild(
				"tits_r1",
				CubeListBuilder.create()
						.texOffs(0, 42)
						.addBox(
								-5.0F, -3.0F, -3.0F,
								10.0F, 4.0F, 4.0F,
								new CubeDeformation(-0.001F)
						),
				PartPose.offsetAndRotation(
						0.0F, -7.0F, -4.0F,
						-0.7854F, 0.0F, 0.0F
				)
		);

		PartDefinition legs = rootPart.addOrReplaceChild(
				"legs",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, -15.0F, 0.0F)
		);

		PartDefinition rightLeg = legs.addOrReplaceChild(
				"right_leg",
				CubeListBuilder.create(),
				PartPose.offset(-4.0F, 0.0F, 0.0F)
		);

		PartDefinition rightThigh = rightLeg.addOrReplaceChild(
				"right_thigh",
				CubeListBuilder.create(),
				PartPose.offset(5.0F, 14.0F, 0.0F)
		);

		rightThigh.addOrReplaceChild(
				"cube_r7",
				CubeListBuilder.create()
						.texOffs(42, 0)
						.addBox(
								-3.0F, -7.0F, -2.0F,
								6.0F, 7.0F, 5.0F,
								new CubeDeformation(-0.001F)
						),
				PartPose.offsetAndRotation(
						-4.0F, -8.25F, 0.25F,
						0.0F, 0.0F, -0.2618F
				)
		);

		PartDefinition rightCalf = rightLeg.addOrReplaceChild(
				"right_calf",
				CubeListBuilder.create()
						.texOffs(64, 0)
						.addBox(
								-1.5F, -2.0F, -1.5F,
								4.0F, 5.0F, 4.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						0.5F, 7.0F, 0.0F,
						-0.0077F, -0.1744F, 0.0443F
				)
		);

		rightCalf.addOrReplaceChild(
				"right_boot",
				CubeListBuilder.create()
						.texOffs(0, 50)
						.addBox(
								-2.0F, 3.0F, -4.0F,
								5.0F, 2.0F, 7.0F,
								CubeDeformation.NONE
						)
						.texOffs(24, 58)
						.addBox(
								-2.0F, 0.0F, -2.0F,
								5.0F, 3.0F, 5.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, 3.0F, 0.0F)
		);

		PartDefinition leftLeg = legs.addOrReplaceChild(
				"left_leg",
				CubeListBuilder.create(),
				PartPose.offset(4.0F, 0.0F, 0.0F)
		);

		PartDefinition leftThigh = leftLeg.addOrReplaceChild(
				"left_thigh2",
				CubeListBuilder.create(),
				PartPose.offset(-5.0F, 14.0F, 0.0F)
		);

		leftThigh.addOrReplaceChild(
				"cube_r8",
				CubeListBuilder.create()
						.texOffs(28, 46)
						.addBox(
								-3.0F, -7.0F, -2.0F,
								6.0F, 7.0F, 5.0F,
								new CubeDeformation(-0.001F)
						),
				PartPose.offsetAndRotation(
						4.0F, -8.25F, 0.25F,
						0.0F, 0.0F, 0.2618F
				)
		);

		PartDefinition leftCalf = leftLeg.addOrReplaceChild(
				"left_calf3",
				CubeListBuilder.create()
						.texOffs(44, 65)
						.addBox(
								-2.5F, -2.0F, -1.5F,
								4.0F, 5.0F, 4.0F,
								CubeDeformation.NONE
						),
				PartPose.offsetAndRotation(
						-0.5F, 7.0F, 0.0F,
						-0.0115F, 0.1304F, -0.088F
				)
		);

		leftCalf.addOrReplaceChild(
				"right_boot3",
				CubeListBuilder.create()
						.texOffs(50, 46)
						.addBox(
								-3.0F, 3.0F, -4.0F,
								5.0F, 2.0F, 7.0F,
								CubeDeformation.NONE
						)
						.texOffs(0, 59)
						.addBox(
								-3.0F, 0.0F, -2.0F,
								5.0F, 3.0F, 5.0F,
								CubeDeformation.NONE
						),
				PartPose.offset(0.0F, 3.0F, 0.0F)
		);

		return LayerDefinition.create(meshDefinition, 128, 128);
	}

	@Override
	public void setupAnim(BasicRenderState state) {
		super.setupAnim(state);

		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);

		this.walkAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);
	}
}
