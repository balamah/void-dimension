package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.FindPassiveEntitiesGoal;
import net.balamah.voiddim.interfaces.MinecraftEntityDongle;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class VoidMawEntity extends PhantomEntity implements MinecraftEntityDongle {
	public AnimationState attackAnimationState = new AnimationState();

	public VoidMawEntity(EntityType<? extends PhantomEntity> entityType, World world) {
		super(entityType, world);

		this.experiencePoints = 15;
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();

		this.setNoGravity(true);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 64)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.ATTACK_DAMAGE, 6.0F)
			.add(EntityAttributes.MAX_HEALTH, 14.5)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		world.sendEntityStatus(this, ModEntityStatuses.ATTACK);

		return super.tryAttack(world, target);
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.attackAnimationState.start(this.age);
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		this.targetSelector.add(2, new FindPassiveEntitiesGoal<VoidMawEntity>(this));
	}

	public boolean testPredicate(
		ServerWorld world, LivingEntity target, TargetPredicate predicate
	) {
		return predicate.test(world, this, target);
	}
}
