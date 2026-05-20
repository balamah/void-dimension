package net.balamah.voiddim.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class CorruptedPlayerEntity extends Monster {
	protected static final EntityDataAccessor<String> PLAYER_NAME =
		SynchedEntityData.defineId(CorruptedPlayerEntity.class, EntityDataSerializers.STRING);
	protected static final EntityDataAccessor<ResolvableProfile> PLAYER_PROFILE =
		SynchedEntityData.defineId(CorruptedPlayerEntity.class, EntityDataSerializers.RESOLVABLE_PROFILE);

	protected static final String PLAYER_NAME_KEY = "PlayerName";
	protected static final String PLAYER_PROFILE_KEY = "PlayerProfile";

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
		this.setPlayerName(player.getGameProfile().name());
		this.setPlayerProfile(ResolvableProfile.createResolved(player.getGameProfile()));
		this.snapTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
		this.setYHeadRot(player.yHeadRot);
		this.setYBodyRot(player.yBodyRot);
		this.setCustomName(player.getDisplayName());
		this.setPersistenceRequired();
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
}
