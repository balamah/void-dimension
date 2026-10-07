package net.balamah.voiddim.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

public class PoggingItem extends Item {
	protected float jumpStrength;

	public PoggingItem(Properties properties, float jumpStrength) {
		super(properties);

		this.jumpStrength = jumpStrength;
	}

	@Override
	public void postHurtEnemy(ItemStack itemStack, LivingEntity victim, LivingEntity attacker) {
		super.postHurtEnemy(itemStack, victim, attacker);

		if (attacker.getY() > victim.getY()) {
			attacker.addDeltaMovement(new Vec3(0, this.jumpStrength, 0));
		}
	}
}
