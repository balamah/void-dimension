package net.balamah.voiddim.custom;

import net.minecraft.server.packs.resources.Resource;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import com.mojang.blaze3d.platform.NativeImage;

public class ImageHelper {
	public static ResourceLocation getCorruptedSkin(
		String playerName, ResourceLocation skinTexturePath
	) {
		try {
			Minecraft minecraft = Minecraft.getInstance();

			Resource skinResource = minecraft.getResourceManager()
				.getResource(skinTexturePath).get();

			BufferedImage skinPath = ImageIO.read(skinResource.open());
			BufferedImage graySkin = convertToGray(skinPath);
			BufferedImage corruptedSkin = drawCorruptedEyes(graySkin);

			return registerCorruptedSkin(minecraft, playerName, corruptedSkin);
		} catch (IOException e) {
			e.printStackTrace();

			return skinTexturePath;
		}
	}

	public static BufferedImage convertToGray(BufferedImage colorImage) throws IOException {
		BufferedImage grayImage = new BufferedImage(
			colorImage.getWidth(), 
			colorImage.getHeight(), 
			BufferedImage.TYPE_INT_ARGB
		);

		Graphics graphics = grayImage.getGraphics();
		graphics.drawImage(colorImage, 0, 0, null);
		graphics.dispose();

		return grayImage;
	}

	public static BufferedImage drawCorruptedEyes(BufferedImage grayImage) throws IOException {
		ResourceLocation eyesIdentifier = ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/misc/blood_eyes.png"
		);

		Resource resource = Minecraft.getInstance().getResourceManager()
			.getResourceOrThrow(eyesIdentifier);

		BufferedImage foreground = ImageIO.read(resource.open());

		Graphics graphics = grayImage.getGraphics();
		graphics.drawImage(foreground, 40, 8, null);
		graphics.dispose();

		return grayImage;
	}

	protected Path saveSkinImagePath(
		Minecraft minecraft, BufferedImage image, String playerName
	) throws IOException
	{
		Path path = minecraft.gameDirectory.toPath()
				.resolve("assets")
				.resolve("skins")
				.resolve("corruption")
				.resolve(playerName + ".png");

		Files.createDirectories(path.getParent());

		ImageIO.write(image, "png", Files.newOutputStream(path));

		return path;
	}

	protected static ResourceLocation registerCorruptedSkin(
		Minecraft minecraft, String playerName, BufferedImage image
	) {
		ResourceLocation corruptedSkinIdentifier = ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "corruption/" + playerName
		);

		NativeImage nativeImage = new NativeImage(image.getWidth(), image.getHeight(), true);

		DynamicTexture texture = new DynamicTexture(() -> "corrupted_skin", nativeImage);
		minecraft.getTextureManager().register(corruptedSkinIdentifier, texture);

		return corruptedSkinIdentifier;
	}
}
