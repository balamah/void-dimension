package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.base.EntityAttributeModifierGoal;
import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.balamah.voiddim.interfaces.ScreamBeforeAttackUser;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ScreamBeforeAttackGoal<T extends ModHostileEntity & ScreamBeforeAttackUser>
	extends EntityAttributeModifierGoal<T>
{
	protected final Identifier roarAttributeId = Identifier.withDefaultNamespace("roar");
	protected final Identifier endAttributeId = Identifier.withDefaultNamespace("end");

	protected final AttributeModifier roarAttributeModifier =
		this.getAttributeModifier(
			this.roarAttributeId, -1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final AttributeModifier roarEndAttributeModifier =
		this.getAttributeModifier(
			this.endAttributeId, 0.3, AttributeModifier.Operation.ADD_VALUE
		);

	protected final AttributeInstance entityAttributeInstance;

	protected final int screamTicks;
	protected final SoundEvent roarSound;

	public ScreamBeforeAttackGoal(T entity, int screamTicks, SoundEvent roarSound) {
		super(entity);

		this.screamTicks = screamTicks;
		this.roarSound = roarSound;

		this.entityAttributeInstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
	}

	@Override
	public void start() {
		super.start();

		this.sendEntityStatus(ModEntityStatuses.ROAR);
		this.entity.playSound(roarSound);
		this.addModifier(
			this.entityAttributeInstance, this.roarAttributeId, this.roarAttributeModifier
		);
	}

	@Override
	public void stop() {
		super.stop();

		this.sendEntityStatus(ModEntityStatuses.ROAR_STOP);
		this.removeModifier(this.entityAttributeInstance, this.roarAttributeModifier);

		this.entity.setScreamBeforeAttackTicks(this.entity.getScreamBeforeAttackCooldown());

		/*
		 * NOTE: Remove modifier after losing target using code
		 * this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(
		 *     this.getRoarEndAttributeModifier()
		 * );
		 */
		if (this.entity.getTarget() != null) {
			this.addModifier(
				this.entityAttributeInstance, this.endAttributeId, this.roarEndAttributeModifier
			);
		}
	}

	@Override
	public void tick() {
		super.tick();

		LivingEntity target = this.entity.getTarget();

		if (target != null) {
			this.entity.lookAt(target, 360f, 360f);
		}
	}

	@Override
	public boolean canUse() {
		return this.entity.getTarget() != null && this.entity.getScreamBeforeAttackTicks() == 0;
	}

	@Override
	public boolean canContinueToUse() {
		return this.entity.getTarget() != null && this.tick <= this.screamTicks;
	}
}
