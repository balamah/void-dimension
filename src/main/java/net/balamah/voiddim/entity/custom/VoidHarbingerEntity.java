package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.registry.tag.FluidTags;
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
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

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
		if (!this.getEntityWorld().isClient() && this.isAlive()) {
			double d = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
			double e = this.getY() + (this.random.nextInt(64) - 32);
			double f = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;

			return this.teleportTo(d, e, f);
		} else {
			return false;
		}
	}

	public boolean teleportTo(Entity entity) {
		Vec3d vec3d = new Vec3d(
			this.getX() - entity.getX(), 
			this.getBodyY(0.5) - entity.getEyeY(), 
			this.getZ() - entity.getZ()
		);

		vec3d = vec3d.normalize();

		double teleportDiameter = 16.0;

		double x;
		double y;
		double z;

		for (int i = 0; i < 10; i++) {
			x = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vec3d.x * teleportDiameter;
			y = this.getY() + (this.random.nextInt(16) - 8) - vec3d.y * teleportDiameter;
			z = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vec3d.z * teleportDiameter;

			if (McCodeHelper.isTeleportationSafe(entity, entity.getY(), x, y, z)) {
				return this.teleportTo(x, y, z);
			}
		}

		return false;
	}

	public boolean teleportTo(double x, double y, double z) {
		BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

		while (mutable.getY() > this.getEntityWorld().getBottomY() &&
			   !this.getEntityWorld().getBlockState(mutable).blocksMovement()
		) {
			mutable.move(Direction.DOWN);
		}

		BlockState blockState = this.getEntityWorld().getBlockState(mutable);
		boolean bl = blockState.blocksMovement();
		boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
		if (!(bl && !bl2))
			return false;

		Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
		boolean bl3 = this.teleport(x, y, z, true);
		if (bl3) {
			this.getEntityWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
			if (!this.isSilent()) {
				this.playSound(ModSounds.VOID_HARBINGER_TELEPORT, 1.0F, 1.0F);
			}
		}

		return bl3;
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

	@Override
	protected void initGoals() {
		super.initGoals();

		Goal shootingGoal = new ShootProjectileGoal<VoidHarbingerEntity, VoidSphereEntity>(
			this, world -> new VoidSphereEntity(ModEntities.VOID_SPHERE, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50, 60
		);

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
