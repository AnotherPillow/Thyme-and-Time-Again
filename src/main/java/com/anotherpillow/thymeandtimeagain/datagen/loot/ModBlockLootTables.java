package com.anotherpillow.thymeandtimeagain.datagen.loot;

import com.anotherpillow.thymeandtimeagain.custom.ThymeCropBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import com.anotherpillow.thymeandtimeagain.block.ModBlocks;
import com.anotherpillow.thymeandtimeagain.item.ModItems;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.THYME_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ThymeCropBlock.AGE, 5));

        this.add(ModBlocks.THYME_CROP.get(), createCropDrops(ModBlocks.THYME_CROP.get(), ModItems.THYME.get(),
                ModItems.THYME_SEEDS.get(), lootitemcondition$builder));

    }
}