package net.balamah.voiddim.entity.custom.base;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.custom.McCodeHelper;

public abstract class StaringEntity extends SunBurningEntity {
	public StaringEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 100)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.0F)
			.add(EntityAttributes.MAX_HEALTH, 20);
	}

	@Override
	public void tick() {
		super.tick();

		/*
		 * Despawn the entity if its target is a player 
		 * within 45 blocks and both can see each other.
		 */
		if (this.getTarget() != null && this.getTarget() instanceof PlayerEntity target &&
			this.distanceTo(target) < 45 && this.canSee(target)
		) {
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
			this.playSound(SoundEvents.ENTITY_WITHER_SPAWN);
			this.discard();
		}
	}

	@Override
	protected void initGoals() {
		this.targetSelector.add(0, McCodeHelper.getTargetGoal(this, PlayerEntity.class));

		this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 100.0F));
	}
}
