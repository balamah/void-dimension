package net.balamah.voiddim.interfaces;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.Identifier;

public interface ScreamBeforeAttackUser extends RoarUser {
	public int getScreamBeforeAttackCooldown();
	public int getScreamBeforeAttackTicks();
	public void setScreamBeforeAttackTicks(int ticks);

	default public AttributeModifier getRoarEndAttributeModifier() {
		return new AttributeModifier(
			Identifier.withDefaultNamespace("end"), 0.4, AttributeModifier.Operation.ADD_VALUE
		);
	}

	default public AttributeInstance getRoarAttributeInstance(LivingEntity entity) {
		return entity.getAttribute(Attributes.MOVEMENT_SPEED);
	}
}
