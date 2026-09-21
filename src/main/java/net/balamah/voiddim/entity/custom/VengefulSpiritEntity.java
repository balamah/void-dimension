package net.balamah.voiddim.entity.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import java.util.Optional;
import java.util.function.Function;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.custom.base.ModProjectile;

public class VengefulSpiritEntity extends ModProjectile {
	public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR =
		new SimpleExplosionDamageCalculator(
			false, false, Optional.empty(),
			BuiltInRegistries.BLOCK.get(BlockTags.AIR).map(Function.identity())
		);

	public VengefulSpiritEntity(
		EntityType<? extends VengefulSpiritEntity> type, Level level
	) {
		super(type, level);
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		if (this.level() instanceof ServerLevel world) {
			Entity target = hitResult.getEntity();

			DamageSource damageSource = ModDamageSources.vengefulSpirit(world);
			target.hurtServer(world, damageSource, 18.5f);

			this.disappear();
		}
	}
}
