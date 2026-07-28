package net.balamah.voiddim.entity.custom.ai.goal;

import com.google.common.base.Function;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class ShootProjectileGoal<E extends CorruptedHostileEntity, T extends Projectile>
	extends SlowMovementGoal<E>
{
	protected final Function<ServerLevel, T> projectileFactory;
	protected final SoundEvent shootPrepareSound;
	protected final SoundEvent shootStartSound;
	protected final int shootingPrepareCooldown;
	protected final int shootingCooldown;

	protected boolean didShoot;

	public ShootProjectileGoal(
		E entity,
		Function<ServerLevel, T> projectileFactory,
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
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		return target != null && !this.entity.areAttacksStopped() &&
			target.distanceTo(this.entity) < 14;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();
		
		LivingEntity target = this.entity.getTarget();

		if (this.tick == this.shootingPrepareCooldown) {
			this.entity.makeSound(this.shootPrepareSound);
		}

		if (this.tick == this.shootingCooldown) {
			this.entity.makeSound(this.shootStartSound);
			this.shootProjectile(this.world, target);

			this.tick = 0;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return !this.didShoot && this.entity.getTarget() != null;
	}

	@Override
	public void start() {
		super.start();

		this.sendEntityStatus(ModEntityStatuses.PROJECTILE_INVOKE);
	}

	@Override
	public void stop() {
		this.didShoot = false;

		this.sendEntityStatus(ModEntityStatuses.STOP_SPECIAL_ATTACK);
	}

	protected void spawnProjectile(ServerLevel serverWorld, double dx, double dy, double dz) {
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

	protected void shootProjectile(Level world, LivingEntity target) {
		if (this.world instanceof ServerLevel serverWorld && target != null) {
			double heightScale = target.isPassenger() ? 0.8 : 0.3;

			double dx = target.getX() - this.entity.getX();
			double dy = target.getY(heightScale) - this.entity.getChargeY();
			double dz = target.getZ() - this.entity.getZ();

			this.sendEntityStatus(ModEntityStatuses.SHOOT);

			this.spawnProjectile(serverWorld, dx, dy, dz);
			this.entity.attackCount++;
			this.didShoot = true;
		}
	}
}
