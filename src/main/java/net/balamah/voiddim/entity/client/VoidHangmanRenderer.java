package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.VoidHangmanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VoidHangmanRenderer
	extends MobRenderer<VoidHangmanEntity,
							  VoidHangmanRenderState,
							  VoidHangmanModel>
{
	public VoidHangmanRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidHangmanModel(context.bakeLayer(VoidHangmanModel.VOID_HANGMAN)), 0.5f);
	}

	@Override
	public VoidHangmanRenderState createRenderState() {
		return new VoidHangmanRenderState();
	}

	@Override
	public Identifier getTextureLocation(VoidHangmanRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/void_hangman.png");
	}

	@Override
	public void extractRenderState(VoidHangmanEntity entity,
								  VoidHangmanRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
	}
}
