package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.item.component.ResolvableProfile;

public class CorruptedPlayerRenderState extends AvatarRenderState {
	public String playerName = "";
	public ResolvableProfile playerProfile = ResolvableProfile.Static.EMPTY;
}
