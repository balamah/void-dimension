package net.balamah.voiddim.entity.custom;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.balamah.voiddim.interfaces.MultipleProjectileShootUser;
import net.balamah.voiddim.interfaces.StrongAttackUser;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.DarkGraspUser;
import net.balamah.voiddim.interfaces.TeleportUser;
import net.balamah.voiddim.sound.ModSounds;

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

	public CorruptedWarriorEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.xpReward = 500;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0.27F)
			.add(Attributes.MAX_HEALTH, 365)
			.add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.ATTACK_DAMAGE, 10.5F)
			.add(Attributes.STEP_HEIGHT, 1.0);
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
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.stopAnimations(this.normalAttackAnimations);
				this.playRandomAnimation(this.normalAttackAnimations);
				break;
			case ModEntityStatuses.SPECIAL_ATTACK:
				this.specialAttackAnimationState.start(this.tickCount);
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
				this.strongAttackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.STRONGEST_ATTACK:
				this.strongestAttackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.PROJECTILE_INVOKE:
				this.summonProjectileAnimationState.start(this.tickCount);
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean result = super.doHurtTarget(world, target);
		
		if (result) {
			world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

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

		Vec3 vec3d = new Vec3(
			this.getX() - entity.getX(), 
			this.getY(0.5) - entity.getEyeY(), 
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

		BlockPos.MutableBlockPos mutable = this.getMutableCoordinate(x, y, z, ignoreLimitPredicate);
		if (mutable == null) {
			return false;
		}

		BlockState blockState = this.level().getBlockState(mutable);
		boolean isSolidBlock = blockState.blocksMotion();
		if (!isSolidBlock) {
			return false;
		}

		Vec3 vec3d = new Vec3(this.getX(), this.getY(), this.getZ());
			boolean didTeleport = this.randomTeleport(x, y, z, true);
			if (didTeleport) {
				this.teleportTicks = this.teleportCooldown;
				this.level().gameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Context.of(this));
				this.playSound(ModSounds.VOID_HARBINGER_TELEPORT, 1.0F, 1.0F);
			}

		return didTeleport;
	}


	@Override
	protected void registerGoals() {
		super.registerGoals();

		Goal summonEntitiesGoal =
			new SummonEntitiesGoal<CorruptedWarriorEntity, VoidBoundServantEntity>(
				this, VoidBoundServantEntity.class, ModEntities.VOID_BOUND_SERVANT, 10
			);

		Goal shootingGoal =
			new ShootMultipleProjectilesGoal<CorruptedWarriorEntity, ConsumedSoulEntity>(
				this, world -> new ConsumedSoulEntity(ModEntities.CONSUMED_SOUL, world),
				ModSounds.MAGIC_PREPARE, ModSounds.CORRUPTED_WARRIOR_LONG_EFFORT,
				3, 15
			);

		this.goalSelector.addGoal(5, summonEntitiesGoal);
		this.goalSelector.addGoal(
			2, new StrongAttackGoal<>(this, 5, 3, ModSounds.CORRUPTED_WARRIOR_LONG_EFFORT)
		);

		this.goalSelector.addGoal(
			4, new DarkGraspInvokeGoal<>(this, 5, 2, 8, ModSounds.CORRUPTED_WARRIOR_EFFORT_1)
		);

		this.goalSelector.addGoal(1, new TeleportTowardsPlayerGoal<>(this));
		this.goalSelector.addGoal(2, shootingGoal);
	}

	@Override
	protected void customServerAiStep(ServerLevel world) {
		super.customServerAiStep(world);

		if (this.getTarget() == null || this.attackInterval == 0) {
			world.broadcastEntityEvent(this, ModEntityStatuses.STOP_ATTACK);
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
		return ModSounds.ARMOR_HIT;
	}

	protected double getRandomCoordinate(double baseCoordinate, double vector, double diameter) {
		return baseCoordinate + (this.random.nextDouble() - 0.5) * 2 - vector * diameter;
	}

	@SuppressWarnings("deprecation")
	protected @Nullable BlockPos.MutableBlockPos getMutableCoordinate(
		double x, double y, double z, boolean ignoreLimitPredicate
	) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(x, y, z);

		while (mutable.getY() > this.level().getMinY() &&
			   !this.level().getBlockState(mutable).blocksMotion()
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
