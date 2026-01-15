package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.mob.MobEntity;
import net.balamah.voiddim.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.ModEntities;

public class MantisEntity extends AnimalEntity {
	public final AnimationState idleAnimationState = new AnimationState();
	protected int idleAnimationTimeout = 0;

	public MantisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		// The lower number priority, the higher in the game priority will be.
		// 0 Is the hightest

        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        // this.goalSelector.add(2, new TemptGoal(this, 1.25D,
											   // Ingredient.ofItems(ModItems.VOID_SHARD),
											   // false));

        this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));

        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.MAX_HEALTH, 18)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.35)
			.add(EntityAttributes.ATTACK_DAMAGE, 1)
			.add(EntityAttributes.FOLLOW_RANGE, 20);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getEntityWorld().isClient()) this.setupAnimationStates();
	}

	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return ModEntities.MANTIS.create(world, SpawnReason.BREEDING);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.isOf(ModItems.VOID_SHARD);
	}

	protected void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 40;
			this.idleAnimationState.start(this.age);
		} else {
			--this.idleAnimationTimeout;
		}
	}
}
