package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.DodgeAttackUser;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class DodgeAttackGoal<T extends CorruptedHostileEntity & DodgeAttackUser>
	extends SlowMovementGoal<T>
{
	protected final int counterAttackDelay;
	protected final double dashSpeed;
	protected final float counterAttackBonusDamage;
	protected boolean dodgedAndHit;
	protected Vec3 targetPosition;

	protected final ResourceLocation attackAttributeId = ResourceLocation.withDefaultNamespace("attacking");
	protected final AttributeModifier attackAttributeModifier;

	public DodgeAttackGoal(
		T entity, int counterAttackDelay, double dashSpeed, float counterAttackBonusDamage
	) {
		super(entity);

		this.counterAttackDelay = counterAttackDelay;
		this.dashSpeed = dashSpeed;
		this.counterAttackBonusDamage = counterAttackBonusDamage;

		this.attackAttributeModifier = this.getAttributeModifier(
			this.attackAttributeId,
			this.counterAttackBonusDamage,
			AttributeModifier.Operation.ADD_VALUE
		);
	}

	@Override
	public boolean canUse() {
		return this.entity.getTarget() != null && this.entity.getDodgeAttackTicks() == 0 &&
			this.entity.attackCount >= 4;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.dodgedAndHit && this.entity.getTarget() != null;
	}

	@Override
	public void start() {
		super.start();

		Vec3 targetVelocity = this.entity.getTarget().getDeltaMovement();
		Vec3 newVelocity = targetVelocity.add(0, 0, 1);
		if (!this.isDodgingPositionSafe(newVelocity.x, newVelocity.y, newVelocity.z)) {
			newVelocity = targetVelocity.add(1, 0, 0);
		} else {
			newVelocity = targetVelocity.add(-1, 0, -1);
		}

		BlockPos newBlockPos = new BlockPos(
			(int) newVelocity.x, (int) newVelocity.y, (int) newVelocity.z
		);

		int newVelocityElevations = 0;
		while (!this.world.getBlockState(newBlockPos).isAir() && newVelocityElevations < 3) {
			newVelocity.add(0, 1, 0);

			newVelocityElevations++;
		}

		this.entity.setDeltaMovement(newVelocity);
		this.addSpeedModifier();
	}

	@Override
	public void stop() {
		super.stop();

		this.dodgedAndHit = false;
		this.entity.attackCount = 0;
		this.removeSpeedModifier();
		this.removeModifier(this.entityAttributeInstance, this.attackAttributeModifier);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == this.counterAttackDelay - 5) {
			this.prepareDashAttack();
		}

		if (this.tick == this.counterAttackDelay) {
			this.performDashAttack();
		}
	}

	/*
	 * Check if it is safe by lowering mutable down.
	 * If future position is a deep hole, or has lava or a cobweb,
	   then the position is not safe
	 */
	@SuppressWarnings("deprecation")
	protected boolean isDodgingPositionSafe(double x, double y, double z) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(x, y, z);

		if (!this.isDodgingToWall(x, y, z)) {
			return false;
		}

		while (mutable.getY() > this.world.getMinBuildHeight() &&
				!this.world.getBlockState(mutable).blocksMotion())
		{
			double heightDifference = this.entity.getY() - mutable.getY();
			Block block = this.world.getBlockState(mutable).getBlock();

			if (heightDifference < 30) {
				mutable.move(Direction.DOWN);
			} else if (heightDifference >= 30 || McCodeHelper.dangerousBlocks.contains(block)) {
				return false;
			}
		}

		return true;
	}

	protected boolean isDodgingToWall(double x, double y, double z) {
		BlockPos upperBlockPosition = new BlockPos((int) x, (int) y + 1, (int) z);

		if (this.world.getBlockState(upperBlockPosition).isAir()) {
			return false;
		}

		return true;
	}

	protected void prepareDashAttack() {
		this.entity.makeSound(ModSounds.COUNTER_ATTACK);
		this.addModifier(
			this.entityAttributeInstance, this.attackAttributeId, this.attackAttributeModifier
		);

		this.targetPosition = this.entity.getTarget().position();
	}

	protected void performDashAttack() {
		Vec3 dashDirection = this.targetPosition.subtract(this.entity.position()).normalize();

		this.entity.setDeltaMovement(dashDirection.scale(this.dashSpeed));

		this.dodgedAndHit = true;
	}
}
