package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public abstract class EntityAttributeModifierGoal<T extends CorruptedHostileEntity>
	extends TickingGoal<T>
{
	public EntityAttributeModifierGoal(T entity) {
		super(entity);
	}

	protected AttributeModifier getAttributeModifier(
		Identifier attributeId, double value, AttributeModifier.Operation operation
	) {
		return new AttributeModifier(attributeId, value, operation);
	}

	protected void addModifier(
		AttributeInstance entityAttributeInstance, Identifier attributeId,
		AttributeModifier modifier
	) {
		if (!entityAttributeInstance.hasModifier(attributeId)) {
			entityAttributeInstance.addTransientModifier(modifier);
		}
	}

	protected void removeModifier(
		AttributeInstance entityAttributeInstance, AttributeModifier modifier
	) {
		entityAttributeInstance.removeModifier(modifier);
	}
}
