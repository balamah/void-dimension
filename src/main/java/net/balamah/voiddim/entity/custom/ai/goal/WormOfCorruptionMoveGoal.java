package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.base.OneShotDamageGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.StationaryAttackUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.sound.ModSounds;

import java.util.ArrayList;

public class WormOfCorruptionMoveGoal<T extends CorruptedHostileEntity & StationaryAttackUser>
	extends OneShotDamageGoal<T>
{
	protected final int maxAttackCountBeforeMoving;

	protected final Random random = Random.create();

	protected ArrayList<Byte> sentStatuses = new ArrayList<Byte>();
	protected double teleportX, teleportY, teleportZ;
	protected int cooldown;

	public WormOfCorruptionMoveGoal(
		T entity, int maxAttackCountBeforeMoving
	) {
		super(entity);

		this.maxAttackCountBeforeMoving = maxAttackCountBeforeMoving;
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
		super.start();

		LivingEntity target = this.entity.getTarget();

		this.entity.setInvulnerable(true);
		this.entity.setStopAttacks(true);
		this.setCoordinates(target);
		this.sendEntityStatus(ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN);
	}

	@Override
	public void tick() {
		super.tick();

		World world = this.entity.getEntityWorld();

		if (!(world instanceof ServerWorld serverWorld)) {
			return;
		}

		if (this.tick == 5) {
			world.playSound(
				null, this.teleportX, this.teleportY, this.teleportZ,
				ModSounds.WORM_OF_CORRUPTION_DIG_DOWN, SoundCategory.AMBIENT,
				2.0F, 1.0F
			);
		}

		if (this.tick == 15) {
			this.entity.teleport(this.teleportX, this.teleportY, this.teleportZ, true);
			this.entity.setInvulnerable(false);

			this.entity.playSound(ModSounds.WORM_OF_CORRUPTION_DIG_UP);
			this.sendEntityStatus(ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN_STOP);

			this.addModifier(this.attributeInstance, this.attributeId, this.attributeModifier);
		}
	}

	@Override
	public void stop() {
		super.stop();

		this.sentStatuses.clear();
		this.sendEntityStatus(ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN_STOP);
		this.sendEntityStatus(ModEntityStatuses.WORM_OF_CORRUPTION_DIG_UP);
		this.entity.setStopAttacks(false);
		this.entity.setInvulnerable(false);
		this.removeModifier(this.attributeInstance, this.attributeModifier);

		this.entity.attackCount = 0;
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null && this.tick <= 16;
	}

	@Override
	protected void sendEntityStatus(byte status) {
		if (this.sentStatuses.contains(status)) {
			return;
		}

		this.world.sendEntityStatus(this.entity, status);

		this.sentStatuses.add(status);
	}

	protected void setCoordinates(LivingEntity target) {
		this.teleportX = target.getX();
		this.teleportY = target.getY();
		this.teleportZ = target.getZ();
	}
}
