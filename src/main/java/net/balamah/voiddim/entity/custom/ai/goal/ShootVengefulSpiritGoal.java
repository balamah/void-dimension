package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.VengefulSpiritEntity;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvent;

public class ShootVengefulSpiritGoal<E extends CorruptedHostileEntity>
	extends ShootProjectileGoal<E, VengefulSpiritEntity>
{
	public ShootVengefulSpiritGoal(
		E entity, SoundEvent shootPrepareSound, SoundEvent shootStartSound,
		int shootingPrepareCooldown, int shootingCooldown
	) {
		super(
			entity,
			world -> new VengefulSpiritEntity(ModEntities.VENGEFUL_SPIRIT, world),
			shootPrepareSound,
			shootStartSound,
			shootingPrepareCooldown, shootingCooldown
		);
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		return target != null && !this.entity.areAttacksStopped() &&
			this.entity.attackCount % 5 == 0;
	}
}
