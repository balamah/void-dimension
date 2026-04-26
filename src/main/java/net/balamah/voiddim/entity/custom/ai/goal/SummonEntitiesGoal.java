package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.custom.McCodeHelper;

public class SummonEntitiesGoal<E extends CorruptedHostileEntity, T extends Entity>
	extends TickingGoal<E>
{
	protected final Class<T> entityClass;
	protected final EntityType<T> entityType;
	protected final int maxTargetDistance;

	public SummonEntitiesGoal(
		E entity, Class<T> entityClass, EntityType<T> entityType, int maxTargetDistance
	) {
		super(entity);

		this.entityClass = entityClass;
		this.entityType = entityType;
		this.maxTargetDistance = maxTargetDistance;
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		Vec3 position = new Vec3(this.entity.getX(), this.entity.getY(), this.entity.getZ());;

		return target != null &&
			   target.distanceTo(this.entity) > this.maxTargetDistance &&
			   !this.areMobsSpawned(this.entity.level(), position, 15);
	}

	@Override
	public boolean requiresUpdateEveryTick() {
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

	protected void spawnEntities(Level defaultWorld) {
		if (!(defaultWorld instanceof ServerLevel world)) {
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
 				entityForSpawn.setPos(stalkerPos, y, z);
 			} else if (sideCoordinate == z) {
 				entityForSpawn.setPos(x, y, stalkerPos);
 			}
 
 			world.addFreshEntity(entityForSpawn);
 		}

		this.tick = -100;
	}

    protected boolean areMobsSpawned(Level world, Vec3 position, double radius) {
        AABB box = new AABB(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

		List<T> mobs = world.getEntitiesOfClass(
			this.entityClass, box, entity -> entity.isAlive()
		);

        return !mobs.isEmpty();
    }
}
