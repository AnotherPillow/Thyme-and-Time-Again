package com.anotherpillow.thymeandtimeagain.datagen;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.block.ModBlocks;
import com.anotherpillow.thymeandtimeagain.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThymeAndTimeAgain.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        item(ModItems.THYME.get());
        item(ModItems.THYME_SEEDS.get());
    }

    // https://canary.discord.com/channels/313125603924639766/1249305774987939900/1254902935557767178 (neoforge discord)
    private void item(Item item){
        String name = getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + name);
    }
    // https://canary.discord.com/channels/313125603924639766/1249305774987939900/1254902935557767178 (neoforge discord)
    private @NotNull String getItemName(Item item){
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(ThymeAndTimeAgain.MODID + ":", "");
    }
}