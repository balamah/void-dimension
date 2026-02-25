package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;

import com.google.common.base.Function;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class ShootProjectileGoal<E extends CorruptedHostileEntity, T extends ProjectileEntity>
	extends TickingGoal<E>
{
	protected final Function<ServerWorld, T> projectileFactory;
	protected final SoundEvent shootPrepareSound;
	protected final SoundEvent shootStartSound;
	protected final int shootingPrepareCooldown;
	protected final int shootingCooldown;

	public ShootProjectileGoal(
		E entity,
		Function<ServerWorld, T> projectileFactory,
		SoundEvent shootPrepareSound, SoundEvent shootStartSound,
		int shootingPrepareCooldown, int shootingCooldown
	) {
		super(entity);

		this.projectileFactory = projectileFactory;
		this.shootPrepareSound = shootPrepareSound;
		this.shootStartSound = shootStartSound;
		this.shootingPrepareCooldown = shootingPrepareCooldown;
		this.shootingCooldown = shootingCooldown;
	}

	@Override
	public boolean canStart() {
		return this.entity.getTarget() != null && !this.entity.areAttacksStopped();
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();
		
		LivingEntity target = this.entity.getTarget();

		if (target == null || target.distanceTo(this.entity) >= 14.0) return;

		if (this.tick == this.shootingPrepareCooldown) {
			this.entity.playSound(this.shootPrepareSound);
		}

		if (this.tick == this.shootingCooldown) {
			this.entity.playSound(this.shootStartSound);

			if (this.world instanceof ServerWorld serverWorld) {
				double dx = target.getX() - this.entity.getX();
				double dy = target.getBodyY(target.hasVehicle() ? 0.8 : 0.3) - this.entity.getChargeY();
				double dz = target.getZ() - this.entity.getZ();

				this.sendEntityStatus(ModEntityStatuses.VOID_SPHERE_SHOOT);

				this.spawnProjectile(serverWorld, dx, dy, dz);
				this.entity.attackCount++;
			}

			this.tick = 0;
		}
	}

	protected void spawnProjectile(
		ServerWorld serverWorld, double dx, double dy, double dz
	) {
        T projectile = projectileFactory.apply(serverWorld);
        if (projectile == null) return;

        projectile.setOwner(this.entity);
		projectile.setPosition(
			this.entity.getX(), this.entity.getEyeY(), this.entity.getZ()
		);

        double len = Math.sqrt(dx*dx + dy*dy + dz*dz);
        if (len > 0) { dx /= len; dy /= len; dz /= len; }

        projectile.setVelocity(dx, dy, dz, 0.7F, 1.0F);
        serverWorld.spawnEntity(projectile);
    }
}
