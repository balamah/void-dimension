package net.balamah.voiddim.custom;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.Minecraft;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.util.Locale;

import javax.imageio.ImageIO;

import com.mojang.blaze3d.platform.NativeImage;

public class ImageHelper {
	public static Identifier getCorruptedSkin(
		PlayerSkin skin, String playerName, Identifier skinTexturePath
	) {
		try {
			Minecraft minecraft = Minecraft.getInstance();

			BufferedImage skinPath = readSkinImage(minecraft, skinTexturePath);
			BufferedImage graySkin = convertToGray(skinPath);
			BufferedImage corruptedSkin = drawCorruptedEyes(graySkin);

			return registerCorruptedSkin(skin, playerName, corruptedSkin);
		} catch (Exception e) {
			e.printStackTrace();

			return skinTexturePath;
		}
	}

	public static BufferedImage convertToGray(BufferedImage colorImage) throws IOException {
		BufferedImage grayImage = new BufferedImage(
			colorImage.getWidth(), 
			colorImage.getHeight(), 
			BufferedImage.TYPE_BYTE_GRAY
		);

		Graphics graphics = grayImage.getGraphics();
		graphics.drawImage(colorImage, 0, 0, null);
		graphics.dispose();

		return grayImage;
	}

	public static BufferedImage drawCorruptedEyes(BufferedImage grayImage) throws IOException {
		Identifier eyesIdentifier = Identifier.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/misc/blood_eyes.png"
		);

		Resource resource = Minecraft.getInstance().getResourceManager()
				.getResourceOrThrow(eyesIdentifier);

		BufferedImage foreground = ImageIO.read(resource.open());

		ImageIO.write(grayImage, "png", new File("/tmp/output"));

		Graphics graphics = grayImage.getGraphics();
		graphics.drawImage(foreground, 40, 8, null);
		graphics.dispose();

		return grayImage;
	}

	public static NativeImage getNativeImage(BufferedImage bufferedImage) {
		NativeImage nativeImage = new NativeImage(
			bufferedImage.getWidth(), bufferedImage.getHeight(), true
		);

		for (int y = 0; y < bufferedImage.getHeight(); y++) {
			for (int x = 0; x < bufferedImage.getWidth(); x++) {
				nativeImage.setPixel(x, y, bufferedImage.getRGB(x, y));
			}
		}

		return nativeImage;
	}

	protected static Identifier registerCorruptedSkin(
		PlayerSkin skin,
		String playerName,
		BufferedImage corruptedSkinImage
	) throws IOException {
		String corruptedSkinLabel = sanitizeTextureName(playerName);
		Identifier corruptedSkinIdentifier = Identifier.fromNamespaceAndPath(
			VoidDimension.MOD_ID, String.format("corrupted/%s", corruptedSkinLabel)
		);

		NativeImage nativeImage = getNativeImage(corruptedSkinImage);
		DynamicTexture corruptedSkin = new DynamicTexture(() -> corruptedSkinLabel, nativeImage);

		Minecraft.getInstance().getTextureManager().register(
			corruptedSkinIdentifier, corruptedSkin
		);

		return corruptedSkinIdentifier;
	}

	protected static String sanitizeTextureName(String playerName) {
		String lowerCasePlayerName = playerName.toLowerCase(Locale.ROOT)
			.replaceAll("[^a-z0-9/._-]", "_");

		String name = (playerName == null || playerName.isBlank()) ? "none" : lowerCasePlayerName;
		String hash = Integer.toUnsignedString((name + "|").hashCode(), 16);

		return name + "_" + hash;
	}

	protected static BufferedImage readSkinImage(
		Minecraft minecraft, Identifier skinTexturePath
	) throws IOException {
		var skinResource = minecraft.getResourceManager().getResource(skinTexturePath);

		if (skinResource.isPresent()) {
			try (var stream = skinResource.get().open()) {
				return ImageIO.read(stream);
			}
		}

		var texture = minecraft.getTextureManager().getTexture(skinTexturePath);
		if (texture instanceof DynamicTexture dynamicTexture) {
			NativeImage nativeImage = dynamicTexture.getPixels();
			BufferedImage image = new BufferedImage(
				nativeImage.getWidth(), nativeImage.getHeight(), BufferedImage.TYPE_INT_ARGB
			);

			for (int y = 0; y < nativeImage.getHeight(); y++) {
				for (int x = 0; x < nativeImage.getWidth(); x++) {
					image.setRGB(x, y, nativeImage.getPixel(x, y));
				}
			}

			return image;
		}

		throw new IOException("Skin texture is not available: " + skinTexturePath);
	}

}
