package net.balamah.voiddim.custom;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.BossBar.Color;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.block.entity.SignText;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.RegistryKey;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.collection.Pool;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Direction;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import net.balamah.voiddim.entity.custom.VoidSphereEntity;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.sound.ModSounds;
import net.balamah.voiddim.VoidDimension;

import java.util.List;

public class McCodeHelper {
	public static final List<RegistryEntry<StatusEffect>> effects = List.of(
		StatusEffects.SLOWNESS,
		StatusEffects.BLINDNESS,
		StatusEffects.WEAKNESS
	);

	public static final List<Block> dangerousBlocks = List.of(
		Blocks.AIR, Blocks.LAVA, Blocks.COBWEB
	);

	public static Block[] irreplaceableBlocks = {
		Blocks.BEDROCK, Blocks.OBSIDIAN, Blocks.AIR
	};

	public static Direction getHorizontalFacing(Entity entity) {
		float yaw = entity.getYaw() % 360;
		if (yaw < 0) yaw += 360;

		if (yaw >= 135 && yaw < 225) return Direction.NORTH;
		if (yaw >= 45 && yaw < 135) return Direction.WEST;
		if (yaw >= 225 && yaw < 315) return Direction.EAST;

		return Direction.SOUTH;
	}

	public static ServerBossBar getBossBar(Text text, Color color) {
		return new ServerBossBar(text, color, BossBar.Style.PROGRESS);
	}

	public static boolean isBlockReplaceable(Block block) {
		for (Block element : irreplaceableBlocks) {
			if (block == element) return false;
		}

		return true;
	}

	public static BlockPos getBlockPosUnderEntity(Entity entity, int iteration) {
		int j = MathHelper.floor(entity.getX() + (iteration % 2 * 2 - 1) * 0.25F);
		int k = MathHelper.floor(entity.getY()) - 1;
		int l = MathHelper.floor(entity.getZ() + (iteration / 2 % 2 * 2 - 1) * 0.25F);

		return new BlockPos(j, k, l);
	}

	public static Block getBlock(World world, BlockPos blockPos) {
		return world.getBlockState(blockPos).getBlock();
	}

	public static Block getBlock(World world, int x, int y, int z) {
		BlockPos blockPos = new BlockPos(x, y, z);

		return world.getBlockState(blockPos).getBlock();
	}

	public static BlockPos getRandomBlockRightOf(Entity entity, int distance, int range) {
		Direction facing = entity.getHorizontalFacing();

		Direction right = switch (facing) {
			case NORTH -> Direction.EAST;
			case EAST -> Direction.SOUTH;
			case SOUTH -> Direction.WEST;
			case WEST -> Direction.NORTH;
			default -> Direction.EAST;
		};

		BlockPos base = entity.getBlockPos();

		Random random = entity.getEntityWorld().getRandom();
		int dx = right.getOffsetX() * distance + random.nextBetween(-range, range);
		int dz = right.getOffsetZ() * distance + random.nextBetween(-range, range);

		return base.add(dx, 0, dz);
	}

	public static boolean isTargetVisible(LivingEntity attacker, LivingEntity target) {
		Vec3d from = attacker.getEyePos();
		Vec3d to = target.getEyePos();

		RaycastContext context = new RaycastContext(
				from, to, RaycastContext.ShapeType.COLLIDER,
				RaycastContext.FluidHandling.NONE, attacker);

		HitResult result = attacker.getEntityWorld().raycast(context);

		return result.getType() == HitResult.Type.MISS;
	}

	public static void disableShield(PlayerEntity target) {
		if (!target.isBlocking()) {
			return;
		}

		ItemStack activeItemStack = target.getActiveItem();
		Item activeItem = activeItemStack.getItem();
		if (activeItem == Items.SHIELD) {
			target.getItemCooldownManager().set(activeItemStack, 100);
			target.stopUsingItem();
			target.clearActiveItem();

			playSoundFromEntity(target, SoundEvents.ITEM_SHIELD_BREAK);
		}
	}

	public static void playSoundFromEntity(Entity entity, SoundEvent sound) {
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		World world = entity.getEntityWorld();
		
		world.playSound(null, x, y, z, sound, SoundCategory.AMBIENT, 2f, 1.0f);
	}

	public static void playSoundFromEntity(
		Entity entity, RegistryEntry.Reference<SoundEvent> sound
	) {
		playSoundFromEntity(entity, sound.value());
	}

	public static boolean shouldTeleportTo(Entity entity, LivingEntity target) {
		if (target == null) {
			return false;
		}

		World world = entity.getEntityWorld();

		int x = (int) entity.getX();
		int y = (int) entity.getY();
		int z = (int) entity.getZ();

		BlockPos elevatedPosition = new BlockPos(x, y + 1, z);
		Block elevatedBlock = world.getBlockState(elevatedPosition).getBlock();

		/**
		 * Blocks.AIR is included in {@link #dangerousBlocks} to prevent hit combos.
		 * Which makes fights with the entity better
		 */
		if (dangerousBlocks.contains(elevatedBlock) || target.getY() > y ||
			target.distanceTo(entity) > 10
		) {
			return true;
		}

		for (int i = 1; i <= 10; i++) {
			int selectedY = y - i;

			BlockPos blockPos = new BlockPos(x, selectedY, z);
			Block selectedBlock = McCodeHelper.getBlock(entity.getEntityWorld(), blockPos);

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

		Block futureBlock = entity.getEntityWorld().getBlockState(futurePosition).getBlock();

		if (dangerousBlocks.contains(futureBlock) || y < targetY) {
			return false;
		}

		return true;
	}

	public static Goal getTargetGoal(MobEntity entity, Class<?> entityTarget) {
		return new ActiveTargetGoal(
			entity, entityTarget, 10, true, false,
			(target, world) -> Math.abs(target.getY() - target.getY()) <= 25.0
		);
	}

	/**
	 * @param potionType {Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION}
	 * @param potionId an id from an attribute in ModPotions
	 */
	public static ItemStack getPotionItemStack(Item potionType, String potionId) {
		RegistryKey<Potion> potionRegistryKey = RegistryKey.of(
			RegistryKeys.POTION, Identifier.of(VoidDimension.MOD_ID, potionId)
		);

		return PotionContentsComponent.createStack(
			potionType, Registries.POTION.getOrThrow(potionRegistryKey)
		);
	}

	/**
	 * @param signLines is an String[] array of 4 elements max. Which looks like {"First", "second", "third", "fourth"}
	 */
	public static void setSignText(SignBlockEntity signBlockEntity, String[] signLines) {
		SignText signText = signBlockEntity.getFrontText();

		for (int i = 0; i < signLines.length; i++) {
			String signLineText = signLines[i];
			signText = signText.withMessage(i, Text.literal(signLineText));
		}

		signBlockEntity.setText(signText, true);
	}

	public static void createShockWave(ServerWorld world, LivingEntity entity, float radius) {
		world.createExplosion(
			entity, null, VoidSphereEntity.EXPLOSION_BEHAVIOR,
			entity.getX(), entity.getY(), entity.getZ(),
			radius, false, World.ExplosionSourceType.TRIGGER,
			ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE,
			Pool.empty(), ModSounds.SHOCKWAVE
		);

		List<LivingEntity> entities = entity.getEntityWorld()
			.getEntitiesByClass(
				LivingEntity.class,
				entity.getBoundingBox().expand(radius),
				e -> !e.isSpectator()
			);

		for (LivingEntity target : entities) {
			if (target instanceof BossEntity || !McCodeHelper.isTargetVisible(entity, target)) {
				continue;
			}

			for (RegistryEntry<StatusEffect> effect : effects) {
				target.addStatusEffect(new StatusEffectInstance(effect, 1200, 2));
				target.damage(world, ModDamageSources.shockWave(world), 15f);

				breakShield(target);
			}
		}
	}

	public static void breakShield(LivingEntity target) {
		if (!(target instanceof PlayerEntity player)) {
			return;
		}

		ItemStack stack = player.getActiveItem();

		if (stack.isOf(Items.SHIELD)) {
			EquipmentSlot slot = (player.getActiveHand() == Hand.MAIN_HAND)
				? EquipmentSlot.MAINHAND
				: EquipmentSlot.OFFHAND;

			stack.damage(150, player, slot);

			McCodeHelper.playSoundFromEntity(target, SoundEvents.ITEM_SHIELD_BREAK);
		}
	}
}
