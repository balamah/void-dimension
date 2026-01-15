package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.WriteView;
import net.minecraft.nbt.NbtCompound;

import net.balamah.voiddim.interfaces.VoidPrayerDataAccess;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements VoidPrayerDataAccess {
    protected final NbtCompound voidPrayerData = new NbtCompound();
	protected final String nbtKey = "VoidPrayerData";

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    protected void writePrayerData(WriteView writeView, CallbackInfo ci) {
		writeView.put(this.nbtKey, NbtCompound.CODEC, this.voidPrayerData);
    }

    @Override
    public NbtCompound getVoidPrayerData() {
        return this.voidPrayerData;
    }
}
