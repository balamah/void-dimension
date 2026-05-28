package net.balamah.voiddim.entity.client;

import java.io.IOException;

import com.mojang.authlib.GameProfile;

import net.balamah.voiddim.custom.ImageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.component.ResolvableProfile;

public class CorruptedPlayerSkinCache {
	public static PlayerSkin getSkin(ResolvableProfile profile, String playerName) {
		GameProfile gameProfile = profile.partialProfile();
		if (gameProfile.name().isBlank() && playerName != null && !playerName.isBlank()) {
			gameProfile = ResolvableProfile.createUnresolved(playerName).partialProfile();
		}

		if (gameProfile.name().isBlank()) {
			return DefaultPlayerSkin.getDefaultSkin();
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
	public static Identifier corruptSkin(String playerName, PlayerSkin skin) {
		Identifier skinTexturePath = skin.body().texturePath();
		return ImageHelper.getCorruptedSkin(playerName, skinTexturePath);
	}
}
