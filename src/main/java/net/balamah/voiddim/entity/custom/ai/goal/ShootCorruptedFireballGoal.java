package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.EnumSet;
import net.balamah.voiddim.entity.custom.SmallCorruptedFireballEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.phys.Vec3;

public class ShootCorruptedFireballGoal extends Goal {
	private final Blaze blaze;
	private int fireballsFired;
	private int fireballCooldown;
	private int targetNotVisibleTicks;

	public ShootCorruptedFireballGoal(Blaze blaze) {
		this.blaze = blaze;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		LivingEntity livingEntity = this.blaze.getTarget();
		return livingEntity != null && livingEntity.isAlive() && this.blaze.canAttack(livingEntity);
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
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		this.fireballCooldown--;
		LivingEntity livingEntity = this.blaze.getTarget();
		if (livingEntity != null) {
			boolean bl = this.blaze.getSensing().hasLineOfSight(livingEntity);
			if (bl) {
				this.targetNotVisibleTicks = 0;
			} else {
				this.targetNotVisibleTicks++;
			}

			double d = this.blaze.distanceToSqr(livingEntity);
			if (d < 4.0) {
				if (!bl) {
					return;
				}

				if (this.fireballCooldown <= 0) {
					this.fireballCooldown = 20;
					this.blaze.doHurtTarget(getServerLevel(this.blaze), livingEntity);
				}

				this.blaze.getMoveControl().setWantedPosition(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
			} else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
				double e = livingEntity.getX() - this.blaze.getX();
				double f = livingEntity.getY(0.5) - this.blaze.getY(0.5);
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
							this.blaze.level().levelEvent(null, LevelEvent.SOUND_BLAZE_FIREBALL, this.blaze.blockPosition(), 0);
						}

						for (int i = 0; i < 3; i++) {
							Vec3 vec3d = new Vec3(this.blaze.getRandom().triangle(e, 2.297 * h), f, this.blaze.getRandom().triangle(g, 2.297 * h));
							SmallCorruptedFireballEntity smallFireballEntity = new SmallCorruptedFireballEntity(this.blaze.level(), this.blaze, vec3d.normalize());
							smallFireballEntity.setPos(smallFireballEntity.getX(), this.blaze.getY(0.5) + 0.5, smallFireballEntity.getZ());
							this.blaze.level().addFreshEntity(smallFireballEntity);
						}
					}
				}

				this.blaze.getLookControl().setLookAt(livingEntity, 10.0F, 10.0F);
			} else if (this.targetNotVisibleTicks < 5) {
				this.blaze.getMoveControl().setWantedPosition(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
			}

			super.tick();
		}
	}

	private double getFollowRange() {
		return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
	}
}
