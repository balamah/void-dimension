package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ThrowBlockUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.balamah.voiddim.custom.McCodeHelper;

public class ThrowBlockGoal<T extends CorruptedHostileEntity & ThrowBlockUser>
	extends SlowMovementGoal<T>
{
	protected int cooldown;
	protected boolean blockThrown = false;

	public ThrowBlockGoal(T entity) {
		super(entity);
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) >= 4;
	}

	@Override
	public void start() {
		this.addSpeedModifier();
	}

	@Override
	public void tick() {
		super.tick();

		World world = this.entity.getWorld();
		LivingEntity target = this.entity.getTarget();
		if (!(world instanceof ServerWorld serverWorld)) return;

		if (this.cooldown == 1) {
			this.entity.setStopAttacks(true);

			if (!this.entityAttributeInstance.hasModifier(this.attributeId)) {
				this.entityAttributeInstance.addTemporaryModifier(this.attributeModifier);
			}

			BlockPos blockPos = McCodeHelper.getRandomBlockRightOf(entity, 4, 0);

			this.throwBlock(serverWorld, blockPos, target);
		}

		if (this.blockThrown) {
			this.stop();
		}
	}

	@Override
	public void stop() {
		super.stop();

		this.removeSpeedModifier();
	}

	protected void throwBlock(ServerWorld serverWorld, BlockPos blockPos, LivingEntity target) {

		this.blockThrown = true;
	}
}
