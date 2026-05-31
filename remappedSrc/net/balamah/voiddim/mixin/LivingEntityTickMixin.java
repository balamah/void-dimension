package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.event.custom.CorruptionInDarknessCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.balamah.voiddim.effect.ModEffects;

@Mixin(LivingEntity.class)
public class LivingEntityTickMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	protected void onTick(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		CorruptionInDarknessCallback.EVENT.invoker().interact(entity);
	}
	
	// Give bonus damage when soul burn effect is applied
	@ModifyVariable(method = "hurtServer", at = @At("HEAD"), argsOnly = true)
	protected float modifyDamage(float damage, ServerWorld level, final DamageSource source) {
		LivingEntity entity = (LivingEntity)(Object) this;
		StatusEffectInstance soulBurnEffect = entity.getStatusEffect(ModEffects.SOUL_BURN);
		float damageReductionMultiplier =
			(entity.hasStatusEffect(ModEffects.DIVINE_PROTECTION)) ? 0.6f : 1;

		if (soulBurnEffect != null) {
			float damageMultiplier = (1.7f + soulBurnEffect.getAmplifier()) * damageReductionMultiplier;
			damage *= damageMultiplier;
		}

		return damage;
	}
}
