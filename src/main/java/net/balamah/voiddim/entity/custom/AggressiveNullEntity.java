package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.ai.goal.PlaceSignsGoal;
import net.balamah.voiddim.sound.ModSounds;

public class AggressiveNullEntity extends CorruptedHostileEntity {
	protected String[] tableLines = {"NULL", "NULL", "NULL", "NULL"};

	public AggressiveNullEntity(EntityType<? extends HostileEntity> entityType, World world) {
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
		// TODO: Add goal: PlaceSignsGoal
		super.initGoals();

		this.goalSelector.add(
			1, new PlaceSignsGoal<AggressiveNullEntity>(
				this, new String[]{"NULL", "NULL", "NULL", "NULL"}, 25
			)
		);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.STARING_ENTITY_DEATH;
	}
}
