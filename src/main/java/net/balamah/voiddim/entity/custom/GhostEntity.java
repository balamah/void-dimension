package net.balamah.voiddim.entity.custom;

import org.jspecify.annotations.Nullable;

import net.balamah.voiddim.entity.custom.base.CorruptedNeuteralEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class GhostEntity extends CorruptedNeuteralEntity {
    public final AnimationState walkAnimationState = new AnimationState();

	protected Identifier ATTACKING_SPEED_MODIFIER_ID = Identifier.withDefaultNamespace("attacking");
	protected AttributeModifier ATTACKING_SPEED_BOOST =
		new AttributeModifier(
			ATTACKING_SPEED_MODIFIER_ID,
			0.20F,
			AttributeModifier.Operation.ADD_VALUE
	);

	protected AttributeInstance entityAttributeInstance =
		this.getAttribute(Attributes.MOVEMENT_SPEED);

	public GhostEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.2F)
			.add(Attributes.ATTACK_DAMAGE, 13.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 25);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide()) {
			if (this.getTarget() == null) {
				this.entityAttributeInstance.removeModifier(ATTACKING_SPEED_MODIFIER_ID);
			} else if (!this.entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) {
				this.entityAttributeInstance.addTransientModifier(ATTACKING_SPEED_BOOST);
			}
		}
	}

	@Override
	protected @Nullable SoundEvent getAmbientSound() {
		if (this.getTarget() != null) {
			return ModSounds.GHOST_ATTACKED;
		}

		return ModSounds.GHOST_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.GHOST_ATTACKED;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.GHOST_DEATH;
	}
}
