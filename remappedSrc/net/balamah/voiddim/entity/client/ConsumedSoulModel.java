package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class ConsumedSoulModel extends EntityModel<ConsumedSoulRenderState> {
	public static final EntityModelLayer CONSUMED_SOUL =
		new EntityModelLayer(
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "consumed_soul"),
			"main"
		);

	private final ModelPart root;
	private final ModelPart face;
	private final ModelPart jaw;
	private final ModelPart upper_jaw;
	private final ModelPart upper_jaw_teeth;
	private final ModelPart lower_jaw;
	private final ModelPart lower_jaw_teeth;

	public ConsumedSoulModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.face = this.root.getChild("face");
		this.jaw = this.root.getChild("jaw");
		this.upper_jaw = this.jaw.getChild("upper_jaw");
		this.upper_jaw_teeth = this.upper_jaw.getChild("upper_jaw_teeth");
		this.lower_jaw = this.jaw.getChild("lower_jaw");
		this.lower_jaw_teeth = this.lower_jaw.getChild("lower_jaw_teeth");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData mesh = new ModelData();
		ModelPartData root = mesh.getRoot();

		ModelPartData rootPart = root.addChild(
			"root",
			ModelPartBuilder.create(),
			ModelTransform.pivot(0.0F, 24.0F, 0.0F)
		);

		rootPart.addChild(
			"face",
			ModelPartBuilder.create()
				.uv(0, 24).cuboid(-4.0F, -3.0F, -3.0F, 8.0F, 1.0F, 5.0F, new Dilation(0.0F))
				.uv(0, 17).cuboid(-4.0F, -5.0F, -2.0F, 8.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(26, 15).cuboid(-4.0F, -5.0F, 3.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 30).cuboid(-3.0F, -4.0F, 3.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(30, 4).cuboid(-2.0F, -3.0F, 2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -7.0F, -3.0F, 8.0F, 2.0F, 7.0F, new Dilation(0.0F))
				.uv(0, 9).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 7.0F, new Dilation(0.0F))
				.uv(26, 17).cuboid(-4.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
				.uv(26, 23).cuboid(3.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
				.uv(18, 33).cuboid(-4.0F, -5.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(22, 33).cuboid(3.0F, -5.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 33).cuboid(-1.0F, -5.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)),
			ModelTransform.NONE
		);

		ModelPartData jaw = rootPart.addChild(
			"jaw",
			ModelPartBuilder.create(),
			ModelTransform.NONE
		);

		ModelPartData upperJaw = jaw.addChild(
			"upper_jaw",
			ModelPartBuilder.create()
				.uv(26, 9).cuboid(-2.0F, 1.0F, -5.0F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-3.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(6, 32).cuboid(2.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, -3.0F, 2.0F)
		);

		upperJaw.addChild(
			"upper_jaw_teeth",
			ModelPartBuilder.create()
				.uv(8, 35).cuboid(-1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
				.uv(36, 6).cuboid(1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F)),
			ModelTransform.NONE
		);

		ModelPartData lowerJaw = jaw.addChild(
			"lower_jaw",
			ModelPartBuilder.create()
				.uv(26, 29).cuboid(0.0F, -1.0F, -5.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(30, 0).cuboid(-2.0F, -1.0F, -4.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(14, 30).cuboid(-3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(26, 34).cuboid(-2.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(30, 6).cuboid(2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 0.0F, 2.0F)
		);

		lowerJaw.addChild(
			"cube_r1",
			ModelPartBuilder.create()
				.uv(20, 30).cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.of(-2.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F)
		);

		lowerJaw.addChild(
			"lower_jaw_teeth",
			ModelPartBuilder.create()
				.uv(30, 34).cuboid(-2.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
				.uv(34, 34).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
				.uv(0, 35).cuboid(-3.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
				.uv(4, 35).cuboid(2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F)),
			ModelTransform.pivot(0.0F, -2.0F, -2.0F)
		);

		return TexturedModelData.of(mesh, 64, 64);
	}
}
