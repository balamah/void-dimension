package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.LivingEntity;

import net.balamah.voiddim.event.custom.CorruptionInDarknessCallback;

@Mixin(LivingEntity.class)
public class LivingEntityTickMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	protected void onTick(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		CorruptionInDarknessCallback.EVENT.invoker().interact(entity);
	}
}
