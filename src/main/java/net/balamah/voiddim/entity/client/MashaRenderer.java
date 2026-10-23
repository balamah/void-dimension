package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.entity.custom.MashaEntity;

public class MashaRenderer
	extends MobRenderer<MashaEntity, BasicRenderState, MashaModel> 
{
	public MashaRenderer(EntityRendererProvider.Context context) {
		super(context, new MashaModel(context.bakeLayer(MashaModel.MASHA)), 0.75f);
	}

	@Override
	public BasicRenderState createRenderState() {
		return new BasicRenderState();
	}

	@Override
	public Identifier getTextureLocation(BasicRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/masha.png");
	}

	@Override
	public void submit(
		BasicRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
		CameraRenderState camera
	) {
		float size = 0f;
		if (state.isBaby) {
			size = 0.5f;
		} else {
			size = 0.7f;
		}

		poseStack.scale(size, size, size);

		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
