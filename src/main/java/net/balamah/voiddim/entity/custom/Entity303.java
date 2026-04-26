package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.DodgeAttackUser;
import net.balamah.voiddim.interfaces.ShockWaveUser;
import net.balamah.voiddim.interfaces.ShootLightningUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class Entity303 extends BossEntity
	implements ShockWaveUser, DodgeAttackUser, ShootLightningUser
{
	public AnimationState lightningInvokeAnimationState = new AnimationState();
	public AnimationState groundCorruptionAnimationState = new AnimationState();
	public AnimationState shockwaveInvokeAnimationState = new AnimationState();

	protected int lightningCooldown;
	protected int shockwaveCooldown = 200;
	protected int groundCorruptionCooldown;
	protected int dodgeAttackCooldown;
	protected String[] deathMessages = {
		"I am not gone, I am waiting",
		"Every end is temporary, even mine",
		"Do not be fooled, I am never truly gone"
	};

	public Entity303(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.STEP_HEIGHT, 2)
			.add(Attributes.ATTACK_DAMAGE, 7.0F)
			.add(Attributes.MOVEMENT_SPEED, 0.3f)
			.add(Attributes.MAX_HEALTH, 315);
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.LIGHTNING_INVOKE:
				this.lightningInvokeAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.SHOCK_WAVE_INVOKE:
				this.shockwaveInvokeAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_BEGIN:
				this.groundCorruptionAnimationState.start(this.tickCount);
				break;
			default:
				super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.lightningCooldown > 0) {
			this.lightningCooldown--;
		}

		if (this.shockwaveCooldown > 0) {
			this.shockwaveCooldown--;
		}

		if (this.groundCorruptionCooldown > 0) {
			this.groundCorruptionCooldown--;
		}

		if (this.dodgeAttackCooldown > 0) {
			this.dodgeAttackCooldown--;
		}
	}
	
	@Override
	public void aiStep() {
		super.aiStep();

		if (this.isSecondPhase()) {
			BlockPos blockToCorruptPos = this.blockPosition().offset(0, -1, 0);
			Level world = this.level();
			Block blockToCorrupt = McCodeHelper.getBlock(world, blockToCorruptPos);
			if (McCodeHelper.isBlockReplaceable(blockToCorrupt)) {
				this.corruptBlock(this.level(), blockToCorruptPos);
			}
		}
	}

	public int getLightningCooldown() {
		return this.lightningCooldown;
	}

	public void setLightningCooldown(int lightningCooldown) {
		this.lightningCooldown = lightningCooldown;
	}


	@Override
	public int getShockWaveCooldown() {
		return 350;
	}

	@Override
	public int getShockWaveTicks() {
		return this.shockwaveCooldown;
	}

	@Override
	public void setShockWaveTicks(int ticks) {
		this.shockwaveCooldown = ticks;
	}

	@Override
	public void handleDamageEvent(DamageSource damageSource) {
		super.handleDamageEvent(damageSource);
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public void thunderHit(ServerLevel world, LightningBolt lightning) {
		this.heal(5);
	}

	@Override
	public int getDodgeAttackCooldown() {
		return 100;
	}

	@Override
	public int getDodgeAttackTicks() {
		return this.dodgeAttackCooldown;
	}

	@Override
	public void setDodgeAttackTicks(int ticks) {
		this.dodgeAttackCooldown = ticks;
	}

	@Override
	public void die(DamageSource damageSource) {
		super.die(damageSource);

		if (this.level() instanceof ServerLevel serverWorld) {
			int randomMessageIndex = this.random.nextInt(this.deathMessages.length);

			McCodeHelper.sendMessageToNearbyPlayers(
				serverWorld, this.position(), 10, this.deathMessages[randomMessageIndex]
			);
		}
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new DodgeAttackGoal<Entity303>(this, 15, 2.0, 13f));
		this.goalSelector.addGoal(2, new ShockWaveInvokeGoal<Entity303>(this, 12, 10));
		this.goalSelector.addGoal(3, new ShootLightningGoal<Entity303>(this));
		this.goalSelector.addGoal(
			2,
			new SummonEntitiesGoal<Entity303, AggressiveNullEntity>(
				this, AggressiveNullEntity.class, ModEntities.AGGRESSIVE_NULL, 5
			)
		);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ENTITY303_DEATH;
	}
}
