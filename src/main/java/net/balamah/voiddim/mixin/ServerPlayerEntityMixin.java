package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.interfaces.VoidPrayerDataAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.ValueOutput;

@Mixin(ServerPlayer.class)
public class ServerPlayerEntityMixin implements VoidPrayerDataAccess {
    protected final CompoundTag voidPrayerData = new CompoundTag();
	protected final String nbtKey = "VoidPrayerData";

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void writePrayerData(ValueOutput writeView, CallbackInfo ci) {
		writeView.store(this.nbtKey, CompoundTag.CODEC, this.voidPrayerData);
    }

    @Override
    public CompoundTag getVoidPrayerData() {
        return this.voidPrayerData;
    }
}
