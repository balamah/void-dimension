package net.balamah.voiddim.entity.custom.ai.goal;

import com.google.common.base.Function;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.MultipleProjectileShootUser;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;

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
	protected void spawnProjectile(ServerLevel serverWorld, double dx, double dy, double dz) {
		for (int i = 0; i < 3; i++) {
			T projectile = projectileFactory.apply(serverWorld);
			if (projectile == null) {
				return;
			}

			projectile.setOwner(this.entity);
			projectile.setPos(this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());

			double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
			if (len > 0) {
				dx /= len;
				dy /= len;
				dz /= len;
			}

			projectile.shoot(dx, dy, dz, 0.7F, 1.0F);
			serverWorld.addFreshEntity(projectile);
		}
	}
}
