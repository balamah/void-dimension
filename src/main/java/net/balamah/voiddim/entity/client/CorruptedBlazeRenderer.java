package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.CorruptedBlazeEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Blaze;
import net.balamah.voiddim.VoidDimension;

public class CorruptedBlazeRenderer
	extends MobRenderer<CorruptedBlazeEntity, LivingEntityRenderState, CorruptedBlazeModel> 
{
	public CorruptedBlazeRenderer(EntityRendererProvider.Context context) {
		super(context, new CorruptedBlazeModel(context.bakeLayer(ModelLayers.BLAZE)), 0.5F);
	}

	protected int getBlockLight(Blaze blazeEntity, BlockPos blockPos) {
		return 15;
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_blaze.png");
	}

	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
