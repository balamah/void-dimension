package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.entity.custom.VoidLightningEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
	private void onStruckByLightning(
		ServerWorld world, LightningEntity lightning, CallbackInfo ci
	) {
		Entity struckEntity = (Entity)(Object)this;

		struckEntity.setFireTicks(struckEntity.getFireTicks() + 1);
		if (struckEntity.getFireTicks() == 0) {
			struckEntity.setOnFireFor(8.0F);
		}

		float damage = (lightning instanceof VoidLightningEntity) ? 16f : 5f;

		struckEntity.hurtServer(world, struckEntity.getDamageSources().lightningBolt(), damage);

		ci.cancel();
	}
}
