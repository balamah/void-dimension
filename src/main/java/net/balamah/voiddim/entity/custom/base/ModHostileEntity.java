package net.balamah.voiddim.entity.custom.base;

import net.minecraft.world.attribute.EnvironmentAttributes;
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
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import java.util.Random;

import net.balamah.voiddim.entity.custom.ai.goal.ModNearestEntityTargetGoal;
import net.balamah.voiddim.entity.custom.ai.goal.VoidHostileEntityAttackGoal;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class ModHostileEntity extends Monster {
	public boolean breaksShield = false;
	public int attackCount;

	protected boolean stopAttacks = false;

	public ModHostileEntity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.isAlive() && this.isSunBurnTick()) {
			EquipmentSlot equipmentSlot = this.sunProtectionSlot();
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
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean hit = super.doHurtTarget(world, target);

		if (hit &&
			target instanceof Player playerEntity &&
			this.level() instanceof ServerLevel &&
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
	public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
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

	protected boolean burnsInDaylight() {
		return true;
	}

	protected void initTargets() {
		/*
		 * TODO: Create ModNearestPlayerTargetGoal.
		 * The goal focuses on the nearest player
		 */
		this.targetSelector.addGoal(
			0, new ModNearestEntityTargetGoal<Player>(this, 32, Player.class)
		);
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

	@SuppressWarnings("deprecation")
	protected boolean isSunBurnTick() {
		Level world = this.level();
		Boolean doMonstersBurn = world.environmentAttributes()
			.getValue(
				EnvironmentAttributes.MONSTERS_BURN, this.position()
			);

		if (!world.isClientSide() && doMonstersBurn) {
			float lightLevel = this.getLightLevelDependentMagicValue();
			BlockPos blockPos = BlockPos.containing(this.getX(), this.getEyeY(), this.getZ());
			boolean isWet = this.isInWaterOrRain() || this.isInPowderSnow || this.wasInPowderSnow;
			if (lightLevel > 0.5F && this.random.nextFloat() * 30.0F < (lightLevel - 0.4F) * 2.0F && !isWet && this.level().canSeeSky(blockPos)) {
				return true;
			}
		}

		return false;
	}
}
