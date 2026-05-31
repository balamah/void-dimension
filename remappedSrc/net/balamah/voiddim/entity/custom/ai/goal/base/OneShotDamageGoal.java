package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.resources.ResourceLocation;

public abstract class OneShotDamageGoal<T extends CorruptedHostileEntity>
	extends EntityAttributeModifierGoal<T>
{
	protected final ResourceLocation attributeId = ResourceLocation.withDefaultNamespace("attacking");
	protected EntityAttributeModifier attributeModifier = this.getAttributeModifier(
		this.attributeId, 10.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
	);

	protected final EntityAttributeInstance attributeInstance;

	public OneShotDamageGoal(T entity) {
		super(entity);

		this.attributeInstance = this.entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
	}
}
