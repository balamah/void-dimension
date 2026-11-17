package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.ScreamBeforeAttackGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ScreamBeforeAttackUser;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
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
}
