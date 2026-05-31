package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.resources.ResourceLocation;

public class CorruptedPlayerRenderState extends AvatarRenderState {
	public String playerName = "";
	public ProfileComponent playerProfile = ResolvableProfile.Static.EMPTY;
	public ResourceLocation skinIdentifier;
}
