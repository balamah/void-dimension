package net.balamah.voiddim.entity.client;

import com.mojang.authlib.GameProfile;

import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.client.Minecraft;

import net.balamah.voiddim.custom.PlayerSkins;

public class CorruptedPlayerSkinCache {
	public static PlayerSkin getSkin(ResolvableProfile profile, String playerName) {
		GameProfile gameProfile = profile.partialProfile();
		if (gameProfile.name().isBlank() && playerName != null && !playerName.isBlank()) {
			gameProfile = ResolvableProfile.createUnresolved(playerName).partialProfile();
		}

		if (gameProfile.name().isBlank()) {
			return PlayerSkins.getSkin(0);
		}


		return Minecraft.getInstance()
				.getSkinManager()
				.createLookup(gameProfile, false)
				.get()
				.with(profile.skinPatch());
	}

	/*
	 * TODO: Implement algorithm
	 * Get skin path
	 * Throw the skin path into corruptor
	 * Return the corrupted skin
	 */
	// public static Identifier corruptSkin(GameProfile profile, String playerName, PlayerSkin skin) {
	// 	Identifier skinTexturePath = skin.body().texturePath();
	// 	return ImageHelper.getCorruptedSkin(profile, playerName, skinTexturePath);
	// }
}
