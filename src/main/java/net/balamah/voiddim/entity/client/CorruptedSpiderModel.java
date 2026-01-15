package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class CorruptedSpiderModel extends SpiderEntityModel {
    public static final EntityModelLayer CORRUPTED_SPIDER =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "corrupted_spider"),
							 "main");

	public CorruptedSpiderModel(ModelPart modelPart) {
		super(modelPart);
	}
}
