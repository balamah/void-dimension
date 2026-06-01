package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.interfaces.MultipleProjectileShootUser;
import net.balamah.voiddim.interfaces.StrongAttackUser;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.DarkGraspUser;
import net.balamah.voiddim.interfaces.TeleportUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;

import org.jetbrains.annotations.Nullable;

public class CorruptedWarriorEntity extends BossEntity
	implements DarkGraspUser, MultipleProjectileShootUser, TeleportUser, StrongAttackUser
{
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState strongAttackAnimationState = new AnimationState();
	public final AnimationState strongestAttackAnimationState = new AnimationState();
	public final AnimationState summonProjectileAnimationState = new AnimationState();
	public final AnimationState specialAttackAnimationState = new AnimationState();
	public final AnimationState normalAttack1AnimationState = new AnimationState();
	public final AnimationState normalAttack2AnimationState = new AnimationState();
	public final AnimationState normalAttack3AnimationState = new AnimationState();

	protected final int multipleProjectilesShootCooldown = 175;
	protected final int darkGraspCooldown = 65;
	protected final int teleportCooldown = 150;
	protected final int strongAttackCooldown = 90;

	protected int attackInterval;
	protected int darkGraspTicks;
	protected int multipleProjectilesShootTicks = 25;
	protected int teleportTicks;
	protected int strongAttackTicks = 50;

	protected AnimationState[] normalAttackAnimations = {
		this.normalAttack1AnimationState,
		this.normalAttack2AnimationState,
		this.normalAttack3AnimationState
	};

	public CorruptedWarriorEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);

		this.experiencePoints = 500;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.27F)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 365)
			.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.5F)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0);
	}

	@Override
	public int getDarkGraspInvokeCooldown() {
		return this.darkGraspCooldown;
	}

	@Override
	public int getDarkGraspInvokeTicks() {
		return this.darkGraspTicks;
	}

	@Override
	public void setDarkGraspInvokeTicks(int ticks) {
		this.darkGraspTicks = ticks;
	}

	@Override
	public int getShootMultipleProjectilesCooldown() {
		return this.multipleProjectilesShootCooldown;
	}

	@Override
	public int getShootMultipleProjectilesTicks() {
		return this.multipleProjectilesShootTicks;
	}

	@Override
	public void setShootMultipleProjectilesTicks(int ticks) {
		this.multipleProjectilesShootTicks = ticks;
	}

	@Override
	public int getStrongAttackCooldown() {
		return this.strongAttackCooldown;
	}

	@Override
	public int getStrongAttackTicks() {
		return this.strongAttackTicks;
	}

	@Override
	public void setStrongAttackTicks(int ticks) {
		this.strongAttackTicks = ticks;
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.SPECIAL_ATTACK:
				this.specialAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.STOP_ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.STOP_SPECIAL_ATTACK:
				this.specialAttackAnimationState.stop();
				this.strongAttackAnimationState.stop();
				this.strongestAttackAnimationState.stop();
				this.summonProjectileAnimationState.stop();
				break;
			case ModEntityStatuses.STRONG_ATTACK:
				this.strongAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.STRONGEST_ATTACK:
				this.strongestAttackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.PROJECTILE_INVOKE:
				this.summonProjectileAnimationState.start(this.age);
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	public boolean doHurtTarget(ServerWorld world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.sendEntityStatus(this, ModEntityStatuses.ATTACK);

			// A magic number, don't touch
			this.attackInterval = 8;
		}

		return result;
	}

	@Override
	public boolean teleportTo(Entity entity) {
		if (this.teleportTicks > 0) {
			return false;
		}

		Vec3d vec3d = new Vec3d(
			this.getX() - entity.getX(), 
			this.getBodyY(0.5) - entity.getEyeY(), 
			this.getZ() - entity.getZ()
		);

		vec3d = vec3d.normalize();

		double teleportDiameter = 10.0;

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
		if (this.teleportTicks > 0) {
			return false;
		}

		BlockPos.Mutable mutable = this.getMutableCoordinate(x, y, z, ignoreLimitPredicate);
		if (mutable == null) {
			return false;
		}

		BlockState blockState = this.getWorld().getBlockState(mutable);
		boolean isSolidBlock = blockState.blocksMovement();
		if (!isSolidBlock) {
			return false;
		}

		Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
			boolean didTeleport = this.teleport(x, y, z, true);
			if (didTeleport) {
				this.teleportTicks = this.teleportCooldown;
				this.getWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
				this.playSound(ModSounds.VOID_HARBINGER_TELEPORT, 1.0F, 1.0F);
			}

		return didTeleport;
	}


	@Override
	protected void initGoals() {
		super.initGoals();

		Goal summonEntitiesGoal =
			new SummonEntitiesGoal<CorruptedWarriorEntity, VoidBoundServantEntity>(
				this, VoidBoundServantEntity.class, ModEntities.VOID_BOUND_SERVANT, 4
			);

		Goal shootingGoal =
			new ShootMultipleProjectilesGoal<CorruptedWarriorEntity, ConsumedSoulEntity>(
				this, world -> new ConsumedSoulEntity(ModEntities.CONSUMED_SOUL, world),
				ModSounds.MAGIC_PREPARE, ModSounds.CORRUPTED_WARRIOR_LONG_EFFORT,
				3, 15
			);

		this.goalSelector.add(3, summonEntitiesGoal);
		this.goalSelector.add(
			2, new StrongAttackGoal<>(this, 5, 3, ModSounds.CORRUPTED_WARRIOR_LONG_EFFORT)
		);

		this.goalSelector.add(
			3, new DarkGraspInvokeGoal<>(this, 5, 2, 8, ModSounds.CORRUPTED_WARRIOR_EFFORT_1)
		);

		this.goalSelector.add(1, new TeleportTowardsPlayerGoal<>(this));
		this.goalSelector.add(2, shootingGoal);
	}

	@Override
	protected void customServerAiStep(ServerWorld world) {
		super.customServerAiStep(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.sendEntityStatus(this, ModEntityStatuses.STOP_ATTACK);
		}

		if (this.attackInterval > 0) {
			this.attackInterval--;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.darkGraspTicks > 0) {
			this.darkGraspTicks--;
		}

		if (this.strongAttackTicks > 0) {
			this.strongAttackTicks--;
		}

		if (this.multipleProjectilesShootTicks > 0 && this.isSecondPhase()) {
			this.multipleProjectilesShootTicks--;
		}

		if (this.teleportTicks > 0) {
			this.teleportTicks--;
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.CORRUPTED_WARRIOR_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.GREAT_ARMOR_HIT;
	}

	protected double getRandomCoordinate(double baseCoordinate, double vector, double diameter) {
		return baseCoordinate + (this.random.nextDouble() - 0.5) * 2 - vector * diameter;
	}

	@SuppressWarnings("deprecation")
	protected @Nullable BlockPos.Mutable getMutableCoordinate(
		double x, double y, double z, boolean ignoreLimitPredicate
	) {
		BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

		while (mutable.getY() > this.getWorld().getMinY() &&
			   !this.getWorld().getBlockState(mutable).blocksMovement()
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
}
