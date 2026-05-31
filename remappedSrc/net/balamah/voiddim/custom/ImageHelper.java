package net.balamah.voiddim.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.Resource;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class ImageHelper {
	public static ResourceLocation getCorruptedSkin(
		String playerName, ResourceLocation skinTexturePath
	) {
		try {
			MinecraftClient minecraft = MinecraftClient.getInstance();

			Resource skinResource = minecraft.getResourceManager()
				.getResource(skinTexturePath).get();

			BufferedImage skinPath = ImageIO.read(skinResource.getInputStream());
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

		Resource resource = MinecraftClient.getInstance().getResourceManager()
			.getResourceOrThrow(eyesIdentifier);

		BufferedImage foreground = ImageIO.read(resource.getInputStream());

		Graphics graphics = grayImage.getGraphics();
		graphics.drawImage(foreground, 40, 8, null);
		graphics.dispose();

		return grayImage;
	}

	protected Path saveSkinImagePath(
		MinecraftClient minecraft, BufferedImage image, String playerName
	) throws IOException
	{
		Path path = minecraft.runDirectory.toPath()
				.resolve("assets")
				.resolve("skins")
				.resolve("corruption")
				.resolve(playerName + ".png");

		Files.createDirectories(path.getParent());

		ImageIO.write(image, "png", Files.newOutputStream(path));

		return path;
	}

	protected static ResourceLocation registerCorruptedSkin(
		MinecraftClient minecraft, String playerName, BufferedImage image
	) {
		ResourceLocation corruptedSkinIdentifier = ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "corruption/" + playerName
		);

		NativeImage nativeImage = new NativeImage(image.getWidth(), image.getHeight(), true);

		NativeImageBackedTexture texture = new NativeImageBackedTexture(() -> "corrupted_skin", nativeImage);
		minecraft.getTextureManager().registerTexture(corruptedSkinIdentifier, texture);

		return corruptedSkinIdentifier;
	}
}
