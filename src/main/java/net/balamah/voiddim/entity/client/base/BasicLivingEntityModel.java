package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class BasicLivingEntityModel<T extends LivingEntityRenderState>
	extends EntityModel<T>
{
	protected ModelPart head;

	public BasicLivingEntityModel(ModelPart root) {
		super(root);
	}

    @Override
	public void setupAnim(T state) {
		super.setupAnim(state);

		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
	}
}
