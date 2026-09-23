package net.balamah.voiddim.entity.custom;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class HollowKnightEntity extends CorruptedHostileEntity {
	public final AnimationState walkingState = new AnimationState();
	public final AnimationState attack1State = new AnimationState();
	public final AnimationState attack2State = new AnimationState();
	public final AnimationState attack3State = new AnimationState();
	public final AnimationState attack4State = new AnimationState();
	public final AnimationState vengefulSpiritState = new AnimationState();

	protected AnimationState[] normalAttackAnimations = {
		this.attack1State, this.attack2State, this.attack3State, this.attack4State
	};

	protected int attackInterval;

	public HollowKnightEntity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.4F)
			.add(Attributes.ATTACK_DAMAGE, 13.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 55);
	}

	@Override
	public void handleEntityEvent(byte id) {
		super.handleEntityEvent(id);

		switch (id) {
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				break;
			default: super.handleEntityEvent(id);
				break;
		}
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

			// A magic number, don't touch
			this.attackInterval = 13;
		}

		return result;
	}

	@Override
	public void aiStep() {
		this.updateSwingTime();
		this.updateNoActionTime();
		super.aiStep();
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

	@Override
	protected void populateDefaultEquipmentSlots(
		RandomSource random, DifficultyInstance difficulty
	) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
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
}
