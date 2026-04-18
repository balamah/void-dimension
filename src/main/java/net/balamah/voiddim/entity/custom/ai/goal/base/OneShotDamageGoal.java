package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;

public abstract class OneShotDamageGoal<T extends CorruptedHostileEntity>
	extends EntityAttributeModifierGoal<T>
{
	protected final Identifier attributeId = Identifier.ofVanilla("attacking");
	protected final EntityAttributeModifier attributeModifier = this.getAttributeModifier(
		this.attributeId, 10.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
	);

	protected final EntityAttributeInstance attributeInstance;

	public OneShotDamageGoal(T entity) {
		super(entity);

		this.attributeInstance = this.entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
	}
}
