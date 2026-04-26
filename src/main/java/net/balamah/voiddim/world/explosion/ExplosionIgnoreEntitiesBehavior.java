package net.balamah.voiddim.world.explosion;

import java.util.Optional;
import java.util.Arrays;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;

public class ExplosionIgnoreEntitiesBehavior extends SimpleExplosionDamageCalculator {
	protected final EntityType<?>[] typesToIgnore;

	public ExplosionIgnoreEntitiesBehavior(EntityType<?>... typesToIgnore) {
		super(true, true, Optional.of(1.22F), Optional.empty());

		this.typesToIgnore = typesToIgnore;
	}

	@Override
	public boolean shouldDamageEntity(Explosion explosion, Entity entity) {
		if (entity instanceof BossEntity) {
			return false;
		}

		return Arrays.stream(this.typesToIgnore).noneMatch(type -> entity.getType() == type);
	}
}
