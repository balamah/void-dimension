package net.balamah.voiddim.entity.custom.base;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import java.util.Random;

import net.balamah.voiddim.entity.custom.ai.goal.VoidHostileEntityAttackGoal;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class CorruptedHostileEntity extends Monster {
	public boolean breaksShield = false;
	public int attackCount;

	protected boolean stopAttacks = false;

	public CorruptedHostileEntity(
		EntityType<? extends Monster> entityType, Level world
	) {
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

	public boolean areAttacksStopped() {
		return this.stopAttacks;
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		boolean hit = super.doHurtTarget(target);

		if (hit &&
			target instanceof Player playerEntity &&
			this.level() instanceof ServerLevel serverWorld &&
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
	public boolean hurt(DamageSource source, float amount) {
		boolean result = super.hurt(source, amount);

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

	public void corruptBlock(Level world, BlockPos blockPos) {
		Block block = McCodeHelper.getBlock(world, blockPos);

		if (McCodeHelper.isBlockReplaceable(block)) {
			world.setBlockAndUpdate(blockPos, ModBlocks.CORRUPT_BLOCK.defaultBlockState());
		}
	}

	protected boolean burnsInDaylight() {
		return true;
	}

	protected void initTargets() {
		this.targetSelector.addGoal(0, McCodeHelper.getTargetGoal(this, Player.class));
		this.targetSelector.addGoal(2, McCodeHelper.getTargetGoal(this, AgeableMob.class));
	}

	protected void initBasicGoals() {
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, AgeableMob.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(
			1, new HurtByTargetGoal(this, BossEntity.class).setAlertOthers(Entity.class)
		);
	}

	protected void initAttackGoals() {
		this.goalSelector.addGoal(1, new VoidHostileEntityAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(1, new VoidHostileEntityAttackGoal(this, 1.0, false));
	}

	@Override
	protected void registerGoals() {
		this.initTargets();
		this.initBasicGoals();
		this.initAttackGoals();
	}

	protected void playSoundCurrentLocation(SoundEvent sound) {
		this.level().playSound(
			null,
			this.blockPosition(),
			sound,
			SoundSource.HOSTILE,
			0.5F,
			1.0F
		);
	}

	protected void playRandomSound(SoundEvent[] sounds) {
		Random random = new Random();

		this.playSoundCurrentLocation(sounds[random.nextInt(sounds.length)]);
	}

	public double getChargeY() {
		return this.getY() + this.getBbHeight() / 2.0F + 0.3F;
	}

	protected void playRandomAnimation(AnimationState[] animations) {
		this.stopAnimations(animations);

		AnimationState state = animations[this.random.nextInt(animations.length)];

		state.start(this.tickCount);
	}

	protected void stopAnimations(AnimationState[] animations) {
		for (AnimationState animation : animations) {
			animation.stop();
		}
	}

	protected boolean isSunBurnTick() {
		Level world = this.level();
		if (!world.isClientSide() && world.dimensionType().hasSkyLight()) {
			float f = this.getLightLevelDependentMagicValue();
			BlockPos blockPos = BlockPos.containing(this.getX(), this.getEyeY(), this.getZ());
			boolean bl = this.isInWaterOrRain() || this.isInPowderSnow || this.wasInPowderSnow;
			if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && this.level().canSeeSky(blockPos)) {
				return true;
			}
		}

		return false;
	}
}
