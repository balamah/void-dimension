package net.balamah.voiddim.interfaces;

import net.minecraft.sound.SoundEvent;

public interface DarkGraspUser {
	public int getDarkGraspInvokeCooldown();
	public int getDarkGraspInvokeTicks();
	public void setDarkGraspInvokeTicks(int ticks);
	public SoundEvent getDarkGraspSound();
}
