package net.balamah.voiddim.entity.client;

import java.util.function.Function;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.model.Model.SinglePartModel;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelData;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class SmallCorruptedFireballModel extends SinglePartModel {
    public static final EntityModelLayer SMALL_CORRUPTED_FIREBALL =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "small_corrupted_fireball"),
							 "main");

	public SmallCorruptedFireballModel(
		ModelPart part, Function<Identifier, RenderLayer> layerFactory
	) {
		super(part, layerFactory);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();
		root.addChild("root",
			ModelPartBuilder.create().cuboid(-2,-2,-2,4,4,4),
			ModelTransform.NONE);

		return TexturedModelData.of(data, 32, 32);
	}
}

