package net.balamah.voiddim.entity.custom.base;

import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.item.Item;

public abstract class SunBurningEntity extends PathAwareEntity {
	public SunBurningEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();

		if (this.isAlive() && this.isAffectedByDaylight()) {
			EquipmentSlot equipmentSlot = this.getDaylightProtectionSlot();
			ItemStack itemStack = this.getEquippedStack(equipmentSlot);
			if (!itemStack.isEmpty()) {
				if (itemStack.isDamageable()) {
					Item item = itemStack.getItem();
					itemStack.setDamage(itemStack.getDamage() + this.random.nextInt(2));
					if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
						this.sendEquipmentBreakStatus(item, equipmentSlot);
						this.equipStack(equipmentSlot, ItemStack.EMPTY);
					}
				}
			} else {
				this.setOnFireFor(8.0F);
			}
		}
	}

	protected boolean isAffectedByDaylight() {
		World world = this.getEntityWorld();
		Boolean doMonstersBurn = world.getEnvironmentAttributes()
			.getAttributeValue(
				EnvironmentAttributes.MONSTERS_BURN_GAMEPLAY, this.getEntityPos()
			);

		if (!world.isClient() && doMonstersBurn) {
			float f = this.getBrightnessAtEyes();
			BlockPos blockPos = BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ());
			boolean bl = this.isTouchingWaterOrRain() || this.inPowderSnow || this.wasInPowderSnow;
			if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F &&
				!bl && this.getEntityWorld().isSkyVisible(blockPos))
			{
				return true;
			}
		}

		return false;
	}

	protected abstract void initGoals();
}
