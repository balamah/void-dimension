package net.balamah.voiddim.entity.client;

import java.io.IOException;

import com.mojang.authlib.GameProfile;

import net.balamah.voiddim.custom.ImageHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.PlayerSkin;

public class CorruptedPlayerSkinCache {
	public static PlayerSkin getSkin(ProfileComponent profile, String playerName) {
		GameProfile gameProfile = profile.partialProfile();
		if (gameProfile.name().isBlank() && playerName != null && !playerName.isBlank()) {
			gameProfile = ProfileComponent.createUnresolved(playerName).partialProfile();
		}

		if (gameProfile.name().isBlank()) {
			return DefaultSkinHelper.getDefaultSkin();
		}

		return MinecraftClient.getInstance()
				.getSkinProvider()
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
	public static ResourceLocation corruptSkin(String playerName, PlayerSkin skin) {
		ResourceLocation skinTexturePath = skin.body().texturePath();
		return ImageHelper.getCorruptedSkin(playerName, skinTexturePath);
	}
}
