package net.balamah.voiddim.entity.custom.ai.goal;

import com.google.common.base.Function;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.MultipleProjectileShootUser;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Direction;

public class ShootMultipleProjectilesGoal
	<E extends CorruptedHostileEntity & MultipleProjectileShootUser, T extends Projectile>
	extends ShootProjectileGoal<E, T>
{
	public ShootMultipleProjectilesGoal(
		E entity,
		Function<ServerLevel, T> projectileFactory,
		SoundEvent shootPrepareSound, SoundEvent shootStartSound,
		int shootingPrepareCooldown, int shootingCooldown
	) {
		super(
			entity, projectileFactory, shootPrepareSound, shootStartSound,
			shootingPrepareCooldown, shootingCooldown
		);
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		return target != null && !this.entity.areAttacksStopped() &&
			this.entity.isSecondPhase() &&
			this.entity.getShootMultipleProjectilesTicks() == 0;
	}

	@Override
	public void start() {
		super.start();
		
		this.addSpeedModifier();
	}

	@Override
	public void stop() {
		super.stop();
		
		this.removeSpeedModifier();
		this.entity.setShootMultipleProjectilesTicks(
			this.entity.getShootMultipleProjectilesCooldown()
		);
	}

	@Override
	protected void spawnProjectile(ServerLevel level, double dx, double dy, double dz) {
		Vec3 forward = new Vec3(dx, dy, dz).normalize();
		Vec3 side = new Vec3(-forward.z, 0, forward.x).normalize();

		double spread = 0.4D;

		for (int i = -1; i <= 1; i++) {
			T projectile = projectileFactory.apply(level);
			if (projectile == null) {
				continue;
			}

			projectile.setOwner(this.entity);
			projectile.setPos(
				this.entity.getX(),
				this.entity.getEyeY(),
				this.entity.getZ()
			);

			Vec3 shootDirection = forward.add(side.scale(i * spread)).normalize();

			projectile.shoot(
				shootDirection.x,
				shootDirection.y,
				shootDirection.z,
				0.7F,
				1.0F
			);

			level.addFreshEntity(projectile);
		}
	}

	protected Vec3 getProjectileOffset(
		Direction entityDirection, Vec3 projectileDirection, int iteration
	) {
		double spreadAmount = 0.3 * iteration;
		Vec3 velocity;

		switch (entityDirection) {
			case NORTH, SOUTH:
				velocity = new Vec3(
					projectileDirection.x,
					projectileDirection.y,
					projectileDirection.z * spreadAmount
				);
				break;

			case EAST, WEST:
				velocity = new Vec3(
					projectileDirection.x * spreadAmount,
					projectileDirection.y,
					projectileDirection.z
				);
				break;
		
			default:
				velocity = projectileDirection;
				break;
		}

		return velocity;
	}
}
