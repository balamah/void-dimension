package net.balamah.voiddim.entity.custom.base;

import net.balamah.voiddim.entity.custom.ai.goal.RandomAttackGoal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class AlphaSteveEntity extends SunBurningEntity {
	protected int ticks;

	public AlphaSteveEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 20)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.JUMP_STRENGTH, 0.4f)
			.add(Attributes.ATTACK_DAMAGE, 7.6f)
			.add(Attributes.MOVEMENT_SPEED, 0.4F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new RandomAttackGoal(this, 500));

		this.targetSelector.addGoal(0, McCodeHelper.getTargetGoal(this, Player.class));
		this.targetSelector.addGoal(2, McCodeHelper.getTargetGoal(this, AgeableMob.class));
	}

	@Override
	public void tick() {
		super.tick();

		this.ticks++;

		Vec3 movement = this.getKnownMovement();
		boolean isMoving = movement.x != 0 || movement.z != 0;
		if (ticks % 15 == 0 && this.onGround() && isMoving) {
			this.jumpFromGround();
		}
	}

	@Override
	protected abstract SoundEvent getHurtSound(DamageSource source);

	@Override
	protected abstract SoundEvent getDeathSound();
}
