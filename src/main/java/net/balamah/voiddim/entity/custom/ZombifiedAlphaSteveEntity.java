package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.ai.goal.RandomAttackGoal;
import net.balamah.voiddim.entity.custom.base.AlphaSteveEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class ZombifiedAlphaSteveEntity extends AlphaSteveEntity {
	public ZombifiedAlphaSteveEntity(
		EntityType<? extends PathfinderMob> entityType, Level world
	) {
		super(entityType, world);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(3, new RandomAttackGoal(this, 30));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.ZOMBIFIED_ALPHA_STEVE_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ZOMBIFIED_ALPHA_STEVE_DEATH;
	}
}
