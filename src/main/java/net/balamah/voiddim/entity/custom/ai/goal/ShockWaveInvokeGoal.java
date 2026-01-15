package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.collection.Pool;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.balamah.voiddim.entity.custom.VoidSphereEntity;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;

import java.util.List;

public class ShockWaveInvokeGoal extends Goal {
	protected final ShatteredSentinelMasterEntity entity;
	protected final int invocationTickCooldown;
	protected final float entitySpeed;
	protected final float radius;

	protected final List<RegistryEntry<StatusEffect>> effects = List.of(
		StatusEffects.SLOWNESS,
		StatusEffects.BLINDNESS,
		StatusEffects.WEAKNESS
	);

	protected final Identifier attributeId = Identifier.ofVanilla("speed");
	protected final EntityAttributeModifier attributeModifier =
		new EntityAttributeModifier(
			this.attributeId, -2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final EntityAttributeInstance entityAttributeInstance;

	protected int tick;

	public ShockWaveInvokeGoal(
		ShatteredSentinelMasterEntity entity, int invocationTickCooldown, float radius
	) {
		this.entity = entity;
		this.invocationTickCooldown = invocationTickCooldown;
		this.entitySpeed = this.entity.getMovementSpeed();
		this.entityAttributeInstance =
			this.entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);

		this.radius = radius;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null &&
			target.distanceTo(this.entity) <= 15 &&
			this.entity.getShockWaveTicks() == 0 &&
			!this.entity.areAttacksStopped();
	}

	@Override
	public void start() {
		this.tick = 0;

		if (!this.entityAttributeInstance.hasModifier(this.attributeId)) {
			this.entityAttributeInstance.addTemporaryModifier(this.attributeModifier);
		}

		this.entity.setStopAttacks(true);
		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHOCK_WAVE_INVOKE
		);

		this.entity.playSound(SoundEvents.ENTITY_PLAYER_BREATH, 4, 1);
	}

	@Override
	public void tick() {
		World world = this.entity.getEntityWorld();

		if (!(world instanceof ServerWorld serverWorld)) return;

		this.tick++;

		if (this.tick == this.invocationTickCooldown) {
			this.createWave(serverWorld, entity);
		}
	}

	@Override
	public void stop() {
		super.stop();

		int shockWaveTicks =
			this.entity.getShockWaveCooldown() + this.entity.getRandom().nextInt(100);

		this.entity.setShockWaveTicks(shockWaveTicks);
		this.entity.setStopAttacks(false);
		this.entityAttributeInstance.removeModifier(this.attributeModifier);
		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_SHOCK_WAVE_INVOKE_STOP
		);

		this.entity.attackCount = 0;
		this.tick = 0;
	}

	@Override
	public boolean shouldContinue() {
		return this.tick <= this.invocationTickCooldown;
	}

	protected void createWave(ServerWorld world, Entity entity) {
		world.createExplosion(
			this.entity, null, VoidSphereEntity.EXPLOSION_BEHAVIOR,
			this.entity.getX(), this.entity.getY(), this.entity.getZ(),
			this.radius, false, World.ExplosionSourceType.TRIGGER,
			ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE,
			Pool.empty(), ModSounds.SHOCKWAVE
		);

		List<LivingEntity> entities = entity.getEntityWorld()
			.getEntitiesByClass(
				LivingEntity.class,
				entity.getBoundingBox().expand(this.radius),
				e -> !e.isSpectator()
			);

		for (LivingEntity target : entities) {
			if (target instanceof BossEntity ||
				!McCodeHelper.isTargetVisible(this.entity, target)
			) {
				continue;
			}

			for (RegistryEntry<StatusEffect> effect : this.effects) {
				target.addStatusEffect(new StatusEffectInstance(effect, 1200, 2));
				target.damage(world, ModDamageSources.shockWave(world), 15f);

				this.breakShield(target);
			}
		}
	}

	protected void breakShield(LivingEntity target) {
		if (!(target instanceof PlayerEntity player)) {
			return;
		}

		ItemStack stack = player.getActiveItem();

		if (stack.isOf(Items.SHIELD)) {
			EquipmentSlot slot = (player.getActiveHand() == Hand.MAIN_HAND)
				? EquipmentSlot.MAINHAND
				: EquipmentSlot.OFFHAND;

			stack.damage(150, player, slot);

			McCodeHelper.playSoundFromEntity(target, SoundEvents.ITEM_SHIELD_BREAK);
		}
	}
}
