package com.anotherpillow.thymeandtimeagain.item;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.block.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
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

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
