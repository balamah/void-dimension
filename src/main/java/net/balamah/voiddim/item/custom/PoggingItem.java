package net.balamah.voiddim.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class PoggingItem extends Item {
	protected float jumpStrength;

	public PoggingItem(Properties properties, float jumpStrength) {
		super(properties);

		this.jumpStrength = jumpStrength;
	}

	@Override
	public void postHurtEnemy(ItemStack itemStack, LivingEntity victim, LivingEntity attacker) {
		super.postHurtEnemy(itemStack, victim, attacker);

		boolean isFalling = attacker.getDeltaMovement().y < 0;
		if (attacker.getY() > victim.getY() && isFalling) {
			attacker.setDeltaMovement(
				attacker.getDeltaMovement().x,
				this.jumpStrength,
				attacker.getDeltaMovement().z
			);

			attacker.causeFallDamage(attacker.fallDistance, 0.0F, attacker.damageSources().fall());
		}
	}
}
