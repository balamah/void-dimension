package net.balamah.voiddim.world.explosion;

import java.util.Optional;
import java.util.Arrays;

import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.balamah.voiddim.entity.custom.base.BossEntity;

public class ExplosionIgnoreEntitiesBehavior extends AdvancedExplosionBehavior {
	protected final EntityType<?>[] typesToIgnore;

	public ExplosionIgnoreEntitiesBehavior(EntityType<?>... typesToIgnore) {
		super(true, true, Optional.of(1.22F), Optional.empty());

		this.typesToIgnore = typesToIgnore;
	}

	@Override
	public boolean shouldDamage(Explosion explosion, Entity entity) {
		if (entity instanceof BossEntity) {
			return false;
		}

		return Arrays.stream(this.typesToIgnore).noneMatch(type -> entity.getType() == type);
	}
}
