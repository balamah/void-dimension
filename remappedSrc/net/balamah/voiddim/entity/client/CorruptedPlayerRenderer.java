package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.custom.ImageHelper;
import net.balamah.voiddim.entity.custom.CorruptedPlayerEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.PlayerSkin;

public class CorruptedPlayerRenderer
	extends BipedEntityRenderer<CorruptedPlayerEntity, CorruptedPlayerRenderState, CorruptedPlayerModel>
{
	public CorruptedPlayerRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new CorruptedPlayerModel(context.getPart(CorruptedPlayerModel.CORRUPTED_PLAYER)),
			0.5f
		);
	}

	@Override
	public CorruptedPlayerRenderState createRenderState() {
		return new CorruptedPlayerRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(CorruptedPlayerRenderState state) {
		return state.skinIdentifier;
	}

	@Override
	public void extractRenderState(
		CorruptedPlayerEntity entity, CorruptedPlayerRenderState state, float partialTicks
	) {
		super.extractRenderState(entity, state, partialTicks);

		state.playerName = entity.getPlayerName();
		state.playerProfile = entity.getPlayerProfile();
		state.showHat = true;
		state.showJacket = true;
		state.showLeftPants = true;
		state.showRightPants = true;
		state.showLeftSleeve = true;
		state.showRightSleeve = true;

		PlayerSkin skin = CorruptedPlayerSkinCache.getSkin(state.playerProfile, state.playerName);
		state.skinIdentifier = CorruptedPlayerSkinCache.corruptSkin(state.playerName, skin);
	}
}
