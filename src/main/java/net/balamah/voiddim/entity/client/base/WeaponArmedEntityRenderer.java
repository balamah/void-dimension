package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.tags.ItemTags;

public abstract class WeaponArmedEntityRenderer
<T extends Mob, S extends ArmedEntityRenderState, M extends EntityModel<S> & ArmedModel<S>>
	extends MobRenderer<T, S, M> 
{
	public WeaponArmedEntityRenderer(Context context, M model, float shadow) {
		super(context, model, shadow);

		this.addLayer(new ItemInHandLayer<S, M>(this));
	}

	@Override
	public abstract Identifier getTextureLocation(S state);

	@Override
	public abstract S createRenderState();
	
	@Override
	public void extractRenderState(T entity, S state, float f) {
		super.extractRenderState(entity, state, f);

		this.extractHumanoidRenderState(entity, state, f, this.itemModelResolver);
		state.leftArmPose = this.getArmPose(entity, state, HumanoidArm.LEFT);
		state.rightArmPose = this.getArmPose(entity, state, HumanoidArm.RIGHT);

	}

	public void extractHumanoidRenderState(
		T entity, S state, final float partialTicks, final ItemModelResolver itemModelResolver
	) {
		ArmedEntityRenderState.extractArmedEntityRenderState(
			entity, state, itemModelResolver, partialTicks
		);

	}

	protected HumanoidModel.ArmPose getArmPose(
		final T mob, final S state, final HumanoidArm arm
	) {
		ItemStack itemHeldByArm = mob.getItemHeldByArm(arm);
		HumanoidArm swingingArm = state.currentSwing != null &&
			state.currentSwing.hand() == InteractionHand.OFF_HAND
			? mob.getMainArm().getOpposite() : mob.getMainArm();
		if (state.currentSwing != null && swingingArm == arm &&
			state.currentSwing.animation().type() == SwingAnimationType.STAB)
		{
			return HumanoidModel.ArmPose.SPEAR;
		} else {
			return itemHeldByArm.is(ItemTags.SPEARS) ?
				HumanoidModel.ArmPose.SPEAR : HumanoidModel.ArmPose.EMPTY;
		}
	}
}
