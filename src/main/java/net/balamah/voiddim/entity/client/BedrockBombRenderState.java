package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.TntEntityRenderState;
import net.minecraft.block.BlockState;

import net.balamah.voiddim.block.ModBlocks;

public class BedrockBombRenderState extends TntEntityRenderState {
	public BlockState blockState = ModBlocks.BEDROCK_BOMB.getDefaultState();
}
