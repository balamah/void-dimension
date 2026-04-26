package net.balamah.voiddim.custom;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public record EntitySpecs<T extends LivingEntity>(
	EntityType<T> entity,
	ModelLayerLocation modelLayer,
	LayerDefinition texturedModelData,
	EntityRendererProvider<T> entityRendererFactory
) {}
