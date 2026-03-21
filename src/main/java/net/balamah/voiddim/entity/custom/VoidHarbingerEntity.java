package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.AnimationState;
import net.minecraft.world.event.GameEvent;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

// TODO: Nerf VoidHarbingerEntity teleportation
public class VoidHarbingerEntity extends BossEntity {
	protected final int teleportCooldown = 140;
	protected int teleportTicks;

	public AnimationState shootAnimationState = new AnimationState();
	public AnimationState summonAnimationState = new AnimationState();
	public AnimationState summonEndAnimationState = new AnimationState();

	public VoidHarbingerEntity(
		EntityType<? extends HostileEntity> entityType, World world
	) {
		super(entityType, world);

		this.experiencePoints = 450;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 32)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.2F)
			.add(EntityAttributes.ATTACK_DAMAGE, 13.0F)
			.add(EntityAttributes.STEP_HEIGHT, 3.0)
			.add(EntityAttributes.MAX_HEALTH, 320);
	}

	public double getChargeY() {
		return this.getY() + this.getHeight() / 2.0F + 0.3F;
	}

	public boolean teleportRandomly() {
		int teleportationRadius = 13;

		if (!this.getEntityWorld().isClient() && this.isAlive()) {
			double newY = this.getY() + (this.random.nextInt(teleportationRadius));

			double x = this.getX() + teleportationRadius;
			double y = Math.max(this.getY(), newY);
			double z = this.getZ() + teleportationRadius;

			return this.teleportTo(x, y, z, true);
		}

		return false;
	}

	public boolean teleportTo(Entity entity) {
		Vec3d vec3d = new Vec3d(
			this.getX() - entity.getX(), 
			this.getBodyY(0.5) - entity.getEyeY(), 
			this.getZ() - entity.getZ()
		);

		vec3d = vec3d.normalize();

		double teleportDiameter = 7.0;

		int randomYoffset = (this.random.nextInt(16) - 8);

		double x, y, z;
		for (int i = 0; i < 10; i++) {
			double randomY = this.getY() + randomYoffset - vec3d.y * teleportDiameter;

			x = this.getRandomCoordinate(this.getX(), vec3d.x, teleportDiameter);
			y = Math.max(entity.getY(), randomY);
			z = this.getRandomCoordinate(this.getZ(), vec3d.z, teleportDiameter);

			if (McCodeHelper.isTeleportationSafe(entity, entity.getY(), x, y, z)) {
				return this.teleportTo(x, y, z, false);
			}
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean teleportTo(double x, double y, double z, boolean ignoreLimitPredicate) {
		BlockPos.Mutable mutable = this.getMutableCoordinate(x, y, z, ignoreLimitPredicate);
		if (mutable == null) {
			return false;
		}

		BlockState blockState = this.getEntityWorld().getBlockState(mutable);
		boolean isSolidBlock = blockState.blocksMovement();
		if (!isSolidBlock) {
			return false;
		}

		Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
		boolean didTeleport = this.teleport(x, y, z, true);
		if (didTeleport) {
			this.getEntityWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
			this.playSound(ModSounds.VOID_HARBINGER_TELEPORT, 1.0F, 1.0F);
		}

		return didTeleport;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.teleportTicks > 0) {
			this.teleportTicks--;
		}

		if (McCodeHelper.shouldTeleportTo(this, this.getTarget())) {
			this.teleportTo(this.getTarget());
		}
	}

	public int getTeleportTicks() {
		return teleportTicks;
	}

	public void setTeleportTicks(int teleportTicks) {
		this.teleportTicks = teleportTicks;
	}

	public int getTeleportCooldown() {
		return teleportCooldown;
	}

	protected double getRandomCoordinate(double baseCoordinate, double vector, double diameter) {
		return baseCoordinate + (this.random.nextDouble() - 0.5) * 2 - vector * diameter;
	}

	@SuppressWarnings("deprecation")
	protected @Nullable BlockPos.Mutable getMutableCoordinate(
		double x, double y, double z, boolean ignoreLimitPredicate
	) {
		BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

		while (mutable.getY() > this.getEntityWorld().getBottomY() &&
			   !this.getEntityWorld().getBlockState(mutable).blocksMovement()
		) {
			double heightDifference = this.getY() - mutable.getY();

			if (heightDifference < 30 || ignoreLimitPredicate) {
				mutable.move(Direction.DOWN);
			} else {
				return null;
			}
		}

		return mutable;
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		Goal shootingGoal = new ShootProjectileGoal<VoidHarbingerEntity, VoidSphereEntity>(
			this, world -> new VoidSphereEntity(ModEntities.VOID_SPHERE, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50, 60
		);

		// Make teleportation more often on the second phase
		this.goalSelector.add(0, new VoidHarbingerTeleportTowardsPlayerGoal(this));
		this.goalSelector.add(1, shootingGoal);
		this.goalSelector.add(
			2,
			new SummonEntitiesGoal<VoidHarbingerEntity, CorruptedStalkerEntity>(
				this, CorruptedStalkerEntity.class, ModEntities.CORRUPTED_STALKER
			)
		);
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.SUMMON_ENTITIES_START:
				this.summonAnimationState.start(this.age);
				break;
			case ModEntityStatuses.SUMMON_ENTITIES_FINISH:
				this.summonAnimationState.stop();
				this.summonEndAnimationState.start(this.age);
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.VOID_HARBINGER_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.VOID_HARBINGER_DEATH;
	}
}
