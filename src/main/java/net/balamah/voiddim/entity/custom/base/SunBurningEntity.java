package net.balamah.voiddim.entity.custom.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class SunBurningEntity extends PathfinderMob {
	public SunBurningEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.isAlive() && this.isSunBurnTick()) {
			EquipmentSlot equipmentSlot = EquipmentSlot.HEAD;
			ItemStack itemStack = this.getItemBySlot(equipmentSlot);
			if (!itemStack.isEmpty()) {
				if (itemStack.isDamageableItem()) {
					Item item = itemStack.getItem();
					itemStack.setDamageValue(itemStack.getDamageValue() + this.random.nextInt(2));
					if (itemStack.getDamageValue() >= itemStack.getMaxDamage()) {
						this.onEquippedItemBroken(item, equipmentSlot);
						this.setItemSlot(equipmentSlot, ItemStack.EMPTY);
					}
				}
			} else {
				this.igniteForSeconds(8.0F);
			}
		}
	}

	protected boolean isSunBurnTick() {
		Level world = this.level();
		if (!world.isClientSide() && world.dimensionType().hasSkyLight()) {
			float f = this.getLightLevelDependentMagicValue();
			BlockPos blockPos = BlockPos.containing(this.getX(), this.getEyeY(), this.getZ());
			boolean bl = this.isInWaterOrRain() || this.isInPowderSnow || this.wasInPowderSnow;
			if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F &&
				!bl && this.level().canSeeSky(blockPos))
			{
				return true;
			}
		}

		return false;
	}

	protected abstract void registerGoals();
}
