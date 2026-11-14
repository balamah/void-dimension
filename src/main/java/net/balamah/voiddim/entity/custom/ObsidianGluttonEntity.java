package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

// TODO: Implement entity ObsidianGluttonEntity
public class ObsidianGluttonEntity extends CorruptedHostileEntity {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();

	public ObsidianGluttonEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.2F)
			.add(Attributes.ATTACK_DAMAGE, 1.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 5);
	}
}
