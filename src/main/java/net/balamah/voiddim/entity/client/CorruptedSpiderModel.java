package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.resources.Identifier;

public class CorruptedSpiderModel extends SpiderModel {
    public static final ModelLayerLocation CORRUPTED_SPIDER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_spider"),
							 "main");

	public CorruptedSpiderModel(ModelPart modelPart) {
		super(modelPart);
	}
}
