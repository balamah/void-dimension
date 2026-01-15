package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.entity.AnimationState;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.AbstractCorruptedHostileEntity;

public class VoidBoundServantEntity extends AbstractCorruptedHostileEntity {
	public final AnimationState suicideAnimationState = new AnimationState();

	public VoidBoundServantEntity(
		EntityType<? extends HostileEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.FOLLOW_RANGE, 32)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.2F)
				.add(EntityAttributes.ATTACK_DAMAGE, 14.0F)
				.add(EntityAttributes.STEP_HEIGHT, 1.0)
				.add(EntityAttributes.MAX_HEALTH, 35);
	}

	@Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
		this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
	}
}
