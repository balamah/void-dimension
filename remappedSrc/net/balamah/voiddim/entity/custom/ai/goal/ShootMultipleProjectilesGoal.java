package net.balamah.voiddim.entity.custom.ai.goal;

import com.google.common.base.Function;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.MultipleProjectileShootUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class ShootMultipleProjectilesGoal
	<E extends CorruptedHostileEntity & MultipleProjectileShootUser, T extends ProjectileEntity>
	extends ShootProjectileGoal<E, T>
{
	public ShootMultipleProjectilesGoal(
		E entity,
		Function<ServerWorld, T> projectileFactory,
		SoundEvent shootPrepareSound, SoundEvent shootStartSound,
		int shootingPrepareCooldown, int shootingCooldown
	) {
		super(
			entity, projectileFactory, shootPrepareSound, shootStartSound,
			shootingPrepareCooldown, shootingCooldown
		);
	}

	@Override
	public boolean canStart() {
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
	protected void spawnProjectile(ServerWorld level, double dx, double dy, double dz) {
		Vec3d forward = new Vec3d(dx, dy, dz).normalize();
		Vec3d side = new Vec3d(-forward.z, 0, forward.x).normalize();

		double spread = 0.4D;

		for (int i = -1; i <= 1; i++) {
			T projectile = projectileFactory.apply(level);
			if (projectile == null) {
				continue;
			}

			projectile.setOwner(this.entity);
			projectile.setPosition(
				this.entity.getX(),
				this.entity.getEyeY(),
				this.entity.getZ()
			);

			Vec3d shootDirection = forward.add(side.multiply(i * spread)).normalize();

			projectile.setVelocity(
				shootDirection.x,
				shootDirection.y,
				shootDirection.z,
				0.7F,
				1.0F
			);

			level.spawnEntity(projectile);
		}
	}

	protected Vec3d getProjectileOffset(
		Direction entityDirection, Vec3d projectileDirection, int iteration
	) {
		double spreadAmount = 0.3 * iteration;
		Vec3d velocity;

		switch (entityDirection) {
			case NORTH, SOUTH:
				velocity = new Vec3d(
					projectileDirection.x,
					projectileDirection.y,
					projectileDirection.z * spreadAmount
				);
				break;

			case EAST, WEST:
				velocity = new Vec3d(
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
