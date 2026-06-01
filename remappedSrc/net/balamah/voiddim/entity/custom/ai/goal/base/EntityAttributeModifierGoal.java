package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.resources.ResourceLocation;

public abstract class EntityAttributeModifierGoal<T extends CorruptedHostileEntity>
	extends TickingGoal<T>
{
	public EntityAttributeModifierGoal(T entity) {
		super(entity);
	}

	protected EntityAttributeModifier getAttributeModifier(
		ResourceLocation attributeId, double value, EntityAttributeModifier.Operation operation
	) {
		return new EntityAttributeModifier(attributeId, value, operation);
	}

	protected void addModifier(
		EntityAttributeInstance entityAttributeInstance, ResourceLocation attributeId,
		EntityAttributeModifier modifier
	) {
		if (!entityAttributeInstance.hasModifier(attributeId)) {
			entityAttributeInstance.addTemporaryModifier(modifier);
		}
	}

	protected void removeModifier(
		EntityAttributeInstance entityAttributeInstance, EntityAttributeModifier modifier
	) {
		entityAttributeInstance.removeModifier(modifier);
	}
}
