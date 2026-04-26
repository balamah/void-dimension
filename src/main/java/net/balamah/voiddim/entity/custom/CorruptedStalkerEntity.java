package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class CorruptedStalkerEntity extends CorruptedHostileEntity {
	protected Identifier ATTACKING_SPEED_MODIFIER_ID = Identifier.withDefaultNamespace("attacking");
	protected AttributeModifier ATTACKING_SPEED_BOOST =
		new AttributeModifier(
			ATTACKING_SPEED_MODIFIER_ID,
			0.15F,
			AttributeModifier.Operation.ADD_VALUE
	);

	protected Holder<MobEffect> effect = MobEffects.INVISIBILITY;
	protected AttributeInstance entityAttributeInstance =
		this.getAttribute(Attributes.MOVEMENT_SPEED);

	public CorruptedStalkerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.xpReward = 15;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 35)
			.add(Attributes.FOLLOW_RANGE, 15)
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.ATTACK_DAMAGE, 10.5F)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public void tick() {
		super.tick();

		Level world = this.level();

		if (world.isClientSide()) {
			return;
		}

		/*
		 * The entity has invisibility when it doesn't attack.
		 * When it attacks, invisibility is removed
		 */
		if (this.getTarget() == null) {
			this.entityAttributeInstance.removeModifier(ATTACKING_SPEED_MODIFIER_ID);

			if (!this.hasEffect(effect)) {
				this.addEffect(new MobEffectInstance(this.effect, 200, 0, false, false));
			}
		} else {
			if (!this.entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) {
				this.entityAttributeInstance.addTransientModifier(ATTACKING_SPEED_BOOST);
			}

			if (this.hasEffect(effect)) {
				this.removeEffect(effect);

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
