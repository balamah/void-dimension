package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.custom.GameProfileService;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.lang.InterruptedException;

import com.mojang.authlib.GameProfile;

import java.io.IOException;

public class CorruptedPlayerEntity extends CorruptedHostileEntity {
	protected static String[] playerNames = {
		"Balamah", "legendary_pasha", "ohLucie", "Fruchtkaffee", "AsilOof",
		"AZGAMER2014", "rlthln"
	};

	protected static final EntityDataAccessor<String> PLAYER_NAME =
		SynchedEntityData.defineId(CorruptedPlayerEntity.class, EntityDataSerializers.STRING);

	protected static final EntityDataAccessor<ResolvableProfile> PLAYER_PROFILE =
		SynchedEntityData.defineId(
			CorruptedPlayerEntity.class, EntityDataSerializers.RESOLVABLE_PROFILE
		);

	protected static final String PLAYER_NAME_KEY = "PlayerName";
	protected static final String PLAYER_PROFILE_KEY = "PlayerProfile";

	protected ServerPlayer player;

	protected EquipmentSlot[] playerEquipmentSlots = {
		EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND,
		EquipmentSlot.HEAD, EquipmentSlot.CHEST,
		EquipmentSlot.LEGS, EquipmentSlot.FEET,
	};

	public CorruptedPlayerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 32)
			.add(Attributes.MOVEMENT_SPEED, 0.31F)
			.add(Attributes.ATTACK_DAMAGE, 1.4F)
			.add(Attributes.MAX_HEALTH, 20.0F);
	}

	public void copyDeadPlayer(ServerPlayer player) {
		this.setPlayerName(player.getGameProfile().name());
		this.setPlayerProfile(ResolvableProfile.createResolved(player.getGameProfile()));
		this.snapTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
		this.setYHeadRot(player.yHeadRot);
		this.setYBodyRot(player.yBodyRot);
		this.setCustomName(player.getDisplayName());
		this.setPersistenceRequired();

		this.player = player;

		this.copyEquipment(this.player);
	}

	public String getPlayerName() {
		return this.entityData.get(PLAYER_NAME);
	}

	public void setPlayerName(String playerName) {
		this.entityData.set(PLAYER_NAME, playerName);
	}

	public ResolvableProfile getPlayerProfile() {
		return this.entityData.get(PLAYER_PROFILE);
	}

	public void setPlayerProfile(ResolvableProfile profile) {
		this.entityData.set(PLAYER_PROFILE, profile);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(
		ServerLevelAccessor world,
		DifficultyInstance difficulty,
		EntitySpawnReason spawnReason,
		@Nullable SpawnGroupData entityData
	) {
		entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);

		this.setRandomPlayerIdentity();
		this.populateDefaultEquipmentSlots(this.random, difficulty);
		this.populateDefaultEquipmentEnchantments(world, this.random, difficulty);
		
		return entityData;
	}

	@Override
	protected void populateDefaultEquipmentSlots(
		RandomSource random, DifficultyInstance localDifficulty
	) {
		EquipmentSlot[] armorSlots = {
			EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
		};

		Item[] weaponPool = {
			Items.DIAMOND_AXE, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD, Items.NETHERITE_AXE
		};

		int chosenWeaponIndex = random.nextInt(weaponPool.length);
		Item chosenWeapon = weaponPool[chosenWeaponIndex];
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(chosenWeapon));

		for (EquipmentSlot armorSlot : armorSlots) {
			this.setItemSlot(armorSlot, this.getRandomArmor(armorSlot));	
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(PLAYER_NAME, "");
		entityData.define(PLAYER_PROFILE, ResolvableProfile.Static.EMPTY);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putString(PLAYER_NAME_KEY, this.getPlayerName());
		output.store(PLAYER_PROFILE_KEY, ResolvableProfile.CODEC, this.getPlayerProfile());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		String playerName = input.getStringOr(PLAYER_NAME_KEY, "");
		this.setPlayerName(playerName);
		this.setPlayerProfile(
			input.read(PLAYER_PROFILE_KEY, ResolvableProfile.CODEC)
				.orElseGet(() -> playerName.isBlank()
					? ResolvableProfile.Static.EMPTY
					: ResolvableProfile.createUnresolved(playerName))
		);
	}

	protected void copyEquipment(ServerPlayer player) {
		for (EquipmentSlot slot : this.playerEquipmentSlots) {
			ItemStack stack = player.getItemBySlot(slot);

			if (!stack.isEmpty()) {
				this.setItemSlot(slot, stack);
				this.setDropChance(slot, 0f);
			}
		}
	}

	protected ItemStack getRandomArmor(EquipmentSlot slot) {
		Item[] helmets = { Items.DIAMOND_HELMET, Items.NETHERITE_HELMET };
		Item[] chestplates = { Items.DIAMOND_CHESTPLATE, Items.NETHERITE_CHESTPLATE };
		Item[] leggings = { Items.DIAMOND_LEGGINGS, Items.NETHERITE_LEGGINGS };
		Item[] boots = { Items.DIAMOND_BOOTS, Items.NETHERITE_BOOTS };
		Item[] targetArray;

		switch (slot) {
			case HEAD:
				targetArray = helmets;
				break;
			case CHEST:
				targetArray = chestplates;
				break;
			case LEGS:
				targetArray = leggings;
				break;
			case FEET:
				targetArray = boots;
				break;
			default:
				throw new IllegalArgumentException("Not an armor slot" + slot);
		}

		int randomArmorIndex = random.nextInt(targetArray.length);

		return new ItemStack(targetArray[randomArmorIndex]);
	}

	public void setPlayerSkinByNickname(String playerName) {
		String normalizedName = playerName == null ? "" : playerName.trim();
		this.setPlayerName(normalizedName);

		if (normalizedName.isBlank()) {
			this.setPlayerProfile(ResolvableProfile.Static.EMPTY);
			return;
		}

		try {
			GameProfile profile = GameProfileService.getGameProfileByPlayerName(normalizedName);
			if (profile == null) {
				this.setPlayerProfile(ResolvableProfile.createUnresolved(normalizedName));
				return;
			}

			this.setPlayerName(profile.name());
			this.setPlayerProfile(ResolvableProfile.createResolved(profile));
		}
		catch (IOException | InterruptedException e) {
			if (e instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}

			this.setPlayerProfile(ResolvableProfile.createUnresolved(normalizedName));
			VoidDimension.LOGGER.error(
				"Can't set CorruptedPlayerEntity skin for player '{}'", normalizedName, e
			);
		}
	}

	protected void setRandomPlayerIdentity() {
		int randomPlayerNameIndex = random.nextInt(playerNames.length);
		String name = playerNames[randomPlayerNameIndex];
		
		this.setPlayerName(name);
		this.setCustomName(Component.translatable(name));

		this.setPlayerSkinByNickname(name);
	}
}
