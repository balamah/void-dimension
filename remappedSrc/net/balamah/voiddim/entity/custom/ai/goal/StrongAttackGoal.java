package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.OneShotDamageGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.StrongAttackUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.sound.SoundEvent;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class StrongAttackGoal<T extends CorruptedHostileEntity & StrongAttackUser>
	extends OneShotDamageGoal<T>
{
	protected EntityAttributeModifier attributeModifier = this.getAttributeModifier(
		this.attributeId, 9, EntityAttributeModifier.Operation.ADD_VALUE
	);

	protected final int stopTick;
	protected final int maxDistance;
	protected final SoundEvent attackSound;

	protected boolean didAttack;

	public StrongAttackGoal(
		T entity, int stopTick, int maxDistance, SoundEvent attackSound
	) {
		super(entity);

		this.stopTick = stopTick;
		this.attackSound = attackSound;
		this.maxDistance = maxDistance;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && this.entity.getStrongAttackTicks() == 0 &&
			this.entity.distanceTo(target) <= this.maxDistance;
	}

	@Override
	public void start() {
		super.start();

		this.addModifier(this.attributeInstance, this.attributeId, this.attributeModifier);
		this.sendEntityStatus(ModEntityStatuses.STRONG_ATTACK);
	}

	@Override
	public void stop() {
		super.stop();

		this.didAttack = false;

		this.sendEntityStatus(ModEntityStatuses.STOP_SPECIAL_ATTACK);
		this.removeModifier(this.attributeInstance, this.attributeModifier);
		this.entity.setStrongAttackTicks(this.entity.getStrongAttackCooldown());
	}

	@Override
	public boolean shouldContinue() {
		return !this.didAttack;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == this.stopTick) {
			this.entity.playSoundIfNotSilent(this.attackSound);

			this.didAttack = true;
		}
	}
}
