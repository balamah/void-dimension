package net.balamah.voiddim.custom;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

import net.minecraft.server.level.ServerPlayer;

public class CorruptedEntityKilledTracker {
    public static final int DURATION = 1200;
    private static final Map<UUID, Long> LAST_KILL = new HashMap<>();

    public static void markKill(ServerPlayer player) {
        LAST_KILL.put(player.getUUID(), player.level().getGameTime());
    }

    public static boolean hasRecentKill(ServerPlayer player) {
        Long time = LAST_KILL.get(player.getUUID());
        return time != null && player.level().getGameTime() - time < DURATION;
    }
}
