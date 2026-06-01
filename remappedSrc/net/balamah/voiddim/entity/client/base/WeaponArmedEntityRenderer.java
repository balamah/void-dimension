package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;

public abstract class WeaponArmedEntityRenderer
<T extends MobEntity, S extends ArmedEntityRenderState, M extends EntityModel<S> & ModelWithArms<S>>
	extends MobEntityRenderer<T, S, M> 
{
	public WeaponArmedEntityRenderer(Context context, M model, float shadow) {
		super(context, model, shadow);

		this.addLayer(new HeldItemFeatureRenderer<S, M>(this));
	}

	@Override
	public abstract ResourceLocation getTextureLocation(S state);

	@Override
	public abstract S createRenderState();
	
	@Override
	public void extractRenderState(T entity, S state, float f) {
		super.extractRenderState(entity, state, f);

		state.leftArmPose = this.getArmPose(entity, Arm.LEFT);
		state.rightArmPose = this.getArmPose(entity, Arm.RIGHT);

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

	protected BipedEntityModel.ArmPose getArmPose(final T mob, final Arm arm) {
		ItemStack itemHeldByArm = mob.getItemHeldByArm(arm);
		SwingAnimation anim = itemHeldByArm.get(DataComponents.SWING_ANIMATION);
		if (anim != null && anim.type() == SwingAnimationType.STAB && mob.handSwinging) {
			return HumanoidModel.ArmPose.SPEAR;
		} else {
			return itemHeldByArm.isIn(ItemTags.SPEARS) ?
				HumanoidModel.ArmPose.SPEAR : BipedEntityModel.ArmPose.EMPTY;
		}
	}

	private static Arm getAttackArm(final LivingEntity entity) {
		Arm mainArm = entity.getMainArm();
		return entity.preferredHand != Hand.OFF_HAND ? mainArm : mainArm.getOpposite();
	}
}
