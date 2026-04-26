package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public abstract class OneShotDamageGoal<T extends CorruptedHostileEntity>
	extends EntityAttributeModifierGoal<T>
{
	protected final Identifier attributeId = Identifier.withDefaultNamespace("attacking");
	protected final AttributeModifier attributeModifier = this.getAttributeModifier(
		this.attributeId, 10.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
	);

	protected final AttributeInstance attributeInstance;

	public OneShotDamageGoal(T entity) {
		super(entity);

		this.attributeInstance = this.entity.getAttribute(Attributes.ATTACK_DAMAGE);
	}
}
