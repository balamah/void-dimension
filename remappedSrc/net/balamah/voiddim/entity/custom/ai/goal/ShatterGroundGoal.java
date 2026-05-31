package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.world.explosion.ExplosionIgnoreEntitiesBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.ShatterGroundUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.ModEntities;

public class ShatterGroundGoal<T extends BossEntity & ShatterGroundUser>
	extends SlowMovementGoal<T>
{
	protected final int endTick = 25;

	public ShatterGroundGoal(T entity) {
	    super(entity);
	}

	@Override
	public void start() {
		this.addSpeedModifier();
		this.entity.setStopAttacks(true);
		this.sendEntityStatus(ModEntityStatuses.GROUND_MANIPULATION_BEGIN);
	}

	@Override
	public boolean canStart() {
		float maxHP = this.entity.getMaxHealth();
		float middleHP = maxHP / 2;
		float currentHP = this.entity.getHealth();

		return this.entity.getTarget() != null &&
			!this.entity.areAttacksStopped() &&
			this.entity.getShatterGroundTicks() == 0 &&
			currentHP < middleHP
			;
	}

	@Override
	public void stop() {
		super.stop();

		this.removeSpeedModifier();
		this.entity.setStopAttacks(false);
		this.entity.setShatterGroundTicks(
			this.entity.getShatterGroundCooldown() +
			this.entity.getRandom().nextInt(100)
		);

		this.sendEntityStatus(ModEntityStatuses.GROUND_MANIPULATION_END);
	}

	@Override
	public boolean shouldContinue() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) <= 20 &&
			this.tick <= this.endTick;
	}

	@Override
	public void tick() {
		this.tick++;

		if (this.tick == this.endTick) {
			this.sendEntityStatus(ModEntityStatuses.GROUND_MANIPULATION_PROCESS);

			ExplosionBehavior behavior = new ExplosionIgnoreEntitiesBehavior(
				this.entity.getType(), ModEntities.SHATTERED_SENTINEL
			);

			this.world.createExplosion(
				this.entity,
				Explosion.createDamageSource(this.world, this.entity),
				behavior,
				this.entity.getX(),
				this.entity.getY(),
				this.entity.getZ(),
				8.0F,
				false,
				World.ExplosionSourceType.MOB
			);
		}
	}
}
