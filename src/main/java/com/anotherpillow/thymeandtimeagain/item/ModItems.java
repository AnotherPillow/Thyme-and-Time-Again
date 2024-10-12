package com.anotherpillow.thymeandtimeagain.item;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.block.ModBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ThymeAndTimeAgain.MODID);

    public static final DeferredItem<Item> THYME = ITEMS.register("thyme", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .alwaysEdible().nutrition(1).saturationModifier(2f).build())));

    public static final DeferredItem<Item> THYME_IN_A_BOTTLE = ITEMS.register("thyme_in_a_bottle", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().effect(
                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 5), 1
            ).build()).stacksTo(16)));

    public static final DeferredItem<Item> THYME_SEEDS = ITEMS.register("thyme_seeds",
            () -> new ItemNameBlockItem(ModBlocks.THYME_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> TIME_DUST = ITEMS.register("time_dust",
            () -> new Item(new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> TIME_CRYSTAL = ITEMS.register("time_crystal",
            () -> new Item(new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.RARE)));

    public static final DeferredItem<BlockItem> TIME_CRYSTAL_BLOCK_ITEM = ModItems.ITEMS.registerSimpleBlockItem("time_crystal_block",
            ModBlocks.TIME_CRYSTAL_BLOCK);


    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
