package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.ai.goal.RandomAttackGoal;
import net.balamah.voiddim.entity.custom.base.AlphaSteveEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class HollowedAlphaSteveEntity extends AlphaSteveEntity {
	public HollowedAlphaSteveEntity(
		EntityType<? extends PathAwareEntity> entityType, World world
	) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		this.goalSelector.add(3, new RandomAttackGoal(this, 500));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.HOLLOWED_ALPHA_STEVE_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.HOLLOWED_ALPHA_STEVE_HIT;
	}
}
