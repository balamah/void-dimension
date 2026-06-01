package net.balamah.voiddim.entity.custom.base;

import net.minecraft.block.Block;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributes;
import java.util.Random;

import net.balamah.voiddim.entity.custom.ai.goal.VoidHostileEntityAttackGoal;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class CorruptedHostileEntity extends HostileEntity {
	public boolean breaksShield = false;
	public int attackCount;

	protected boolean stopAttacks = false;

	public CorruptedHostileEntity(
		EntityType<? extends HostileEntity> entityType, World world
	) {
		super(entityType, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();

		if (this.isAlive() && this.isAffectedByDaylight()) {
			EquipmentSlot equipmentSlot = this.sunProtectionSlot();
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
	public boolean doHurtTarget(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);

		if (hit &&
			target instanceof PlayerEntity playerEntity &&
			this.getWorld() instanceof ServerWorld serverWorld &&
			this.breaksShield
		) {
			McCodeHelper.disableShield(playerEntity);
		}

		return hit;
	}

	public void setStopAttacks(boolean stopAttacks) {
		this.stopAttacks = stopAttacks;
	}

	@Override
	public boolean hurtServer(ServerWorld world, DamageSource source, float amount) {
		boolean result = super.hurtServer(world, source, amount);

		if (result) {
			this.attackCount++;
		}

		return result;
	}

	public boolean isSecondPhase() {
		float maxHP = this.getMaxHealth();
		float middleHP = maxHP / 2;
		float currentHP = this.getHealth();

		return currentHP < middleHP;
	}

	public void corruptBlock(World world, BlockPos blockPos) {
		Block block = McCodeHelper.getBlock(world, blockPos);

		if (McCodeHelper.isBlockReplaceable(block)) {
			world.setBlockState(blockPos, ModBlocks.CORRUPT_BLOCK.getDefaultState());
		}
	}

	protected boolean burnsInDaylight() {
		return true;
	}

	protected void initTargets() {
		this.targetSelector.add(0, McCodeHelper.getTargetGoal(this, PlayerEntity.class));
		this.targetSelector.add(2, McCodeHelper.getTargetGoal(this, PassiveEntity.class));
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
		this.getWorld().playSound(
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
		World world = this.getWorld();
		Boolean doMonstersBurn = world.environmentAttributes()
			.getValue(
				EnvironmentAttributes.MONSTERS_BURN, this.getPos()
			);

		if (!world.isClient() && doMonstersBurn) {
			float f = this.getBrightnessAtEyes();
			BlockPos blockPos = BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ());
			boolean bl = this.isTouchingWaterOrRain() || this.inPowderSnow || this.wasInPowderSnow;
			if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && this.getWorld().isSkyVisible(blockPos)) {
				return true;
			}
		}

		return false;
	}
}
