package com.anotherpillow.thymeandtimeagain.datagen;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.block.ModBlocks;
import com.anotherpillow.thymeandtimeagain.custom.ThymeCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThymeAndTimeAgain.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeThymeCrop((CropBlock) ModBlocks.THYME_CROP.get(), "thyme_stage", "thyme_stage");
    }


    public void makeThymeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> thymeStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] thymeStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((ThymeCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(ThymeAndTimeAgain.MODID, "block/" + textureName + state.getValue(((ThymeCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

//    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
//        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
//    }
}