package net.balamah.voiddim.custom;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public record NonLivingEntitySpecs<T extends Entity>(
	EntityType<T> entity,
	EntityModelLayer modelLayer,
	TexturedModelData texturedModelData,
	EntityRendererFactory<T> entityRendererFactory
) {}
