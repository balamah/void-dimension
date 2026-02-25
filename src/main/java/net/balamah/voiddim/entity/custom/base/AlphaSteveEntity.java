package net.balamah.voiddim.entity.custom.base;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.item.Item;

import net.balamah.voiddim.entity.custom.ai.goal.RandomAttackGoal;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class AlphaSteveEntity extends PathAwareEntity {
	protected int ticks;

	public AlphaSteveEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return PathAwareEntity.createMobAttributes()
			.add(EntityAttributes.MAX_HEALTH, 20)
			.add(EntityAttributes.STEP_HEIGHT, 1.0)
			.add(EntityAttributes.JUMP_STRENGTH, 0.4f)
			.add(EntityAttributes.ATTACK_DAMAGE, 7.6f)
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
		super.initGoals();

		this.goalSelector.add(3, new RandomAttackGoal(this, 500));

		this.targetSelector.add(0, McCodeHelper.getTargetGoal(this, PlayerEntity.class));
		this.targetSelector.add(2, McCodeHelper.getTargetGoal(this, PassiveEntity.class));
	}

	@Override
	public void tick() {
		super.tick();

		ticks++;

		Vec3d movement = this.getMovement();
		boolean isMoving = movement.x != 0 || movement.z != 0;
		if (ticks % 15 == 0 && this.isOnGround() && isMoving) {
			this.jump();
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

	@Override
	protected abstract SoundEvent getHurtSound(DamageSource source);

	@Override
	protected abstract SoundEvent getDeathSound();
}
