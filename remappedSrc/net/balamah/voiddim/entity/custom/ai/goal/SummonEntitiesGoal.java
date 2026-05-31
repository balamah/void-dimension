package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntitySpawnReason;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;

public class SummonEntitiesGoal<E extends CorruptedHostileEntity, T extends Entity>
	extends TickingGoal<E>
{
	protected final Class<T> entityClass;
	protected final EntityType<T> entityType;
	protected final int minTargetDistance;

	public SummonEntitiesGoal(
		E entity, Class<T> entityClass, EntityType<T> entityType, int minTargetDistance
	) {
		super(entity);

		this.entityClass = entityClass;
		this.entityType = entityType;
		this.minTargetDistance = minTargetDistance;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		Vec3d position = new Vec3d(this.entity.getX(), this.entity.getY(), this.entity.getZ());;

		return target != null &&
			   target.distanceTo(this.entity) > this.minTargetDistance &&
			   !this.areMobsSpawned(this.entity.getWorld(), position, 15);
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == 15) {
			this.sendEntityStatus(ModEntityStatuses.SUMMON_ENTITIES_START);
		}

		if (this.tick == 20) {
			this.spawnEntities(this.world);
		}
	}

	@Override
	public void stop() {
		super.stop();

		this.sendEntityStatus(ModEntityStatuses.SUMMON_ENTITIES_FINISH);
	}

	protected double getSideCoordinate() {
		switch (McCodeHelper.getHorizontalFacing(this.entity)) {
			case NORTH:
			case SOUTH:
				return this.entity.getX();
			case WEST:
			case EAST:
				return this.entity.getZ();
			default:
				return this.entity.getX();
		}
	}

	protected void spawnEntities(World defaultWorld) {
		if (!(defaultWorld instanceof ServerWorld world)) {
			return;
		}

		Map<T, Double> entityArray = new HashMap<T, Double>();

		double x = this.entity.getX();
		double y = this.entity.getY();
		double z = this.entity.getZ();

		double sideCoordinate = this.getSideCoordinate();

		double firstEntityPos = sideCoordinate - 1;
		double secondEntityPos = sideCoordinate + 1;

		T firstEntity = entityType.create(
			world, null, new BlockPos((int) x, (int) y, (int) z),
			EntitySpawnReason.MOB_SUMMONED, true, false
		);

		T secondEntity = firstEntity;

 		entityArray.put(firstEntity, firstEntityPos);
 		entityArray.put(secondEntity, secondEntityPos);
 
 		for (Map.Entry<T, Double> entry : entityArray.entrySet()) {
 			T entityForSpawn = entry.getKey();
 			Double stalkerPos = entry.getValue();
 
 			if (sideCoordinate == x) {
 				entityForSpawn.setPosition(stalkerPos, y, z);
 			} else if (sideCoordinate == z) {
 				entityForSpawn.setPosition(x, y, stalkerPos);
 			}
 
 			world.spawnEntity(entityForSpawn);
 		}

		this.tick = -100;
	}

    protected boolean areMobsSpawned(World world, Vec3d position, double radius) {
        Box box = new Box(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

		List<T> mobs = world.getEntitiesByClass(
			this.entityClass, box, entity -> entity.isAlive()
		);

        return !mobs.isEmpty();
    }
}
