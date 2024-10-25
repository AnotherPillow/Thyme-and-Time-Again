package com.anotherpillow.thymeandtimeagain.command;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.awt.*;

public class DisplayItemUsageCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context){
        dispatcher.register(Commands.literal("tta-display-item-usage")
                .then(Commands.argument("item", ItemArgument.item(context))
                .executes(DisplayItemUsageCommand::execute)));
    }
    private static int execute(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player){
            ItemStack stack = new ItemStack(command.getArgument("item", ItemInput.class).getItem());
            ThymeAndTimeAgain.mc.gameRenderer.displayItemActivation(stack);
        }
        return Command.SINGLE_SUCCESS;
    }
}
