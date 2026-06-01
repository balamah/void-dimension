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
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.level.storage.ValueOutput;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements VoidPrayerDataAccess {
    protected final NbtCompound voidPrayerData = new NbtCompound();
	protected final String nbtKey = "VoidPrayerData";

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    protected void writePrayerData(ValueOutput writeView, CallbackInfo ci) {
		writeView.store(this.nbtKey, NbtCompound.CODEC, this.voidPrayerData);
    }

	@Inject(method = "onDeath", at = @At("TAIL"))
	protected void spawnCorruptedPlayer(DamageSource source, CallbackInfo ci) {
		ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;

		if (player.getWorld().getRegistryKey() == ModDimensions.VOID_WORLD &&
			player.hasStatusEffect(ModEffects.CORRUPTION) && McCodeHelper.isPlayerInSurvival(player)
		) {
			CorruptedPlayerEntity corruptedPlayer =
				new CorruptedPlayerEntity(ModEntities.CORRUPTED_PLAYER, player.getWorld());

			corruptedPlayer.copyDeadPlayer(player);
			player.getWorld().spawnEntity(corruptedPlayer);
		}
	}

    @Override
    public NbtCompound getVoidPrayerData() {
        return this.voidPrayerData;
    }
}
