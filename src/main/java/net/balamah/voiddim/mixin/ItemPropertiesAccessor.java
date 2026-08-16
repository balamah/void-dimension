package net.balamah.voiddim.mixin;

import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Properties.class)
public interface ItemPropertiesAccessor {
	@Accessor("componentInitializer")
	DataComponentInitializers.Initializer<Item> voidDimension$getComponentInitializer();

	@Accessor("componentInitializer")
	void voidDimension$setComponentInitializer(
		DataComponentInitializers.Initializer<Item> componentInitializer
	);
}
