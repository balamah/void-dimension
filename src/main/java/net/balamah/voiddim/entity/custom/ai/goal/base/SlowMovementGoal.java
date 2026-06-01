package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public abstract class SlowMovementGoal<T extends CorruptedHostileEntity>
	extends EntityAttributeModifierGoal<T>
{
	protected final ResourceLocation attributeId = ResourceLocation.withDefaultNamespace("speed");
	protected final AttributeModifier attributeModifier =
		this.getAttributeModifier(
			this.attributeId, -2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final AttributeInstance entityAttributeInstance;

	public SlowMovementGoal(T entity) {
		super(entity);

		this.entityAttributeInstance = entity.getAttribute(
			Attributes.MOVEMENT_SPEED
		);
	}

	protected void addSpeedModifier() {
		this.addModifier(this.entityAttributeInstance, this.attributeId, this.attributeModifier);
	}

	protected void removeSpeedModifier() {
		this.removeModifier(this.entityAttributeInstance, this.attributeModifier);
	}
}
