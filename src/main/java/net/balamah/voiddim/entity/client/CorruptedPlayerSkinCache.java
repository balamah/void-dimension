package net.balamah.voiddim.entity.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
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
}
