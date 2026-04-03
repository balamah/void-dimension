package net.balamah.voiddim.custom;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;

public record EntitySpecs<T extends LivingEntity>(
	EntityType<T> entity,
	EntityModelLayer modelLayer,
	TexturedModelData texturedModelData,
	EntityRendererFactory<T> entityRendererFactory
) {}
