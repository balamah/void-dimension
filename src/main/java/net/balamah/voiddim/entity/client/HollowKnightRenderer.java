package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.WeaponArmedEntityRenderer;
import net.balamah.voiddim.entity.custom.HollowKnightEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.VoidDimension;

public class HollowKnightRenderer
	extends WeaponArmedEntityRenderer<HollowKnightEntity,
							  HollowKnightRenderState,
							  HollowKnightModel> 
{
	public HollowKnightRenderer(EntityRendererProvider.Context context) {
		super(context, new HollowKnightModel(context.bakeLayer(HollowKnightModel.HOLLOW_KNIGHT)), 0.75f);

		this.addLayer(new ItemInHandLayer<>(this));
	}

	@Override
	public HollowKnightRenderState createRenderState() {
		return new HollowKnightRenderState();
	}

	@Override
	public Identifier getTextureLocation(HollowKnightRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/hollow_knight.png");
	}

	@Override
	public void extractRenderState(HollowKnightEntity entity,
								  HollowKnightRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.isHurting = entity.isHurting;

		renderState.walkingState.copyFrom(entity.walkingState);
		renderState.attack1State.copyFrom(entity.attack1State);
		renderState.attack2State.copyFrom(entity.attack2State);
		renderState.attack3State.copyFrom(entity.attack3State);
		renderState.attack4State.copyFrom(entity.attack4State);
		renderState.vengefulSpiritState.copyFrom(entity.vengefulSpiritState);
	}
}
