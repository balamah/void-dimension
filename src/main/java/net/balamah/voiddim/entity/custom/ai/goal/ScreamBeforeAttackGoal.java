package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.base.EntityAttributeModifierGoal;
import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.balamah.voiddim.interfaces.ScreamBeforeAttackUser;
import net.minecraft.sounds.SoundEvent;

/*
 * TODO: Implement ScreamBeforeAttackGoal(T entity, int screamTicks):
 * - start()
 *   + play roar animation
 *   + play roar `this.roarSound`
 *   + add attribute modifier to slow down the entity
 * - stop()
 *   + stop roar animation
 *   + delete all attribute modifiers
 * - canContinueToUse()
 *   if (this.entity != null) and (this.tick <= this.screamTicks)
 */
public class ScreamBeforeAttackGoal<T extends ModHostileEntity & ScreamBeforeAttackUser>
	extends EntityAttributeModifierGoal<T>
{
	protected final int screamTicks;
	protected final SoundEvent roarSound;

	public ScreamBeforeAttackGoal(T entity, int screamTicks, SoundEvent roarSound) {
		super(entity);

		this.screamTicks = screamTicks;
		this.roarSound = roarSound;
	}

	@Override
	public void start() {
		super.start();

		this.sendEntityStatus(ModEntityStatuses.ROAR);
	}

	@Override
	public void stop() {
		super.stop();

		this.sendEntityStatus(ModEntityStatuses.ROAR_STOP);
		this.entity.setScreamBeforeAttackTicks(this.entity.getScreamBeforeAttackCooldown());
	}

	@Override
	public boolean canUse() {
		return this.entity.getTarget() != null && this.entity.getScreamBeforeAttackTicks() == 0;
	}

	@Override
	public boolean canContinueToUse() {
		// TODO Auto-generated method stub
		return super.canContinueToUse();
	}
}
