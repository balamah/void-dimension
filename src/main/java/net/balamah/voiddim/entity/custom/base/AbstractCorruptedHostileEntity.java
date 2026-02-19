package net.balamah.voiddim.entity.custom.base;

import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.item.Item;

import java.util.Random;

import net.balamah.voiddim.entity.custom.ai.goal.VoidHostileEntityAttackGoal;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class AbstractCorruptedHostileEntity extends HostileEntity {
	public boolean breaksShield = false;
	public int attackCount;

	protected boolean stopAttacks = false;

	public AbstractCorruptedHostileEntity(
		EntityType<? extends HostileEntity> entityType, World world
	) {
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

	public boolean areAttacksStopped() {
		return this.stopAttacks;
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);

		if (hit &&
			target instanceof PlayerEntity playerEntity &&
			this.getEntityWorld() instanceof ServerWorld serverWorld &&
			this.breaksShield
		) {
			McCodeHelper.disableShield(playerEntity);
		}

		return hit;
	}

	public void setStopAttacks(boolean stopAttacks) {
		this.stopAttacks = stopAttacks;
	}

	protected boolean burnsInDaylight() {
		return true;
	}

	protected void initTargets() {
		this.targetSelector.add(0, this.getTargetGoal(PlayerEntity.class));
		this.targetSelector.add(2, this.getTargetGoal(PassiveEntity.class));
	}

	protected void initBasicGoals() {
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAtEntityGoal(this, PassiveEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));

		this.targetSelector.add(
			1, new RevengeGoal(this, BossEntity.class).setGroupRevenge(Entity.class)
		);
	}

	protected void initAttackGoals() {
		this.goalSelector.add(1, new VoidHostileEntityAttackGoal(this, 1.0, false));
		this.goalSelector.add(1, new VoidHostileEntityAttackGoal(this, 1.0, false));
	}

	@Override
	protected void initGoals() {
		this.initTargets();
		this.initBasicGoals();
		this.initAttackGoals();
	}

	protected void playSoundCurrentLocation(SoundEvent sound) {
		this.getEntityWorld().playSound(
			null,
			this.getBlockPos(),
			sound,
			SoundCategory.HOSTILE,
			0.5F,
			1.0F
		);
	}

	protected void playRandomSound(SoundEvent[] sounds) {
		Random random = new Random();

		this.playSoundCurrentLocation(sounds[random.nextInt(sounds.length)]);
	}

	public double getChargeY() {
		return this.getY() + this.getHeight() / 2.0F + 0.3F;
	}

	protected Goal getTargetGoal(Class<?> entityTarget) {
		return new ActiveTargetGoal(
			this, entityTarget, 10, true, false,
			(entity, world) -> Math.abs(entity.getY() - this.getY()) <= 25.0
		);
	}

	protected void playRandomAnimation(AnimationState[] animations) {
		this.stopAnimations(animations);

		AnimationState state = animations[this.random.nextInt(animations.length)];

		state.start(this.age);
	}

	protected void stopAnimations(AnimationState[] animations) {
		for (AnimationState animation : animations) {
			animation.stop();
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
			if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && this.getEntityWorld().isSkyVisible(blockPos)) {
				return true;
			}
		}

		return false;
	}
}
