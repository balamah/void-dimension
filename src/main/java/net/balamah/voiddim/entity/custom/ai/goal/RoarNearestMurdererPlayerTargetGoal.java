package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.base.ModHostileEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class RoarNearestMurdererPlayerTargetGoal extends NearestMurdererPlayerTargetGoal {
	protected int tick;

	protected final int roarDurationTicks;
	
	protected final Identifier attributeId = Identifier.withDefaultNamespace("speed");
	protected final AttributeModifier attributeModifier =
		new AttributeModifier(
			this.attributeId, -2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected final AttributeInstance entityAttributeInstance;
	
	public RoarNearestMurdererPlayerTargetGoal(
		ModHostileEntity entity, double range, SoundEvent attackSound, int roarDurationTicks
	) {
		super(entity, range, attackSound);

		this.roarDurationTicks = roarDurationTicks;
		this.entityAttributeInstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
	}

	@Override
	public void start() {
		super.start();
		
		this.entity.level().broadcastEntityEvent(this.entity, ModEntityStatuses.ROAR);
		if (!this.entityAttributeInstance.hasModifier(this.attributeId)) {
			this.entityAttributeInstance.addTransientModifier(this.attributeModifier);
		}
	}

	@Override
	public void stop() {
		super.stop();

		this.stopRoar();
	}

	@Override
	public void tick() {
		super.tick();

		this.tick++;

		if (this.tick == this.roarDurationTicks) {
			this.stopRoar();
		}
	}

	protected void stopRoar() {
		this.entity.level().broadcastEntityEvent(this.entity, ModEntityStatuses.ROAR_STOP);
		this.entityAttributeInstance.removeModifier(this.attributeId);
	}
}
