package net.balamah.voiddim.interfaces;

import net.minecraft.sounds.SoundEvent;

public interface DarkGraspUser {
	public int getDarkGraspInvokeCooldown();
	public int getDarkGraspInvokeTicks();
	public void setDarkGraspInvokeTicks(int ticks);
	public SoundEvent getDarkGraspSound();
}
