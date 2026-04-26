package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.ai.goal.RandomPlaceSignsGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class AggressiveNullEntity extends CorruptedHostileEntity {
	protected String[] tableLines = {"NULL", "NULL", "NULL", "NULL"};

	public AggressiveNullEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 25)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.JUMP_STRENGTH, 0.4f)
			.add(Attributes.ATTACK_DAMAGE, 7.6f)
			.add(Attributes.MOVEMENT_SPEED, 0.65F)
			.add(Attributes.FOLLOW_RANGE, 35)
			.add(Attributes.MOVEMENT_SPEED, 0.4F);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		Goal randomPlaceSignsGoal =
			new RandomPlaceSignsGoal<AggressiveNullEntity>(
				this, new String[]{"NULL", "NULL", "NULL", "NULL"}, 17, 6, 5
			);

		this.goalSelector.addGoal(1, randomPlaceSignsGoal);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.STARING_ENTITY_DEATH;
	}
}
