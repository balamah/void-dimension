package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.ai.goal.FindPassiveEntitiesGoal;
import net.balamah.voiddim.interfaces.MinecraftEntityDongle;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class VoidMawEntity extends Phantom implements MinecraftEntityDongle {
	public AnimationState attackAnimationState = new AnimationState();

	public VoidMawEntity(EntityType<? extends Phantom> entityType, Level world) {
		super(entityType, world);

		this.xpReward = 15;
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();

		this.setNoGravity(true);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.ATTACK_DAMAGE, 6.0F)
			.add(Attributes.MAX_HEALTH, 14.5)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

		return super.doHurtTarget(world, target);
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.attackAnimationState.start(this.tickCount);
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.targetSelector.addGoal(2, new FindPassiveEntitiesGoal<VoidMawEntity>(this));
	}

	public boolean testPredicate(
		ServerLevel world, LivingEntity target, TargetingConditions predicate
	) {
		return predicate.test(world, this, target);
	}
}
