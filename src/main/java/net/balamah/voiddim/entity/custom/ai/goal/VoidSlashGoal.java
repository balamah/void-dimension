package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.List;

import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.balamah.voiddim.interfaces.VoidSlashUser;
import net.balamah.voiddim.effect.ModDamageSources;

public class VoidSlashGoal<T extends BossEntity & VoidSlashUser> extends TickingGoal<T> {
	protected final double slashingTick;
	protected final double width;
	protected final double height;
	protected final double length;

	protected boolean hasInvokedVoidSlash;
	protected Vec3 entityPos;
	protected Vec3 slashDirection;
	protected AABB slashBox;
	
	public VoidSlashGoal(T entity, int slashingTick, double width, double height, double length) {
		super(entity);

		this.slashingTick = slashingTick;
		this.width = width;
		this.height = height;
		this.length = length;
	}

	@Override
	public boolean canUse() {
		return this.entity.attackCount >= 4 && this.entity.getTarget() != null;
	}

	@Override
	public void start() {
		super.start();

		this.entityPos = this.entity.position();

		LivingEntity target = this.entity.getTarget();
		Vec3 targetPos = target.position();

		this.slashDirection = targetPos.subtract(entityPos).normalize();
		Vec3 end = targetPos.add(this.slashDirection.scale(this.length));

		this.slashBox = new AABB(targetPos, end).inflate(this.width, this.height, this.width);
	}

	@Override
	public void stop() {
		super.stop();

		this.hasInvokedVoidSlash = false;
		this.entity.setVoidSlashTicks(this.entity.getVoidSlashCooldown());
	}

	@Override
	public boolean canContinueToUse() {
		return !this.hasInvokedVoidSlash && this.entity.getTarget() != null;
	}

	@Override
	public void tick() {
		super.tick();

		this.displayParticles();

		if (this.tick == this.slashingTick) {
			this.damageSlashBox();

			this.hasInvokedVoidSlash = true;
		}
	}

	protected void damageSlashBox() {
		List<LivingEntity> boxEntities = this.world.getEntitiesOfClass(
			LivingEntity.class, this.slashBox, target -> target != this.entity
		);

		for (LivingEntity boxEntity : boxEntities) {
			if (!(this.world instanceof ServerLevel serverWorld)) {
				return;
			}

			boxEntity.hurtServer(serverWorld, ModDamageSources.voidSlash(serverWorld), 15f);
		}
	}

	protected void displayParticles() {
		Vec3 upVector = new Vec3(0, 1, 0);
		Vec3 rightVector = new Vec3(this.slashDirection.z, 0, -this.slashDirection.x)
			.normalize();

		int lengthSteps = 30;
		int widthSteps = 6;
		int heightSteps = 3;

		for (int i = 0; i < lengthSteps; i++) {
			double t = i / (double) lengthSteps;
			Vec3 center = this.entityPos.add(this.slashDirection.scale(t * length));

			for (int w = -widthSteps; w <= widthSteps; w++) {
				for (int h = -heightSteps; h <= heightSteps; h++) {
					Vec3 offset = rightVector.scale(w * (width / widthSteps))
								   .add(upVector.scale(h * (height / heightSteps)));

					Vec3 pos = center.add(offset);

					this.world.addParticle(
						ModParticleTypes.VOID_SLASH_AREA,
						pos.x, pos.y, pos.z,
						0.0, 0.0, 0.0
					);
				}
			}
		}
	}
}
