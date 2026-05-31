package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.entity.custom.CorruptedPlayerEntity;
import net.balamah.voiddim.interfaces.VoidPrayerDataAccess;
import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;

@Mixin(ServerPlayer.class)
public class ServerPlayerEntityMixin implements VoidPrayerDataAccess {
    protected final CompoundTag voidPrayerData = new CompoundTag();
	protected final String nbtKey = "VoidPrayerData";

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void writePrayerData(CompoundTag nbt, CallbackInfo ci) {
		nbt.put(this.nbtKey, this.voidPrayerData);
    }

	@Inject(method = "die", at = @At("TAIL"))
	protected void spawnCorruptedPlayer(DamageSource source, CallbackInfo ci) {
		ServerPlayer player = (ServerPlayer)(Object)this;

		if (player.level().dimension() == ModDimensions.VOID_WORLD &&
			player.hasEffect(ModEffects.CORRUPTION) && McCodeHelper.isPlayerInSurvival(player)
		) {
			CorruptedPlayerEntity corruptedPlayer =
				new CorruptedPlayerEntity(ModEntities.CORRUPTED_PLAYER, player.level());

			corruptedPlayer.copyDeadPlayer(player);
			player.level().addFreshEntity(corruptedPlayer);
		}
	}

    @Override
    public CompoundTag getVoidPrayerData() {
        return this.voidPrayerData;
    }
}
