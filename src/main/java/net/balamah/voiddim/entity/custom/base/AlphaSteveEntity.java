package net.balamah.voiddim.entity.custom.base;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.RandomAttackGoal;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class AlphaSteveEntity extends SunBurningEntity {
	protected int ticks;

	public AlphaSteveEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return PathAwareEntity.createMobAttributes()
			.add(EntityAttributes.MAX_HEALTH, 20)
			.add(EntityAttributes.STEP_HEIGHT, 1.0)
			.add(EntityAttributes.JUMP_STRENGTH, 0.4f)
			.add(EntityAttributes.ATTACK_DAMAGE, 7.6f)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.4F);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(3, new RandomAttackGoal(this, 500));

		this.targetSelector.add(0, McCodeHelper.getTargetGoal(this, PlayerEntity.class));
		this.targetSelector.add(2, McCodeHelper.getTargetGoal(this, PassiveEntity.class));
	}

	@Override
	public void tick() {
		super.tick();

		this.ticks++;

		Vec3d movement = this.getMovement();
		boolean isMoving = movement.x != 0 || movement.z != 0;
		if (ticks % 15 == 0 && this.isOnGround() && isMoving) {
			this.jump();
		}
	}

	@Override
	protected abstract SoundEvent getHurtSound(DamageSource source);

	@Override
	protected abstract SoundEvent getDeathSound();
}
