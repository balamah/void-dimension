package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.balamah.voiddim.entity.custom.VoidSphereEntity;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

public class ShatteredSentinelMasterShootGoal
	extends ShootProjectileGoal<ShatteredSentinelMasterEntity, VoidSphereEntity>
{
	public ShatteredSentinelMasterShootGoal(ShatteredSentinelMasterEntity entity) {
		super(
			entity,
			world -> new VoidSphereEntity(ModEntities.VOID_SPHERE, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50,
			30
		);
	}

	@Override
	public boolean canStart() {
		if (!super.canStart()) {
			return false;
		}

		return this.entity.getTarget().getY() - this.entity.getY() >= 2;
	}
}
