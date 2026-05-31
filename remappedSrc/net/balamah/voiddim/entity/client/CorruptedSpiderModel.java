package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class CorruptedSpiderModel extends SpiderModel {
    public static final EntityModelLayer CORRUPTED_SPIDER =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_spider"),
							 "main");

	public CorruptedSpiderModel(ModelPart modelPart) {
		super(modelPart);
	}
}
