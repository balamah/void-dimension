package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ServerLevelAccessor;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class VoidBoundServantEntity extends CorruptedHostileEntity {
	public final AnimationState suicideAnimationState = new AnimationState();
	public final AnimationState useShieldAnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();
	public final AnimationState attack3AnimationState = new AnimationState();
	public final AnimationState attack4AnimationState = new AnimationState();

	protected boolean hasShield;
	protected int shieldDisabledTicks = 0;
	protected boolean isDefending;
	protected int attackInterval;

	protected AnimationState[] attackAnimations = {
		this.attack1AnimationState, this.attack2AnimationState, this.attack3AnimationState,
		this.attack4AnimationState
	};

	public VoidBoundServantEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.ATTACK_DAMAGE, 2.5F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 30);
	}

	@Override
	public void tick() {
		super.tick();

		LivingEntity target = this.getTarget();
		boolean shouldDefend = target != null && this.shieldDisabledTicks == 0 && this.hasShield;

		if (shouldDefend != this.isDefending) {
			this.isDefending = shouldDefend;

			if (this.isDefending) {
				this.startUsingItem(InteractionHand.OFF_HAND);
				this.level().broadcastEntityEvent(this, ModEntityStatuses.DEFEND);
			} else {
				this.stopUsingItem();
				this.level().broadcastEntityEvent(this, ModEntityStatuses.STOP_DEFEND);
			}
		}

		if (this.shieldDisabledTicks > 0) {
			this.shieldDisabledTicks--;
		}
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.DEFEND:
				this.useShieldAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.STOP_DEFEND:
				this.useShieldAnimationState.stop();
				break;
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.attackAnimations);
				this.playRandomAnimation(this.attackAnimations);
				break;
			case ModEntityStatuses.SUICIDE:
				this.suicideAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.attackAnimations);
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(
		ServerLevelAccessor world,
		DifficultyInstance difficulty,
		MobSpawnType spawnReason,
		@Nullable SpawnGroupData entityData
	) {
		entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);

		this.populateDefaultEquipmentSlots(this.random, difficulty);
		this.populateDefaultEquipmentEnchantments(world, this.random, difficulty);

		return entityData;
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		boolean result = super.doHurtTarget(target);
		
		if (result) {
			this.level().broadcastEntityEvent(this, ModEntityStatuses.ATTACK);
			this.playSound(ModSounds.VOID_BOUND_SERVANT_ATTACK);

			// A magic number, don't touch
			this.attackInterval = 8;
		}

		return result;
	}

	/*
	 * NOTE Methods that will may help in the future:
	 * boolean isBlocking()
	 * @Nullable ItemStack getItemBlockingWith()	
	*/
	@Override
	public boolean hurt(DamageSource source, float amount) {
		Entity attacker = source.getEntity();

		if (this.isBlocking() && attacker instanceof LivingEntity living) {
			ItemStack weapon = living.getMainHandItem();

			if (weapon.getItem() instanceof AxeItem) {
				this.stopUsingItem();
				this.isDefending = false;

				this.level().broadcastEntityEvent(this, ModEntityStatuses.STOP_DEFEND);
				this.playSound(SoundEvents.SHIELD_BREAK);

				this.shieldDisabledTicks = 100;

				return super.hurt(source, amount);
			}
		}

		if (this.isBlocking()) {
			this.playSound(SoundEvents.SHIELD_BLOCK);

			return false;
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean isLeftHanded() {
		return false;
	}

	@Override
	protected void populateDefaultEquipmentSlots(
		RandomSource random, DifficultyInstance localDifficulty
	) {
		Item[] weaponPool = {
			Items.IRON_SWORD, Items.IRON_AXE, Items.DIAMOND_AXE,
			Items.DIAMOND_SWORD, Items.NETHERITE_SWORD
		};

		int chosenWeaponIndex = random.nextInt(weaponPool.length);
		Item chosenWeapon = weaponPool[chosenWeaponIndex];

		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(chosenWeapon));

		if (random.nextInt(2) == 1) {
			this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));

			this.hasShield = true;
		}
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.getTarget() == null || this.attackInterval == 0) {
			this.level().broadcastEntityEvent(this, ModEntityStatuses.STOP_ATTACK);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.SMALL_ARMOR_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.VOID_BOUND_SERVANT_DEATH;
	}
}
