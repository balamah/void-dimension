package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.ai.goal.RandomPlaceSignsGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class AggressiveNullEntity extends CorruptedHostileEntity {
	protected String[] tableLines = {"NULL", "NULL", "NULL", "NULL"};

	public AggressiveNullEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return PathAwareEntity.createMobAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 25)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0)
			.add(EntityAttributes.GENERIC_JUMP_STRENGTH, 0.4f)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.6f)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.65F)
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4F);
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		Goal randomPlaceSignsGoal =
			new RandomPlaceSignsGoal<AggressiveNullEntity>(
				this, new String[]{"NULL", "NULL", "NULL", "NULL"}, 17, 6, 5
			);

		this.goalSelector.add(1, randomPlaceSignsGoal);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.STARING_ENTITY_DEATH;
	}
}
