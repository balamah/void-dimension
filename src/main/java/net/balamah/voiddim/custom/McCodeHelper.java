package net.balamah.voiddim.custom;

import net.balamah.voiddim.entity.custom.VoidSphereEntity;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.BossEvent;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.UUID;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.balamah.voiddim.VoidDimension;

import java.util.List;

public class McCodeHelper {
	public static final List<Holder<MobEffect>> effects = List.of(
		MobEffects.SLOWNESS,
		MobEffects.BLINDNESS,
		MobEffects.WEAKNESS
	);

	public static final List<Block> dangerousBlocks = List.of(
		Blocks.AIR, Blocks.LAVA, Blocks.COBWEB
	);

	public static Block[] irreplaceableBlocks = {
		Blocks.BEDROCK, Blocks.OBSIDIAN, Blocks.AIR
	};

	public static Direction getHorizontalFacing(Entity entity) {
		float yaw = entity.getYRot() % 360;
		if (yaw < 0) yaw += 360;

		if (yaw >= 135 && yaw < 225) return Direction.NORTH;
		if (yaw >= 45 && yaw < 135) return Direction.WEST;
		if (yaw >= 225 && yaw < 315) return Direction.EAST;

		return Direction.SOUTH;
	}

	public static ServerBossEvent getBossBar(Component text, BossBarColor color) {
		return new ServerBossEvent(UUID.randomUUID(), text, color, BossEvent.BossBarOverlay.PROGRESS);
	}

	public static boolean isBlockReplaceable(Block block) {
		for (Block element : irreplaceableBlocks) {
			if (block == element) return false;
		}

		return true;
	}

	public static BlockPos getBlockPosUnderEntity(Entity entity, int iteration) {
		int j = Mth.floor(entity.getX() + (iteration % 2 * 2 - 1) * 0.25F);
		int k = Mth.floor(entity.getY()) - 1;
		int l = Mth.floor(entity.getZ() + (iteration / 2 % 2 * 2 - 1) * 0.25F);

		return new BlockPos(j, k, l);
	}

	public static Block getBlock(Level world, BlockPos blockPos) {
		return world.getBlockState(blockPos).getBlock();
	}

	public static Block getBlock(Level world, int x, int y, int z) {
		BlockPos blockPos = new BlockPos(x, y, z);

		return world.getBlockState(blockPos).getBlock();
	}

	public static BlockPos getRandomBlockRightOf(Entity entity, int distance, int range) {
		Direction facing = entity.getDirection();

		Direction right = switch (facing) {
			case NORTH -> Direction.EAST;
			case EAST -> Direction.SOUTH;
			case SOUTH -> Direction.WEST;
			case WEST -> Direction.NORTH;
			default -> Direction.EAST;
		};

		BlockPos base = entity.blockPosition();

		RandomSource random = entity.level().getRandom();
		int dx = right.getStepX() * distance + random.nextIntBetweenInclusive(-range, range);
		int dz = right.getStepZ() * distance + random.nextIntBetweenInclusive(-range, range);

		return base.offset(dx, 0, dz);
	}

	public static boolean isTargetVisible(LivingEntity attacker, LivingEntity target) {
		Vec3 from = attacker.getEyePosition();
		Vec3 to = target.getEyePosition();

		ClipContext context = new ClipContext(
				from, to, ClipContext.Block.COLLIDER,
				ClipContext.Fluid.NONE, attacker);

		HitResult result = attacker.level().clip(context);

		return result.getType() == HitResult.Type.MISS;
	}

	public static void disableShield(Player target) {
		if (!target.isBlocking()) {
			return;
		}

		ItemStack activeItemStack = target.getUseItem();
		Item activeItem = activeItemStack.getItem();
		if (activeItem == Items.SHIELD) {
			target.getCooldowns().addCooldown(activeItemStack, 100);
			target.releaseUsingItem();
			target.stopUsingItem();

			playSoundFromEntity(target, SoundEvents.SHIELD_BREAK);
		}
	}

	public static void playSoundFromEntity(Entity entity, SoundEvent sound) {
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		Level world = entity.level();
		
		world.playSound(null, x, y, z, sound, SoundSource.AMBIENT, 2f, 1.0f);
	}

	public static void playSoundFromEntity(
		Entity entity, Holder.Reference<SoundEvent> sound
	) {
		playSoundFromEntity(entity, sound.value());
	}

	public static boolean shouldTeleportTo(Entity entity, LivingEntity target) {
		if (target == null) {
			return false;
		}

		Level world = entity.level();

		BlockPos blockPos = entity.blockPosition();
		int y = blockPos.getY();

		BlockPos underEntity = blockPos.below();
		Block blockUnderEntity = world.getBlockState(underEntity).getBlock();

		/**
		 * Blocks.AIR is included in {@link #dangerousBlocks} to prevent hit combos.
		 * Which makes fights with the entity better
		 */
		if (dangerousBlocks.contains(blockUnderEntity) || target.getY() > y ||
			target.distanceTo(entity) > 10
		) {
			return true;
		}

		for (int i = 1; i <= 10; i++) {
			int selectedY = y - i;

			BlockPos selectedPos = new BlockPos(blockPos.getX(), selectedY, blockPos.getZ());
			Block selectedBlock = McCodeHelper.getBlock(entity.level(), selectedPos);

			if (!dangerousBlocks.contains(selectedBlock)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isTeleportationSafe(
		Entity entity, double targetY, double x, double y, double z
	) {
		BlockPos futurePosition = new BlockPos(
				(int) x, (int) y, (int) z);

		Block futureBlock = entity.level().getBlockState(futurePosition).getBlock();

		if (dangerousBlocks.contains(futureBlock) || y < targetY) {
			return false;
		}

		return true;
	}

	public static Goal getTargetGoal(Mob entity, Class<?> entityTarget) {
		return new NearestAttackableTargetGoal(
			entity, entityTarget, 10, true, false,
			(target, world) -> Math.abs(target.getY() - target.getY()) <= 25.0
		);
	}

	/**
	 * @param potionType {Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION}
	 * @param potionId an id from an attribute in ModPotions
	 */
	public static ItemStack getPotionItemStack(Item potionType, String potionId) {
		ResourceKey<Potion> potionRegistryKey = ResourceKey.create(
			Registries.POTION, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, potionId)
		);

		return PotionContents.createItemStack(
			potionType, BuiltInRegistries.POTION.getOrThrow(potionRegistryKey)
		);
	}

	/**
	 * @param signLines is an String[] array of 4 elements max. Which looks like {"First", "second", "third", "fourth"}
	 */
	public static void setSignText(SignBlockEntity signBlockEntity, String[] signLines) {
		SignText signText = signBlockEntity.getFrontText();

		for (int i = 0; i < signLines.length; i++) {
			String signLineText = signLines[i];
			signText = signText.setMessage(i, Component.literal(signLineText));
		}

		signBlockEntity.setText(signText, true);
	}

	public static void createShockWave(ServerLevel world, LivingEntity entity, float radius) {
		world.explode(
			entity, null, VoidSphereEntity.EXPLOSION_BEHAVIOR,
			entity.getX(), entity.getY(), entity.getZ(),
			radius, false, Level.ExplosionInteraction.TRIGGER,
			ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE,
			WeightedList.of(), ModSounds.SHOCKWAVE
		);

		List<LivingEntity> entities = entity.level()
			.getEntitiesOfClass(
				LivingEntity.class,
				entity.getBoundingBox().inflate(radius),
				e -> !e.isSpectator()
			);

		for (LivingEntity target : entities) {
			if (target instanceof BossEntity || !McCodeHelper.isTargetVisible(entity, target)) {
				continue;
			}

			for (Holder<MobEffect> effect : effects) {
				target.addEffect(new MobEffectInstance(effect, 1200, 2));
				target.hurtServer(world, ModDamageSources.shockWave(world), 15f);

				breakShield(target);
			}
		}
	}

	public static void breakShield(LivingEntity target) {
		if (!(target instanceof Player player)) {
			return;
		}

		ItemStack stack = player.getUseItem();

		if (stack.is(Items.SHIELD)) {
			EquipmentSlot slot = (player.getUsedItemHand() == InteractionHand.MAIN_HAND)
					? EquipmentSlot.MAINHAND
					: EquipmentSlot.OFFHAND;

			stack.hurtAndBreak(150, player, slot);

			McCodeHelper.playSoundFromEntity(target, SoundEvents.SHIELD_BREAK);
		}
	}

	public static void playSound(
		Level world, SoundEvent sound, Vec3 position, SoundSource category,
		float volume, float pitch
	) {
		playSound(world, sound, position.x, position.y, position.z, category, volume, pitch);
	}

	public static void playSound(
		Level world, SoundEvent sound, double x, double y, double z, SoundSource category,
		float volume, float pitch
	) {
		world.playSound(null, x, y, z, sound, category, volume, pitch);
	}

	public static void sendMessageToNearbyPlayers(
		ServerLevel world, Vec3 center, double radius, String message
	) {
        AABB box = new AABB(
            center.x - radius, center.y - radius, center.z - radius,
            center.x + radius, center.y + radius, center.z + radius
        );

	        for (ServerPlayer player : world.players()) {
	            if (box.contains(player.position())) {
	                player.sendSystemMessage(Component.literal(message));
	            }
	        }
	    }
}
