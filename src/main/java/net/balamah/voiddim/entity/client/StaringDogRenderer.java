package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.WolfEntityRenderState;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.StaringDogEntity;
import net.balamah.voiddim.VoidDimension;

@SuppressWarnings("deprecation")
public class StaringDogRenderer
	extends AgeableMobEntityRenderer<StaringDogEntity, WolfEntityRenderState, WolfEntityModel>
{
	public StaringDogRenderer(EntityRendererFactory.Context context) {
		super(
			context, new WolfEntityModel(context.getPart(EntityModelLayers.WOLF)),
			new WolfEntityModel(context.getPart(EntityModelLayers.WOLF_BABY)), 0.5F
		);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/staring_dog_glow.png")
		);
	}

	public Identifier getTexture(WolfEntityRenderState dogRenderState) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/staring_dog.png");
	}

	public WolfEntityRenderState createRenderState() {
		return new WolfEntityRenderState();
	}

	public void updateRenderState(
		StaringDogEntity dog, WolfEntityRenderState dogRenderState, float f
	) {
		super.updateRenderState(dog, dogRenderState, f);

		dogRenderState.inSittingPose = dog.isInSittingPose();
	}
}
