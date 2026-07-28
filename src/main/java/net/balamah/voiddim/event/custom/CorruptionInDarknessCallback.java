package net.balamah.voiddim.event.custom;

import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.fabricmc.fabric.api.event.Event;

public interface CorruptionInDarknessCallback {
	Event<CorruptionInDarknessCallback> EVENT = 
		EventFactory.createArrayBacked(
			CorruptionInDarknessCallback.class,
			(listeners) -> (entity) -> {
				for (CorruptionInDarknessCallback listener : listeners) {
					InteractionResult result = listener.interact(entity);

					if (result != InteractionResult.PASS) {
						return result;
					}
				}

				return InteractionResult.PASS;
			}
		);

	public InteractionResult interact(LivingEntity entity);
}
