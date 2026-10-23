package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.resources.Identifier;

public abstract class EntityAttributeModifierGoal<T extends ModHostileEntity>
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
