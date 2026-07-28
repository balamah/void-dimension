package net.balamah.voiddim.entity.client.base;

import net.balamah.voiddim.entity.client.WerewolfAnimations;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;

public class AbstractWerewolfModel
	<T extends AbstractWerewolfRenderState> extends BasicLivingEntityModel<T>
{
	protected final KeyframeAnimation attackHitAnimation;
	protected final KeyframeAnimation attackBiteAnimation;
	protected final KeyframeAnimation walkingAnimation;
	
	protected final ModelPart body;
	protected final ModelPart mouth;
	protected final ModelPart jaw;
	protected final ModelPart jaw_teeth;
	protected final ModelPart upper_mouth;
	protected final ModelPart upper_teeth;
	protected final ModelPart horns;
	protected final ModelPart tail;
	protected final ModelPart front_paws;
	protected final ModelPart right_front_paw;
	protected final ModelPart right_shoulder_bicep;
	protected final ModelPart right_elbow;
	protected final ModelPart left_front_paw;
	protected final ModelPart left_shoulder_bicep;
	protected final ModelPart left_elbow;
	protected final ModelPart back_paws;
	protected final ModelPart right_back_paw;
	protected final ModelPart right_calf;
	protected final ModelPart left_back_paw;
	protected final ModelPart left_calf;

	public AbstractWerewolfModel(ModelPart root) {
		super(root);

		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.mouth = this.head.getChild("mouth");
		this.jaw = this.mouth.getChild("jaw");
		this.jaw_teeth = this.jaw.getChild("jaw_teeth");
		this.upper_mouth = this.mouth.getChild("upper_mouth");
		this.upper_teeth = this.upper_mouth.getChild("upper_teeth");
		this.horns = this.body.getChild("horns");
		this.tail = this.body.getChild("tail");
		this.front_paws = root.getChild("front_paws");
		this.right_front_paw = this.front_paws.getChild("right_front_paw");
		this.right_shoulder_bicep = this.right_front_paw.getChild("right_shoulder_bicep");
		this.right_elbow = this.right_front_paw.getChild("right_elbow");
		this.left_front_paw = this.front_paws.getChild("left_front_paw");
		this.left_shoulder_bicep = this.left_front_paw.getChild("left_shoulder_bicep");
		this.left_elbow = this.left_front_paw.getChild("left_elbow");
		this.back_paws = root.getChild("back_paws");
		this.right_back_paw = this.back_paws.getChild("right_back_paw");
		this.right_calf = this.right_back_paw.getChild("right_calf");
		this.left_back_paw = this.back_paws.getChild("left_back_paw");
		this.left_calf = this.left_back_paw.getChild("left_calf");

		/*
		 * NOTE: Put these parts in WerewolfModel:
		 * private final ModelPart ears;
		 * this.ears = this.head.getChild("ears");
		 */
		this.attackHitAnimation = WerewolfAnimations.ATTACK_HIT.bake(root);
		this.attackBiteAnimation = WerewolfAnimations.ATTACK_BITE.bake(root);
		this.walkingAnimation = WerewolfAnimations.WALK.bake(root);
	}

	    @Override
	    public void setupAnim(T state) {
	        super.setupAnim(state);

		this.walkingAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		this.attackHitAnimation.apply(state.attackHitAnimationState, state.ageInTicks, 1f);
		this.attackBiteAnimation.apply(state.attackBiteAnimationState, state.ageInTicks, 1f);
    }
}
