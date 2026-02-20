package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.item.Item;

public class HollowedAlphaSteveEntity extends PathAwareEntity {
	public HollowedAlphaSteveEntity(
		EntityType<? extends PathAwareEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return PathAwareEntity.createMobAttributes()
			.add(EntityAttributes.MAX_HEALTH, 35)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.4F);
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

	@Override
	protected void initGoals() {
		// TODO Auto-generated method stub
		super.initGoals();

		this.goalSelector.add(0, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 15.0F));
		this.goalSelector.add(2, new LookAroundGoal(this));
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
			if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && this.getEntityWorld().isSkyVisible(blockPos)) {
				return true;
			}
		}

		return false;
	}
}
