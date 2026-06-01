package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class CorruptedStalkerEntity extends CorruptedHostileEntity {
	protected ResourceLocation ATTACKING_SPEED_MODIFIER_ID = ResourceLocation.withDefaultNamespace("attacking");
	protected EntityAttributeModifier ATTACKING_SPEED_BOOST =
		new EntityAttributeModifier(
			ATTACKING_SPEED_MODIFIER_ID,
			0.15F,
			EntityAttributeModifier.Operation.ADD_VALUE
	);

	protected RegistryEntry<StatusEffect> effect = StatusEffects.INVISIBILITY;
	protected EntityAttributeInstance entityAttributeInstance =
		this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);

	public CorruptedStalkerEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);

		this.experiencePoints = 15;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 35)
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 15)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.5F)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0);
	}

	@Override
	public void tick() {
		super.tick();

		World world = this.getWorld();

		if (world.isClient()) {
			return;
		}

		/*
		 * The entity has invisibility when it doesn't attack.
		 * When it attacks, invisibility is removed
		 */
		if (this.getTarget() == null) {
			this.entityAttributeInstance.removeModifier(ATTACKING_SPEED_MODIFIER_ID);

			if (!this.hasStatusEffect(effect)) {
				this.addStatusEffect(new StatusEffectInstance(this.effect, 200, 0, false, false));
			}
		} else {
			if (!this.entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) {
				this.entityAttributeInstance.addTemporaryModifier(ATTACKING_SPEED_BOOST);
			}

			if (this.hasStatusEffect(effect)) {
				this.removeStatusEffect(effect);

				this.playSoundCurrentLocation(ModSounds.CORRUPTED_STALKER_ATTACK);
			}
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.CORRUPTED_STALKER_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.CORRUPTED_STALKER_DEATH;
	}
}
