package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.CorruptedBlazeEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.balamah.voiddim.VoidDimension;

public class CorruptedBlazeRenderer
	extends MobEntityRenderer<CorruptedBlazeEntity, LivingEntityRenderState, CorruptedBlazeModel> 
{
	public CorruptedBlazeRenderer(EntityRendererFactory.Context context) {
		super(context, new CorruptedBlazeModel(context.getPart(EntityModelLayers.BLAZE)), 0.5F);
	}

	protected int getBlockLight(BlazeEntity blazeEntity, BlockPos blockPos) {
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_blaze.png");
	}

	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
