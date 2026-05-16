package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ServerLevelAccessor;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
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

	protected boolean shouldBlock;
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
				.add(Attributes.MOVEMENT_SPEED, 0.2F)
				.add(Attributes.ATTACK_DAMAGE, 8.0F)
				.add(Attributes.STEP_HEIGHT, 1.0)
				.add(Attributes.MAX_HEALTH, 35);
	}

	@Override
	public void tick() {
		super.tick();

		Level world = this.level();
		LivingEntity target = this.getTarget();

		if (target != null) {
			shouldBlock = true;
		} else {
			shouldBlock = false;
		}

		if (shouldBlock) {
			this.startUsingItem(InteractionHand.OFF_HAND);
			world.broadcastEntityEvent(this, ModEntityStatuses.DEFEND);
		} else {
			this.stopUsingItem();
			world.broadcastEntityEvent(this, ModEntityStatuses.STOP_DEFEND);
		}
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.DEFEND:
				this.useShieldAnimationState.start(this.tickCount);
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
		EntitySpawnReason spawnReason,
		@Nullable SpawnGroupData entityData
	) {
		entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);

		this.populateDefaultEquipmentSlots(this.random, difficulty);
		this.populateDefaultEquipmentEnchantments(world, this.random, difficulty);

		return entityData;
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

			// A magic number, don't touch
			this.attackInterval = 8;
		}

		return result;
	}

	@Override
	protected void populateDefaultEquipmentSlots(
		RandomSource random, DifficultyInstance localDifficulty
	) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
	}

	@Override
	protected void customServerAiStep(ServerLevel world) {
		super.customServerAiStep(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.broadcastEntityEvent(this, ModEntityStatuses.STOP_ATTACK);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}
	}
}
