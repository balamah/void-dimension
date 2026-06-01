package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.StaringDogEntity;
import net.minecraft.client.model.animal.wolf.AdultWolfModel;
import net.minecraft.client.model.animal.wolf.BabyWolfModel;
import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

@SuppressWarnings("deprecation")
public class StaringDogRenderer
	extends AgeableMobRenderer<StaringDogEntity, WolfRenderState, WolfModel>
{
	public StaringDogRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new AdultWolfModel(context.getPart(EntityModelLayers.WOLF)),
			new BabyWolfModel(context.getPart(ModelLayers.WOLF_BABY)),
			0.5F
		);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/staring_dog.png")
		);
	}

	@Override
	public ResourceLocation getTextureLocation(WolfRenderState dogRenderState) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/staring_dog.png");
	}

	@Override
	public WolfRenderState createRenderState() {
		return new WolfRenderState();
	}

	@Override
	public void extractRenderState(
		StaringDogEntity dog, WolfRenderState dogRenderState, float f
	) {
		super.extractRenderState(dog, dogRenderState, f);

		dogRenderState.isSitting = dog.isInSittingPose();
	}
}
