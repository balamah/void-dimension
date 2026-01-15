package net.balamah.voiddim.event.custom;

import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public interface CorruptionInDarknessCallback {
	Event<CorruptionInDarknessCallback> EVENT = 
		EventFactory.createArrayBacked(
			CorruptionInDarknessCallback.class,
			(listeners) -> (entity) -> {
				for (CorruptionInDarknessCallback listener : listeners) {
					ActionResult result = listener.interact(entity);

					if (result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			}
		);

	public ActionResult interact(LivingEntity entity);
}
