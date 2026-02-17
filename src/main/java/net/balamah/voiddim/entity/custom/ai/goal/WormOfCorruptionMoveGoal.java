package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.WormOfCorruptionEntity;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.sound.ModSounds;

import java.util.ArrayList;

public class WormOfCorruptionMoveGoal extends Goal {
	protected final WormOfCorruptionEntity entity;
	protected final int maxAttackCountBeforeMoving;

	protected final Random random = Random.create();

	protected ArrayList<Byte> sentStatuses = new ArrayList<Byte>();
	protected double teleportX, teleportY, teleportZ;
	protected int cooldown;
	protected EntityAttributeInstance attackAttributeInstance;

	protected final Identifier attributeId = Identifier.ofVanilla("attacking");
	protected final EntityAttributeModifier attackAttributeInstanceModifier =
		new EntityAttributeModifier(
			this.attributeId,
			10.0F,
			EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	public WormOfCorruptionMoveGoal(
			WormOfCorruptionEntity entity, int maxAttackCountBeforeMoving
	) {
		this.entity = entity;
		this.maxAttackCountBeforeMoving = maxAttackCountBeforeMoving;
		this.attackAttributeInstance =
			this.entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		if (target == null) return false;

		BlockPos blockPos = this.entity.getBlockPos().down();
		Block block = McCodeHelper.getBlock(this.entity.getEntityWorld(), blockPos);

		return this.entity.distanceTo(target) > 1 &&
			   this.entity.attackCount == this.maxAttackCountBeforeMoving &&
			   McCodeHelper.isBlockReplaceable(block)
			   ;
	}

	@Override
	public void start() {
		this.cooldown = 0;

		LivingEntity target = this.entity.getTarget();

		this.entity.setInvulnerable(true);
		this.entity.setStopAttacks(true);
		this.setCoordinates(target);
		this.sendStatus(
			this.entity.getEntityWorld(), ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN
		);
	}

	@Override
	public void tick() {
		super.tick();

		World world = this.entity.getEntityWorld();

		if (!(world instanceof ServerWorld serverWorld)) {
			return;
		}

		this.cooldown++;

		if (this.cooldown == 5) {
			world.playSound(
				null, this.teleportX, this.teleportY, this.teleportZ,
				ModSounds.WORM_OF_CORRUPTION_DIG_DOWN, SoundCategory.AMBIENT,
				2.0F, 1.0F
			);
		}

		if (this.cooldown == 15) {
			this.entity.teleport(this.teleportX, this.teleportY, this.teleportZ, true);
			this.entity.setInvulnerable(false);

			this.entity.playSound(ModSounds.WORM_OF_CORRUPTION_DIG_UP);
			this.sendStatus(serverWorld, ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN_STOP);

			if (!this.attackAttributeInstance.hasModifier(this.attributeId)) {
				this.attackAttributeInstance.addTemporaryModifier(
					this.attackAttributeInstanceModifier
				);
			}
		}
	}

	@Override
	public void stop() {
		super.stop();

		World world = this.entity.getEntityWorld();

		this.sentStatuses.clear();
		this.sendStatus(world, ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN_STOP);
		this.sendStatus(world, ModEntityStatuses.WORM_OF_CORRUPTION_DIG_UP);
		this.attackAttributeInstance.removeModifier(this.attackAttributeInstanceModifier);
		this.entity.setStopAttacks(false);
		this.entity.setInvulnerable(false);

		this.entity.attackCount = 0;
		this.cooldown = 0;
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null && this.cooldown <= 16;
	}

	protected void sendStatus(World world, byte entitySignal) {
		if (this.sentStatuses.contains(entitySignal)) {
			return;
		}

		world.sendEntityStatus(this.entity, entitySignal);

		this.sentStatuses.add(entitySignal);
	}

	protected void setCoordinates(LivingEntity target) {
		this.teleportX = target.getX();
		this.teleportY = target.getY();
		this.teleportZ = target.getZ();
	}
}
