package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import net.balamah.voiddim.sound.ModSounds;

public class HollowedBeastEntity extends WerewolfEntity {
	public HollowedBeastEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 64)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.ATTACK_DAMAGE, 9.0F)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
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
