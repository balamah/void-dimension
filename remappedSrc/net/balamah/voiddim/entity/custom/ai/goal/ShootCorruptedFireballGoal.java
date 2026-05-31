package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.EnumSet;
import net.balamah.voiddim.entity.custom.SmallCorruptedFireballEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldEvents;

public class ShootCorruptedFireballGoal extends Goal {
	private final BlazeEntity blaze;
	private int fireballsFired;
	private int fireballCooldown;
	private int targetNotVisibleTicks;

	public ShootCorruptedFireballGoal(BlazeEntity blaze) {
		this.blaze = blaze;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
	}

	@Override
	public boolean canStart() {
		LivingEntity livingEntity = this.blaze.getTarget();
		return livingEntity != null && livingEntity.isAlive() && this.blaze.canTarget(livingEntity);
	}

	@Override
	public void start() {
		this.fireballsFired = 0;
	}

	@Override
	public void stop() {
		this.targetNotVisibleTicks = 0;
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		this.fireballCooldown--;
		LivingEntity livingEntity = this.blaze.getTarget();
		if (livingEntity != null) {
			boolean bl = this.blaze.getVisibilityCache().canSee(livingEntity);
			if (bl) {
				this.targetNotVisibleTicks = 0;
			} else {
				this.targetNotVisibleTicks++;
			}

			double d = this.blaze.squaredDistanceTo(livingEntity);
			if (d < 4.0) {
				if (!bl) {
					return;
				}

				if (this.fireballCooldown <= 0) {
					this.fireballCooldown = 20;
					this.blaze.tryAttack(getServerLevel(this.blaze), livingEntity);
				}

				this.blaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
			} else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
				double e = livingEntity.getX() - this.blaze.getX();
				double f = livingEntity.getBodyY(0.5) - this.blaze.getBodyY(0.5);
				double g = livingEntity.getZ() - this.blaze.getZ();
				if (this.fireballCooldown <= 0) {
					this.fireballsFired++;
					if (this.fireballsFired == 1) {
						this.fireballCooldown = 60;
					} else if (this.fireballsFired <= 4) {
						this.fireballCooldown = 6;
					} else {
						this.fireballCooldown = 100;
						this.fireballsFired = 0;
					}

					if (this.fireballsFired > 1) {
						double h = Math.sqrt(Math.sqrt(d)) * 0.5;
						if (!this.blaze.isSilent()) {
							this.blaze.getWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, this.blaze.getBlockPos(), 0);
						}

						for (int i = 0; i < 3; i++) {
							Vec3d vec3d = new Vec3d(this.blaze.getRandom().nextTriangular(e, 2.297 * h), f, this.blaze.getRandom().nextTriangular(g, 2.297 * h));
							SmallCorruptedFireballEntity smallFireballEntity = new SmallCorruptedFireballEntity(this.blaze.getWorld(), this.blaze, vec3d.normalize());
							smallFireballEntity.setPos(smallFireballEntity.getX(), this.blaze.getBodyY(0.5) + 0.5, smallFireballEntity.getZ());
							this.blaze.getWorld().spawnEntity(smallFireballEntity);
						}
					}
				}

				this.blaze.getLookControl().lookAt(livingEntity, 10.0F, 10.0F);
			} else if (this.targetNotVisibleTicks < 5) {
				this.blaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
			}

			super.tick();
		}
	}

	private double getFollowRange() {
		return this.blaze.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
	}
}
