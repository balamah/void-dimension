package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class CorruptedPlayerEntity extends CorruptedHostileEntity {
	protected static final TrackedData<String> PLAYER_NAME =
		DataTracker.registerData(CorruptedPlayerEntity.class, TrackedDataHandlerRegistry.STRING);

	protected static final TrackedData<ProfileComponent> PLAYER_PROFILE =
		DataTracker.registerData(
			CorruptedPlayerEntity.class, EntityDataSerializers.RESOLVABLE_PROFILE
		);

	protected static final String PLAYER_NAME_KEY = "PlayerName";
	protected static final String PLAYER_PROFILE_KEY = "PlayerProfile";

	protected ServerPlayerEntity player;

	protected EquipmentSlot[] playerEquipmentSlots = {
		EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND,
		EquipmentSlot.HEAD, EquipmentSlot.CHEST,
		EquipmentSlot.LEGS, EquipmentSlot.FEET,
	};

	public CorruptedPlayerEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0F)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0F);
	}

	public void copyDeadPlayer(ServerPlayerEntity player) {
		this.setPlayerName(player.getGameProfile().name());
		this.setPlayerProfile(ProfileComponent.createResolved(player.getGameProfile()));
		this.snapTo(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
		this.setHeadYaw(player.headYaw);
		this.setBodyYaw(player.bodyYaw);
		this.setCustomName(player.getDisplayName());
		this.setPersistent();

		this.player = player;

		this.copyEquipment(this.player);
	}

	public String getPlayerName() {
		return this.dataTracker.get(PLAYER_NAME);
	}

	public void setPlayerName(String playerName) {
		this.dataTracker.set(PLAYER_NAME, playerName);
	}

	public ProfileComponent getPlayerProfile() {
		return this.dataTracker.get(PLAYER_PROFILE);
	}

	public void setPlayerProfile(ProfileComponent profile) {
		this.dataTracker.set(PLAYER_PROFILE, profile);
	}

	@Override
	@Nullable
	public EntityData finalizeSpawn(
		ServerWorldAccess world,
		LocalDifficulty difficulty,
		EntitySpawnReason spawnReason,
		@Nullable EntityData entityData
	) {
		entityData = super.initialize(world, difficulty, spawnReason, entityData);

		this.initEquipment(this.random, difficulty);
		this.updateEnchantments(world, this.random, difficulty);
		
		return entityData;
	}

	@Override
	protected void initDataTracker(DataTracker.Builder entityData) {
		super.initDataTracker(entityData);
		entityData.add(PLAYER_NAME, "");
		entityData.add(PLAYER_PROFILE, ResolvableProfile.Static.EMPTY);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.writeCustomDataToNbt(output);
		output.putString(PLAYER_NAME_KEY, this.getPlayerName());
		output.store(PLAYER_PROFILE_KEY, ProfileComponent.CODEC, this.getPlayerProfile());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readCustomDataFromNbt(input);
		String playerName = input.getStringOr(PLAYER_NAME_KEY, "");
		this.setPlayerName(playerName);
		this.setPlayerProfile(
			input.read(PLAYER_PROFILE_KEY, ProfileComponent.CODEC)
				.orElseGet(() -> playerName.isBlank()
					? ResolvableProfile.Static.EMPTY
					: ResolvableProfile.createUnresolved(playerName))
		);
	}

	protected void copyEquipment(ServerPlayerEntity player) {
		for (EquipmentSlot slot : this.playerEquipmentSlots) {
			ItemStack stack = player.getEquippedStack(slot);

			if (!stack.isEmpty()) {
				this.equipStack(slot, stack);
				this.setEquipmentDropChance(slot, 0f);
			}
		}
	}
}
