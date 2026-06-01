package net.balamah.voiddim.custom;

import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.resources.Identifier;
import net.minecraft.core.ClientAsset;

// It sucks, but i want to use wide steve skin texture
public class PlayerSkins {
	public static final PlayerSkin[] DEFAULT_SKINS = new PlayerSkin[]{
		create("entity/player/wide/steve", PlayerModelType.WIDE),
		create("entity/player/slim/alex", PlayerModelType.SLIM),
		create("entity/player/slim/ari", PlayerModelType.SLIM),
		create("entity/player/slim/efe", PlayerModelType.SLIM),
		create("entity/player/slim/kai", PlayerModelType.SLIM),
		create("entity/player/slim/makena", PlayerModelType.SLIM),
		create("entity/player/slim/noor", PlayerModelType.SLIM),
		create("entity/player/slim/steve", PlayerModelType.SLIM),
		create("entity/player/slim/sunny", PlayerModelType.SLIM),
		create("entity/player/slim/zuri", PlayerModelType.SLIM),
		create("entity/player/wide/alex", PlayerModelType.WIDE),
		create("entity/player/wide/ari", PlayerModelType.WIDE),
		create("entity/player/wide/efe", PlayerModelType.WIDE),
		create("entity/player/wide/kai", PlayerModelType.WIDE),
		create("entity/player/wide/makena", PlayerModelType.WIDE),
		create("entity/player/wide/noor", PlayerModelType.WIDE),
		create("entity/player/wide/sunny", PlayerModelType.WIDE),
		create("entity/player/wide/zuri", PlayerModelType.WIDE)
	};

	public static PlayerSkin getSkin(int index) {
		return DEFAULT_SKINS[index];
	}

	protected static PlayerSkin create(final String body, final PlayerModelType model) {
		ClientAsset.ResourceTexture resourceTexture =
			new ClientAsset.ResourceTexture(Identifier.withDefaultNamespace(body));

		return new PlayerSkin(
			resourceTexture, null, null, model, true
		);
	}
}
