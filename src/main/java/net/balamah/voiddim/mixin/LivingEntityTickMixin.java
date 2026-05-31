package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import net.balamah.voiddim.event.custom.CorruptionInDarknessCallback;
import net.balamah.voiddim.effect.ModEffects;

@Mixin(LivingEntity.class)
public class LivingEntityTickMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	protected void onTick(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		CorruptionInDarknessCallback.EVENT.invoker().interact(entity);
	}
	
	// Give bonus damage when soul burn effect is applied
	@ModifyVariable(method = "hurt", at = @At("HEAD"), argsOnly = true)
	protected float modifyDamage(float damage, final DamageSource source) {
		LivingEntity entity = (LivingEntity)(Object) this;
		MobEffectInstance soulBurnEffect = entity.getEffect(ModEffects.SOUL_BURN);
		float damageReductionMultiplier =
			(entity.hasEffect(ModEffects.DIVINE_PROTECTION)) ? 0.6f : 1;

		if (soulBurnEffect != null) {
			float damageMultiplier = (1.7f + soulBurnEffect.getAmplifier()) * damageReductionMultiplier;
			damage *= damageMultiplier;
		}

		return damage;
	}
}
