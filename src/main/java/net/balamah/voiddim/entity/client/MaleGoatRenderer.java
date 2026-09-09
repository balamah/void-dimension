package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.state.GoatRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.balamah.voiddim.entity.custom.MaleGoatEntity;
import net.minecraft.resources.Identifier;

public class MaleGoatRenderer
	extends MobRenderer<MaleGoatEntity, GoatRenderState, MaleGoatModel>
{
	private static final Identifier GOAT_LOCATION =
		Identifier.withDefaultNamespace("textures/entity/goat/goat.png");

	private static final Identifier BABY_GOAT_LOCATION =
		Identifier.withDefaultNamespace("textures/entity/goat/goat_baby.png");

	public MaleGoatRenderer(final EntityRendererProvider.Context context) {
		super(context, new MaleGoatModel(context.bakeLayer(MaleGoatModel.MALE_GOAT)), 0.75f);
	}

	public Identifier getTextureLocation(final GoatRenderState state) {
		return state.isBaby ? BABY_GOAT_LOCATION : GOAT_LOCATION;
	}

	public GoatRenderState createRenderState() {
		return new GoatRenderState();
	}

	public void extractRenderState(
		final MaleGoatEntity entity, final GoatRenderState state, final float partialTicks
	) {
		super.extractRenderState(entity, state, partialTicks);
		state.hasLeftHorn = entity.hasLeftHorn();
		state.hasRightHorn = entity.hasRightHorn();
		state.rammingXHeadRot = entity.getRammingXHeadRot();
	}

	@Override
	public void submit(
		GoatRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
		CameraRenderState camera)
	{
		float size = 0f;
		if (state.isBaby) {
			size = 0.5f;
		} else {
			size = 1f;
		}

		poseStack.scale(size, size, size);

		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
