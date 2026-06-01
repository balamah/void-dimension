package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.resources.ResourceLocation;

public abstract class SlowMovementGoal<T extends CorruptedHostileEntity>
	extends EntityAttributeModifierGoal<T>
{
	protected final ResourceLocation attributeId = ResourceLocation.withDefaultNamespace("speed");
	protected final EntityAttributeModifier attributeModifier =
		this.getAttributeModifier(
			this.attributeId, -2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final EntityAttributeInstance entityAttributeInstance;

	public SlowMovementGoal(T entity) {
		super(entity);

		this.entityAttributeInstance = entity.getAttributeInstance(
			EntityAttributes.GENERIC_MOVEMENT_SPEED
		);
	}

	protected void addSpeedModifier() {
		this.addModifier(this.entityAttributeInstance, this.attributeId, this.attributeModifier);
	}

	protected void removeSpeedModifier() {
		this.removeModifier(this.entityAttributeInstance, this.attributeModifier);
	}
}
