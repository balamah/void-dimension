package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.entity.custom.VoidLightningEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "thunderHit", at = @At("HEAD"), cancellable = true)
	private void onStruckByLightning(
		ServerLevel world, LightningBolt lightning, CallbackInfo ci
	) {
		Entity struckEntity = (Entity)(Object)this;

		struckEntity.setRemainingFireTicks(struckEntity.getRemainingFireTicks() + 1);
		if (struckEntity.getRemainingFireTicks() == 0) {
			struckEntity.igniteForSeconds(8.0F);
		}

		float damage = (lightning instanceof VoidLightningEntity) ? 16f : 5f;

		struckEntity.hurt(struckEntity.damageSources().lightningBolt(), damage);

		ci.cancel();
	}
}
