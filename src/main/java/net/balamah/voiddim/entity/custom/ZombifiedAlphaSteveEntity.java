package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.RandomAttackGoal;
import net.balamah.voiddim.entity.custom.base.AlphaSteveEntity;
import net.balamah.voiddim.sound.ModSounds;

public class ZombifiedAlphaSteveEntity extends AlphaSteveEntity {
	public ZombifiedAlphaSteveEntity(
		EntityType<? extends PathAwareEntity> entityType, World world
	) {
		super(entityType, world);
	}
	
	@Override
	protected void initGoals() {
		super.initGoals();

		this.goalSelector.add(3, new RandomAttackGoal(this, 30));
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
