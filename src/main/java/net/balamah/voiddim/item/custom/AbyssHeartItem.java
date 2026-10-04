package net.balamah.voiddim.item.custom;

import java.util.EnumSet;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AbyssHeartItem extends Item {
	public AbyssHeartItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (level.isClientSide()) {
			return InteractionResult.PASS;
		}

		EnumSet<Relative> flags = EnumSet.of(Relative.X, Relative.Y, Relative.Z);
		ServerLevel overworld = player.level().getServer().getLevel(Level.OVERWORLD);

		if (player.level().dimension() != ModDimensions.ABYSS_WORLD || overworld == null) {
			return InteractionResult.FAIL;
		}

		player.teleportTo(
			overworld, player.getX() * 12, 240, player.getZ() * 12,
			flags, player.getYRot(), player.getXRot(), true
		);

		player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 800, 1));

		return InteractionResult.SUCCESS;
	}
}
