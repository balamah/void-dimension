package net.balamah.voiddim.custom;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public record NonLivingEntitySpecs<T extends Entity>(
	EntityType<T> entity,
	ModelLayerLocation modelLayer,
	LayerDefinition texturedModelData,
	EntityRendererProvider<T> entityRendererFactory
) {}
