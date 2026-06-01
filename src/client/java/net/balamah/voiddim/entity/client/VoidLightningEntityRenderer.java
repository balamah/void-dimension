package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.state.LightningBoltRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;

public class VoidLightningEntityRenderer extends LightningBoltRenderer {
	public VoidLightningEntityRenderer(Context context) {
		super(context);
	}

	public void submit(
		LightningBoltRenderState lightningEntityRenderState,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		float[] fs = new float[8];
		float[] gs = new float[8];
		float f = 0.0F;
		float g = 0.0F;
		RandomSource random = RandomSource.create(lightningEntityRenderState.seed);

		for (int i = 7; i >= 0; i--) {
			fs[i] = f;
			gs[i] = g;
			f += random.nextInt(11) - 5;
			g += random.nextInt(11) - 5;
		}

		float h = f;
		float j = g;
		orderedRenderCommandQueue.submitCustomGeometry(matrixStack, RenderTypes.lightning(), (matricesEntry, vertexConsumer) -> {
			Matrix4f matrix4f = matricesEntry.pose();

			for (int i = 0; i < 4; i++) {
				RandomSource randomx = RandomSource.create(lightningEntityRenderState.seed);

				for (int jx = 0; jx < 3; jx++) {
					int k = 7;
					int l = 0;
					if (jx > 0) {
						k = 7 - jx;
					}

					if (jx > 0) {
						l = k - 2;
					}

					float hx = fs[k] - h;
					float m = gs[k] - j;

					for (int n = k; n >= l; n--) {
						float o = hx;
						float p = m;
						if (jx == 0) {
							hx += randomx.nextInt(11) - 5;
							m += randomx.nextInt(11) - 5;
						} else {
							hx += randomx.nextInt(31) - 15;
							m += randomx.nextInt(31) - 15;
						}

						float q = 0.5F;
						float r = 0.45F;
						float s = 0.45F;
						float t = 0.5F;
						float u = 0.1F + i * 0.2F;
						if (jx == 0) {
							u *= n * 0.1F + 1.0F;
						}

						float v = 0.1F + i * 0.2F;
						if (jx == 0) {
							v *= (n - 1.0F) * 0.1F + 1.0F;
						}

						quad(matrix4f, vertexConsumer, hx, m, n, o, p, 0.45F, 0.45F, 0.5F, u, v, false, false, true, false);
						quad(matrix4f, vertexConsumer, hx, m, n, o, p, 0.45F, 0.45F, 0.5F, u, v, true, false, true, true);
						quad(matrix4f, vertexConsumer, hx, m, n, o, p, 0.45F, 0.45F, 0.5F, u, v, true, true, false, true);
						quad(matrix4f, vertexConsumer, hx, m, n, o, p, 0.45F, 0.45F, 0.5F, u, v, false, true, false, false);
					}
				}
			}
		});
	}
	
	private static void quad(
		Matrix4f matrix, VertexConsumer buffer,
		float x1, float z1, int y, float x2, float z2,
		float red, float green, float blue,
		float offset2, float offset1,
		boolean shiftEast1, boolean shiftSouth1, boolean shiftEast2,
		boolean shiftSouth2
	) {
		float color1 = 1.0F;
		float color2 = 0.01F;
		float color3 = 0.00F;
		float transparency = 0.9F;

		buffer.addVertex(matrix, x1 + (shiftEast1 ? offset1 : -offset1), (float)(y * 16), z1 + (shiftSouth1 ? offset1 : -offset1)).setColor(color1, color2, color3, transparency);
		buffer.addVertex(matrix, x2 + (shiftEast1 ? offset2 : -offset2), (float)((y + 1) * 16), z2 + (shiftSouth1 ? offset2 : -offset2)).setColor(color1, color2, color3, transparency);
		buffer.addVertex(matrix, x2 + (shiftEast2 ? offset2 : -offset2), (float)((y + 1) * 16), z2 + (shiftSouth2 ? offset2 : -offset2)).setColor(color1, color2, color3, transparency);
		buffer.addVertex(matrix, x1 + (shiftEast2 ? offset1 : -offset1), (float)(y * 16), z1 + (shiftSouth2 ? offset1 : -offset1)).setColor(color1, color2, color3, transparency);
	}
}
