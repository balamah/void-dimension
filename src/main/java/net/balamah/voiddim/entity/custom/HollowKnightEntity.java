package net.balamah.voiddim.entity.custom;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class HollowKnightEntity extends Monster {
	public final AnimationState walkingState = new AnimationState();
	public final AnimationState attack1State = new AnimationState();
	public final AnimationState attack2State = new AnimationState();
	public final AnimationState attack3State = new AnimationState();
	public final AnimationState attack4State = new AnimationState();
	public final AnimationState vengefulSpiritState = new AnimationState();

	public HollowKnightEntity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 1F)
			.add(Attributes.ATTACK_DAMAGE, 13.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 55);
	}
}
