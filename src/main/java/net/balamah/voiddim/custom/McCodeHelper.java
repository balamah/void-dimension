package net.balamah.voiddim.custom;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.BossBar.Color;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import java.util.List;

public class McCodeHelper {
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
		if (dangerousBlocks.contains(elevatedBlock) || target.getY() > y) {
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
			(int) x, (int) y, (int) z
		);

		Block futureBlock = entity.getEntityWorld().getBlockState(futurePosition).getBlock();

		if (dangerousBlocks.contains(futureBlock) || y < targetY) {
			return false;
		}

		return true;
	}
}
