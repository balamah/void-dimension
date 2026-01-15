package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.balamah.voiddim.entity.custom.ThrownBlockEntity;
import net.balamah.voiddim.custom.McCodeHelper;

public class ThrowBlockGoal extends Goal {
	protected final ShatteredSentinelMasterEntity entity;

	protected final EntityAttributeInstance entityAttributeInstance;
	protected final Identifier attributeId = Identifier.ofVanilla("speed");
	protected final EntityAttributeModifier attributeModifier =
		new EntityAttributeModifier(
			this.attributeId, -1.2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);

	protected int cooldown;
	protected boolean blockThrown = false;

	public ThrowBlockGoal(
		ShatteredSentinelMasterEntity entity
	) {
		this.entity = entity;
		this.entityAttributeInstance =
			this.entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) >= 4;
	}

	@Override
	public void start() {
		this.cooldown = 0;
	}

	@Override
	public void tick() {
		World world = this.entity.getEntityWorld();
		LivingEntity target = this.entity.getTarget();
		if (!(world instanceof ServerWorld serverWorld)) return;

		this.cooldown++;

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

		// this.entity.setThrowBlockTicks(500);
		this.entityAttributeInstance.removeModifier(this.attributeModifier);
	}

	protected void throwBlock(ServerWorld serverWorld, BlockPos blockPos, LivingEntity target) {

		this.blockThrown = true;
	}
}
