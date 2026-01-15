package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.CorruptedStalkerEntity;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.VoidDimension;

public class CorruptedStalkerRenderer
	extends MobEntityRenderer<CorruptedStalkerEntity, BasicRenderState,
								  CorruptedStalkerModel> 
{
	public CorruptedStalkerRenderer(EntityRendererFactory.Context context) {
		super(context, new CorruptedStalkerModel(context.getPart(CorruptedStalkerModel.CORRUPTED_STALKER)), 0.75f);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/corrupted_stalker_eye.png")
		);
	}

	@Override
	public BasicRenderState createRenderState() {
		return new BasicRenderState();
	}

	@Override
	public Identifier getTexture(BasicRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/corrupted_stalker.png");
	}
}
