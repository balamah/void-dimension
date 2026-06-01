package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
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
		super.setAngles(state);

		this.head.pitch = state.xRot * (float) (Math.PI / 180.0);
		this.head.yaw = state.yRot * (float) (Math.PI / 180.0);
	}
}
