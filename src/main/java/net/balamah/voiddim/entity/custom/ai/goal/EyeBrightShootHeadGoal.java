package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;

import net.balamah.voiddim.entity.custom.EyeBrightHeadEntity;
import net.balamah.voiddim.entity.custom.EyeBrightEntity;
import net.balamah.voiddim.entity.ModEntities;

import java.util.Random;

public class EyeBrightShootHeadGoal
	extends ShootProjectileGoal<EyeBrightEntity, EyeBrightHeadEntity>
{
	public EyeBrightShootHeadGoal(
		EyeBrightEntity entity, SoundEvent shootPrepareSound, SoundEvent shootStartSound,
		int shootingPrepareCooldown, int shootingCooldown
	) {
		super(
			entity, world -> new EyeBrightHeadEntity(ModEntities.EYE_BRIGHT_HEAD, world),
			shootPrepareSound, shootStartSound,
			shootingPrepareCooldown, shootingCooldown
		);
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && this.entity.getShootTicks() == 0;
	}

	@Override
	public void start() {
		super.start();

		this.addSpeedModifier();
	}

	@Override
	public void stop() {
		super.stop();

		int randomAddition = new Random().nextInt(30);

		this.entity.setShootTicks(this.entity.getShootCooldown() + randomAddition);
		this.removeSpeedModifier();
	}
}
