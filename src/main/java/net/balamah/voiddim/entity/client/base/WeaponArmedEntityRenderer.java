package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.core.component.DataComponents;
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

		state.leftArmPose = this.getArmPose(entity, HumanoidArm.LEFT);
		state.rightArmPose = this.getArmPose(entity, HumanoidArm.RIGHT);

		this.extractHumanoidRenderState(entity, state, f, this.itemModelResolver);

	}

	public void extractHumanoidRenderState(
		T entity, S state, final float partialTicks, final ItemModelResolver itemModelResolver
	) {
		ArmedEntityRenderState.extractArmedEntityRenderState(
			entity, state, itemModelResolver, partialTicks
		);

		state.attackArm = getAttackArm(entity);
		state.mainArm = entity.getMainArm();
	}

	protected HumanoidModel.ArmPose getArmPose(final T mob, final HumanoidArm arm) {
		ItemStack itemHeldByArm = mob.getItemHeldByArm(arm);
		SwingAnimation anim = itemHeldByArm.get(DataComponents.SWING_ANIMATION);
		if (anim != null && anim.type() == SwingAnimationType.STAB && mob.swinging) {
			return HumanoidModel.ArmPose.SPEAR;
		} else {
			return itemHeldByArm.is(ItemTags.SPEARS) ?
				HumanoidModel.ArmPose.SPEAR : HumanoidModel.ArmPose.EMPTY;
		}
	}

	private static HumanoidArm getAttackArm(final LivingEntity entity) {
		HumanoidArm mainArm = entity.getMainArm();
		return entity.swingingArm != InteractionHand.OFF_HAND ? mainArm : mainArm.getOpposite();
	}
}
