package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.balamah.voiddim.world.explosion.ExplosionIgnoreEntitiesBehavior;
import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.ModEntities;

public class ShatterGroundGoal extends Goal {
	protected final ShatteredSentinelMasterEntity entity;

	protected final Identifier attributeId = Identifier.ofVanilla("speed");
	protected final EntityAttributeModifier attributeModifier =
		new EntityAttributeModifier(
			this.attributeId, -2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final EntityAttributeInstance entityAttributeInstance;

	protected final int endTick = 25;
	protected int tick;

	public ShatterGroundGoal(ShatteredSentinelMasterEntity entity) {
	    this.entity = entity;
		this.entityAttributeInstance =
			this.entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
	}

	@Override
	public void start() {
		this.tick = 0;

		if (!this.entityAttributeInstance.hasModifier(this.attributeId)) {
			this.entityAttributeInstance.addTemporaryModifier(this.attributeModifier);
		}

		this.entity.setStopAttacks(true);
		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHATTER_GROUND_BEGIN
		);
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

		this.entityAttributeInstance.removeModifier(this.attributeModifier);
		this.entity.setStopAttacks(false);
		this.entity.setShatterGroundTicks(
			this.entity.getShatterGroundCooldown() +
			this.entity.getRandom().nextInt(100)
		);

		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHATTER_GROUND_END
		);
	}

	@Override
	public boolean shouldContinue() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) <= 20 &&
			this.tick <= this.endTick;
	}

	@Override
	public void tick() {
		World world = this.entity.getEntityWorld();

		this.tick++;

		if (this.tick == this.endTick) {
			world.sendEntityStatus(
				this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHATTER_GROUND_PUSH
			);

			ExplosionBehavior behavior = new ExplosionIgnoreEntitiesBehavior(
				this.entity.getType(), ModEntities.SHATTERED_SENTINEL
			);

			world.createExplosion(
				this.entity,
				Explosion.createDamageSource(world, this.entity),
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
