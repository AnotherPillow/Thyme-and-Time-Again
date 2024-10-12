package com.anotherpillow.thymeandtimeagain.block;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.custom.ThymeCropBlock;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ThymeAndTimeAgain.MODID);

    public static final DeferredBlock<Block> THYME_CROP = BLOCKS.register("thyme_crop",
            () -> new ThymeCropBlock(Blocks.WHEAT.properties().noCollission().noOcclusion()));

    public static final DeferredBlock<Block> TIME_CRYSTAL_BLOCK = BLOCKS.register("time_crystal_block",
            () -> new Block(Blocks.EMERALD_BLOCK.properties()));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}