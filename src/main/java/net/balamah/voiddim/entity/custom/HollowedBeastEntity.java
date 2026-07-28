package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class HollowedBeastEntity extends WerewolfEntity {
	public HollowedBeastEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.ATTACK_DAMAGE, 9.0F)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.HOLLOWED_BEAST_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.HOLLOWED_BEAST_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.HOLLOWED_BEAST_HIT;
	}
}
