package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.VoidLightningEntity;
import net.balamah.voiddim.interfaces.ShootLightningUser;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

public class ShootLightningGoal<T extends CorruptedHostileEntity & ShootLightningUser>
	extends TickingGoal<T>
{
	protected Random random = new Random();
	protected Vec3d targetPosition;
	protected boolean shotPredicate;

	public ShootLightningGoal(T entity) {
		super(entity);
	}

	@Override
	public boolean canStart() {
		return this.entity.getTarget() != null && this.entity.getLightningCooldown() == 0;
	}

	@Override
	public boolean shouldContinue() {
		return !this.shotPredicate;
	}

	@Override
	public void start() {
		super.start();

		this.targetPosition = this.getTargetPosition(this.entity.getTarget());
		this.sendEntityStatus(ModEntityStatuses.LIGHTNING_INVOKE);

		this.world.playSound(
			this.entity, targetPosition.x, targetPosition.y, targetPosition.z,
			ModSounds.LIGHTNING, SoundCategory.HOSTILE
		);
	}

	@Override
	public void stop() {
		super.stop();

		this.shotPredicate = false;

		this.entity.setLightningCooldown(600);
		this.sendEntityStatus(ModEntityStatuses.LIGHTNING_STOP);
	}

	@Override
	public void tick() {
		super.tick();

		this.walkAway();
		this.displayParticles(this.targetPosition);

		if (this.tick == 20) {
			this.summonLightningBolt(this.targetPosition);
		}
	}

	protected Vec3d getTargetPosition(LivingEntity target) {
		return target.getEntityPos();
	}

	protected void displayParticles(Vec3d position) {
		if (!(this.world instanceof ServerWorld serverWorld)) {
			return;
		}

		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble(5.5) * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		serverWorld.spawnParticles(
			ModParticleTypes.LIGHTNING, position.x, position.y, position.z, 10, vz, vx, vy, 0.1
		);
	}

	protected void summonLightningBolt(Vec3d position) {
		if (!(this.world instanceof ServerWorld)) {
			return;
		}

		VoidLightningEntity lightningBolt = new VoidLightningEntity(
			ModEntities.VOID_LIGHTNING_BOLT, this.world
		);

		lightningBolt.setPosition(position);
		this.world.spawnEntity(lightningBolt);
		this.shotPredicate = true;
	}

	protected void walkAway() {
		double x = this.entity.getX();
		double y = this.entity.getY();
		double z = this.entity.getZ();

		double xt = this.targetPosition.x;
		double zt = this.targetPosition.z;

		double xEntityTargetDistance = Math.abs(xt - x);
		double zEntityTargetDistance = Math.abs(zt - z);

		double xTarget = x;
		double zTarget = z;

		if (xEntityTargetDistance < 4) {
			xTarget = xt - 5;
		} else if (zEntityTargetDistance < 4) {
			zTarget = zt - 5;
		}

		this.entity.getNavigation().startMovingTo(xTarget, y, zTarget, 1.0);
	}
}
