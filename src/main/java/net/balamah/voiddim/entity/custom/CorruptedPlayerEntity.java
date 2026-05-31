package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.properties.PropertyMap;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Optional;

public class CorruptedPlayerEntity extends CorruptedHostileEntity {
	protected static final EntityDataAccessor<String> PLAYER_NAME =
		SynchedEntityData.defineId(CorruptedPlayerEntity.class, EntityDataSerializers.STRING);

	protected static final String PLAYER_NAME_KEY = "PlayerName";

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
			.add(Attributes.MOVEMENT_SPEED, 0.25F)
			.add(Attributes.ATTACK_DAMAGE, 3.0F)
			.add(Attributes.MAX_HEALTH, 20.0F);
	}

	public void copyDeadPlayer(ServerPlayer player) {
		this.setPlayerName(player.getGameProfile().getName());
		this.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
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
		String playerName = this.getPlayerName();
		return new ResolvableProfile(
			playerName.isBlank() ? Optional.empty() : Optional.of(playerName),
			Optional.empty(),
			new PropertyMap()
		);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(
		ServerLevelAccessor world,
		DifficultyInstance difficulty,
		MobSpawnType spawnReason,
		@Nullable SpawnGroupData entityData
	) {
		entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);

		this.populateDefaultEquipmentSlots(this.random, difficulty);
		this.populateDefaultEquipmentEnchantments(world, this.random, difficulty);
		
		return entityData;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(PLAYER_NAME, "");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag output) {
		super.addAdditionalSaveData(output);
		output.putString(PLAYER_NAME_KEY, this.getPlayerName());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag input) {
		super.readAdditionalSaveData(input);
		String playerName = input.getString(PLAYER_NAME_KEY);
		this.setPlayerName(playerName);
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
}
