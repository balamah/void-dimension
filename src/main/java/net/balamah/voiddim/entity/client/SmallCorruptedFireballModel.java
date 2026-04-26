package net.balamah.voiddim.entity.client;

import java.util.function.Function;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;

public class SmallCorruptedFireballModel extends EntityModel<EntityRenderState> {
    public static final ModelLayerLocation SMALL_CORRUPTED_FIREBALL =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "small_corrupted_fireball"),
							 "main");

	public SmallCorruptedFireballModel(
		ModelPart part, Function<Identifier, RenderType> layerFactory
	) {
		super(part, layerFactory);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition data = new MeshDefinition();
		PartDefinition root = data.getRoot();
		root.addOrReplaceChild("root",
			CubeListBuilder.create().addBox(-2,-2,-2,4,4,4),
			PartPose.ZERO);

		return LayerDefinition.create(data, 32, 32);
	}
}
