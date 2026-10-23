package net.balamah.voiddim.entity.custom.base;

import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.tags.BlockTags;

import java.util.Optional;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

public abstract class ModProjectile extends AbstractHurtingProjectile {
	protected EntityType<?>[] immuneEntities = {};

	protected Holder<SoundEvent> hitSound;

	public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR =
		new SimpleExplosionDamageCalculator(
			false, false, Optional.empty(),
			BuiltInRegistries.BLOCK.get(BlockTags.AIR).map(Function.identity())
		);

	public ModProjectile(EntityType<? extends ModProjectile> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected abstract void onHitEntity(EntityHitResult hitResult);

	@Override
	protected void onHitBlock(BlockHitResult hitResult) {
		super.onHitBlock(hitResult);

		if (!this.level().isClientSide()) {
			this.disappear();
		}
	}
	
	protected void disappear() {
		this.explode(this.position());

		this.discard();
	}

	protected abstract void explode(Vec3 pos);

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	protected @Nullable ParticleOptions getTrailParticle() {
		return ParticleTypes.SOUL;
	}
}
