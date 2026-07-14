package net.balamah.voiddim.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.TeleportUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

public class VoidHarbingerEntity extends BossEntity implements TeleportUser {
	protected final int teleportCooldown = 100;
	protected int teleportTicks;

	public AnimationState shootAnimationState = new AnimationState();
	public AnimationState summonAnimationState = new AnimationState();
	public AnimationState summonEndAnimationState = new AnimationState();

	public VoidHarbingerEntity(
		EntityType<? extends Monster> entityType, Level world
	) {
		super(entityType, world);

		this.xpReward = 450;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.2F)
			.add(Attributes.ATTACK_DAMAGE, 13.0F)
			.add(Attributes.STEP_HEIGHT, 3.0)
			.add(Attributes.MAX_HEALTH, 320);
	}

	public double getChargeY() {
		return this.getY() + this.getBbHeight() / 2.0F + 0.3F;
	}

	public boolean teleportRandomly() {
		int teleportationRadius = 13;

		if (this.teleportTicks > 0) {
			return false;
		}

		if (!this.level().isClientSide() && this.isAlive()) {
			double newY = this.getY() + (this.random.nextInt(teleportationRadius));

			double x = this.getX() + teleportationRadius;
			double y = Math.max(this.getY(), newY);
			double z = this.getZ() + teleportationRadius;

			return this.teleportTo(x, y, z, true);
		}

		return false;
	}

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
		if (this.teleportTicks > 0) {
			return false;
		}

		BlockPos.MutableBlockPos mutable = McCodeHelper.getMutableCoordinate(
			this.level(), x, y, z, ignoreLimitPredicate
		);

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
	public void tick() {
		super.tick();

		if (this.teleportTicks > 0) {
			this.teleportTicks--;
		}

		if (!this.level().isClientSide() &&
			this.teleportTicks <= 0 &&
			McCodeHelper.shouldTeleportTo(this, this.getTarget())
		) {
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

	@Override
	protected void initBasicGoals() {
		this.goalSelector.addGoal(
			7, new StayOnHomeBlockGoal(this, 1.0, 50, false, Blocks.BEDROCK, 50)
		);

		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, AgeableMob.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(
			1, new HurtByTargetGoal(this, BossEntity.class).setAlertOthers(Entity.class)
		);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		Goal shootingGoal = new ShootProjectileGoal<VoidHarbingerEntity, VoidSphereEntity>(
			this, world -> new VoidSphereEntity(ModEntities.VOID_SPHERE, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50, 60
		);

		// Make teleportation more often on the second phase
		this.goalSelector.addGoal(0, new TeleportTowardsPlayerGoal<VoidHarbingerEntity>(this));
		this.goalSelector.addGoal(1, shootingGoal);
		this.goalSelector.addGoal(
			2,
			new SummonEntitiesGoal<VoidHarbingerEntity, CorruptedStalkerEntity>(
				this, CorruptedStalkerEntity.class, ModEntities.CORRUPTED_STALKER, 10
			)
		);
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.SUMMON_ENTITIES_START:
				this.summonAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.SUMMON_ENTITIES_FINISH:
				this.summonAnimationState.stop();
				this.summonEndAnimationState.start(this.tickCount);
				break;
			default: super.handleEntityEvent(status);
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
