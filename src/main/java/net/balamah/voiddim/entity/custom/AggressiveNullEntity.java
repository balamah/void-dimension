package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.RandomPlaceSignsGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;

public class AggressiveNullEntity extends CorruptedHostileEntity {
	protected String[] tableLines = {"NULL", "NULL", "NULL", "NULL"};

	public AggressiveNullEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return PathAwareEntity.createMobAttributes()
			.add(EntityAttributes.MAX_HEALTH, 25)
			.add(EntityAttributes.STEP_HEIGHT, 1.0)
			.add(EntityAttributes.JUMP_STRENGTH, 0.4f)
			.add(EntityAttributes.ATTACK_DAMAGE, 7.6f)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.65F)
			.add(EntityAttributes.FOLLOW_RANGE, 35)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.4F);
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		Goal randomPlaceSignsGoal =
			new RandomPlaceSignsGoal<AggressiveNullEntity>(
				this, new String[]{"NULL", "NULL", "NULL", "NULL"}, 25, 6
			);

		this.goalSelector.add(1, randomPlaceSignsGoal);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.STARING_ENTITY_DEATH;
	}
}
