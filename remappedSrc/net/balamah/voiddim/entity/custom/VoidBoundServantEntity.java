package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.sound.ModSounds;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntitySpawnReason;

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

	public VoidBoundServantEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5F)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 30);
	}

	@Override
	public void tick() {
		super.tick();

		LivingEntity target = this.getTarget();
		boolean shouldDefend = target != null && this.shieldDisabledTicks == 0 && this.hasShield;

		if (shouldDefend != this.isDefending) {
			this.isDefending = shouldDefend;

			if (this.isDefending) {
				this.setCurrentHand(Hand.OFF_HAND);
				this.getWorld().sendEntityStatus(this, ModEntityStatuses.DEFEND);
			} else {
				this.clearActiveItem();
				this.getWorld().sendEntityStatus(this, ModEntityStatuses.STOP_DEFEND);
			}
		}

		if (this.shieldDisabledTicks > 0) {
			this.shieldDisabledTicks--;
		}
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.DEFEND:
				this.useShieldAnimationState.start(this.age);
				break;
			case ModEntityStatuses.STOP_DEFEND:
				this.useShieldAnimationState.stop();
				break;
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.attackAnimations);
				this.playRandomAnimation(this.attackAnimations);
				break;
			case ModEntityStatuses.SUICIDE:
				this.suicideAnimationState.start(this.age);
				break;
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.attackAnimations);
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	@Nullable
	public EntityData finalizeSpawn(
		ServerWorldAccess world,
		LocalDifficulty difficulty,
		EntitySpawnReason spawnReason,
		@Nullable EntityData entityData
	) {
		entityData = super.initialize(world, difficulty, spawnReason, entityData);

		this.initEquipment(this.random, difficulty);
		this.updateEnchantments(world, this.random, difficulty);

		return entityData;
	}

	@Override
	public boolean doHurtTarget(ServerWorld world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.sendEntityStatus(this, ModEntityStatuses.ATTACK);
			this.playSoundIfNotSilent(ModSounds.VOID_BOUND_SERVANT_ATTACK);

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
	public boolean hurtServer(ServerWorld world, DamageSource source, float amount) {
		Entity attacker = source.getAttacker();

		if (this.isBlocking() && attacker instanceof LivingEntity living) {
			ItemStack weapon = living.getMainHandStack();

			if (weapon.getItem() instanceof AxeItem) {
				this.clearActiveItem();
				this.isDefending = false;

				this.getWorld().sendEntityStatus(this, ModEntityStatuses.STOP_DEFEND);
				this.playSoundIfNotSilent(SoundEvents.ITEM_SHIELD_BREAK.value());

				this.shieldDisabledTicks = 100;

				return super.hurtServer(world, source, amount);
			}
		}

		if (this.isBlocking()) {
			this.playSoundIfNotSilent(SoundEvents.ITEM_SHIELD_BLOCK.value());

			return false;
		}

		return super.hurtServer(world, source, amount);
	}

	@Override
	public boolean isLeftHanded() {
		return false;
	}

	@Override
	protected void initEquipment(
		Random random, LocalDifficulty localDifficulty
	) {
		Item[] weaponPool = {
			Items.IRON_SWORD, Items.IRON_AXE, Items.DIAMOND_AXE,
			Items.DIAMOND_SWORD, Items.NETHERITE_SWORD
		};

		int chosenWeaponIndex = random.nextInt(weaponPool.length);
		Item chosenWeapon = weaponPool[chosenWeaponIndex];

		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(chosenWeapon));

		if (random.nextInt(2) == 1) {
			this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));

			this.hasShield = true;
		}
	}

	@Override
	protected void customServerAiStep(ServerWorld world) {
		super.mobTick(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.sendEntityStatus(this, ModEntityStatuses.STOP_ATTACK);
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
