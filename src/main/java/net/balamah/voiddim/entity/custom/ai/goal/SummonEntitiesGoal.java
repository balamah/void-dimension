package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class SummonEntitiesGoal<T extends Entity> extends Goal {
	protected final HostileEntity entity;
	protected final Class<T> entityClass;
	protected final EntityType<T> entityType;

	public int cooldown;

	public SummonEntitiesGoal(HostileEntity entity, Class<T> entityClass,
							  EntityType<T> entityType)
	{
		this.entity = entity;
		this.entityClass = entityClass;
		this.entityType = entityType;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		Vec3d position = new Vec3d(this.entity.getX(), this.entity.getY(), this.entity.getZ());;

		return target != null &&
			   target.distanceTo(this.entity) > 11.0 &&
			   !this.areMobsSpawned(this.entity.getEntityWorld(), position, 15);
	}

	@Override
	public void start() {
		this.cooldown = 0;
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		World world = this.entity.getEntityWorld();

		this.cooldown++;

		if (this.cooldown == 15) {
			world.sendEntityStatus(
				this.entity, ModEntityStatuses.SUMMON_ENTITIES_START
			);
		}

		if (this.cooldown == 20) {
			this.spawnEntities(world);
		}
	}

	@Override
	public void stop() {
		super.stop();

		this.entity.getEntityWorld().sendEntityStatus(
			this.entity, ModEntityStatuses.SUMMON_ENTITIES_FINISH
		);
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

		T firstEntity = entityType.create(world, null,
											   new BlockPos((int) x, (int) y, (int) z),
											   SpawnReason.MOB_SUMMONED, true, false);

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

		this.cooldown = -100;
	}

    protected boolean areMobsSpawned(World world, Vec3d position, double radius) {
        Box box = new Box(
			position.x - radius, position.y - radius, position.z - radius,
			position.x + radius, position.y + radius, position.z + radius
        );

        List<T> mobs = world.getEntitiesByClass(this.entityClass,
												box,
												entity -> entity.isAlive());

        return !mobs.isEmpty();
    }
}
