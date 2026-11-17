package net.balamah.voiddim.entity.custom;

import java.util.List;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.ScreamBeforeAttackGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ScreamBeforeAttackUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class UnknownOneEntity extends CorruptedHostileEntity implements ScreamBeforeAttackUser {
	public final AnimationState rageBaitAnimationState = new AnimationState();

	protected boolean isScreaming = false;
	protected int screamBeforeAttackCooldown = 2000;
	protected int screamBeforeAttackTicks = 5;

	public UnknownOneEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public int getScreamBeforeAttackCooldown() {
		return this.screamBeforeAttackCooldown;
	}

	@Override
	public int getScreamBeforeAttackTicks() {
		return this.screamBeforeAttackTicks;
	}

	@Override
	public void setScreamBeforeAttackTicks(int ticks) {
		this.screamBeforeAttackTicks = ticks;
	}

	@Override
	public boolean isScreaming() {
		return this.isScreaming;
	}

	@Override
	public void setScreaming(boolean screaming) {
		this.isScreaming = screaming;
	}

	@Override
	public void handleEntityEvent(byte id) {
		super.handleEntityEvent(id);

		switch (id) {
			case ModEntityStatuses.ROAR:
				this.rageBaitAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.ROAR_STOP:
				this.rageBaitAnimationState.stop();
				break;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.screamBeforeAttackTicks > 0) {
			this.screamBeforeAttackTicks--;
		}

		if (this.getTarget() == null) {
			this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(
				this.getRoarEndAttributeModifier()
			);
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();

		boolean shouldStop = this.shouldStop();
		VoidDimension.LOGGER.info("Should stop? {}", shouldStop);
		if (shouldStop) {
			this.getNavigation().stop();
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.2F)
			.add(Attributes.ATTACK_DAMAGE, 1.0F)
			.add(Attributes.STEP_HEIGHT, 1.0)
			.add(Attributes.MAX_HEALTH, 55);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(
			1, new ScreamBeforeAttackGoal<>(this, 16, ModSounds.GHOST_ATTACKED)
		);
	}

	protected boolean shouldStop() {
		List<Player> players = this.level().getEntitiesOfClass(
			Player.class,
			this.getBoundingBox().inflate(10),
			e -> e.isAlive() && !e.isSpectator() && e instanceof ServerPlayer player &&
			player.gameMode.getGameModeForPlayer() != GameType.CREATIVE
		);

		for (Player player : players) {
			double eyeY = this.getEyeY();
			double middleY = this.getY() + 0.5 * this.getScale();
			double centerY = (this.getEyeY() + this.getY()) / 2.0;
			boolean playerSees = this.isLookingAtMe(
				player, 0.5, false, true, eyeY, middleY, centerY
			);

			if (!playerSees) {
				return true;
			}
		}

		return this.getTarget() == null;
	}
}
