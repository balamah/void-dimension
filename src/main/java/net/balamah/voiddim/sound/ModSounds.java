package net.balamah.voiddim.sound;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class ModSounds {
	public static final SoundEvent WIND_MANIPULATION_JUMP =
		register("wind_manipulation.jump");

	public static final SoundEvent CORRUPTED_STALKER_ATTACK =
		register("corrupted_stalker.attack");

	public static final SoundEvent CORRUPTED_STALKER_HIT =
		register("corrupted_stalker.hit");

	public static final SoundEvent CORRUPTED_STALKER_DEATH =
		register("corrupted_stalker.death");

	public static final SoundEvent SHATTERED_SENTINEL_HIT =
		register("shattered_sentinel.hit");

	public static final SoundEvent SHATTERED_SENTINEL_DEATH =
		register("shattered_sentinel.death");

	public static final SoundEvent SHATTERED_SENTINEL_MASTER_HIT =
		register("shattered_sentinel_master.hit");

	public static final SoundEvent SHATTERED_SENTINEL_MASTER_DEATH =
		register("shattered_sentinel_master.death");

	public static final SoundEvent WEREWOLF_HIT =
		register("werewolf.hit");

	public static final SoundEvent WEREWOLF_AMBIENT =
		register("werewolf.ambient");

	public static final SoundEvent WEREWOLF_ATTACK =
		register("werewolf.attack");

	public static final SoundEvent WEREWOLF_DEATH =
		register("werewolf.death");

	public static final SoundEvent HOLLOWED_BEAST_AMBIENT =
		register("hollowed_beast.ambient");

	public static final SoundEvent HOLLOWED_BEAST_DEATH =
		register("hollowed_beast.death");

	public static final SoundEvent HOLLOWED_BEAST_HIT =
		register("hollowed_beast.hit");

	public static final SoundEvent VOID_HARBINGER_DEATH =
		register("void_harbinger.death");

	public static final SoundEvent VOID_HARBINGER_HIT =
		register("void_harbinger.hit");

	public static final SoundEvent VOID_HARBINGER_SHOOT_PREPARE =
		register("void_harbinger.shoot_prepare");

	public static final SoundEvent VOID_HARBINGER_SHOOT =
		register("void_harbinger.shoot");

	public static final SoundEvent VOID_HARBINGER_TELEPORT =
		register("void_harbinger.teleport");

	public static final SoundEvent WORM_OF_CORRUPTION_DEATH =
		register("worm_of_corruption.death");

	public static final SoundEvent WORM_OF_CORRUPTION_HIT =
		register("worm_of_corruption.hit");

	public static final SoundEvent WORM_OF_CORRUPTION_DIG_UP =
		register("worm_of_corruption.dig_up");

	public static final SoundEvent WORM_OF_CORRUPTION_DIG_DOWN =
		register("worm_of_corruption.dig_down");

	public static final SoundEvent WORM_OF_CORRUPTION_ATTACK =
		register("worm_of_corruption.attack");

	public static final Holder.Reference<SoundEvent> VOID_SPHERE_BURST =
		registerReference("void_sphere.burst");

	public static final Holder.Reference<SoundEvent> SHOCKWAVE =
		registerReference("shockwave");

	public static final SoundEvent CORRUPTED_BLAZE_DEATH =
		register("corrupted_blaze.death");

	public static final SoundEvent CORRUPTED_BLAZE_AMBIENT =
		register("corrupted_blaze.ambient");

	public static final SoundEvent CORRUPTED_BLAZE_HIT =
		register("corrupted_blaze.hit");

	public static final SoundEvent VOID_AMBIENT_1 = register("void.ambient.1");
	public static final SoundEvent VOID_AMBIENT_2 = register("void.ambient.2");
	public static final SoundEvent MUSIC_VOID = register("music.void");

	public static final SoundEvent HOLLOWED_ALPHA_STEVE_HIT =
		register("hollowed_alpha_steve.hit");

	public static final SoundEvent ZOMBIFIED_ALPHA_STEVE_HIT =
		register("zombified_alpha_steve.hit");

	public static final SoundEvent ZOMBIFIED_ALPHA_STEVE_DEATH =
			register("zombified_alpha_steve.death");

	public static final SoundEvent STARING_ENTITY_DEATH = register("staring_entity.death");

	public static final SoundEvent MUSIC_CALM4 = register("music_disc.calm4");
    public static final ResourceKey<JukeboxSong> MUSIC_CALM4_KEY =
		ResourceKey.create(
			Registries.JUKEBOX_SONG,
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "calm4")
		);

	public static final SoundEvent LIGHTNING = register("special_attacks.lightning");
	public static final SoundEvent COUNTER_ATTACK = register("special_attacks.counter_attack");

	public static final SoundEvent ENTITY303_DEATH = register("entity303.death");

	public static final SoundEvent CONSUMED_SOUL_HIT = register("consumed_soul.hit");

	public static final SoundEvent EYE_BRIGHT_SHOOT_PREPARE =
		register("eye_bright.shoot_prepare");

	public static final SoundEvent MAGNET_PREPARE = register("special_attacks.magnet_prepare");
	public static final SoundEvent MAGNET_EXECUTE = register("special_attacks.magnet_execute");

	public static final SoundEvent CORRUPTED_WARRIOR_DARK_GRASP_PREPARE =
		register("corrupted_warrior.dark_grasp_prepare");

	public static void registerModSounds() {
		VoidDimension.LOGGER.info("Registering mod sounds for " + VoidDimension.MOD_ID);
	}

	protected static SoundEvent register(String id) {
		Identifier identifier = Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, id);

		return Registry.register(
			BuiltInRegistries.SOUND_EVENT,
			identifier,
			SoundEvent.createVariableRangeEvent(identifier)
		);
	}

	protected static SoundEvent register(String id, float range) {
		Identifier identifier = Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, id);

		return Registry.register(
			BuiltInRegistries.SOUND_EVENT,
			identifier,
			SoundEvent.createFixedRangeEvent(identifier, range)
		);
	}

	protected static Holder.Reference<SoundEvent> registerReference(String id) {
		Identifier identifier = Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, id);

		return Registry.registerForHolder(
			BuiltInRegistries.SOUND_EVENT,
			identifier,
			SoundEvent.createVariableRangeEvent(identifier)
		);
	}
}
