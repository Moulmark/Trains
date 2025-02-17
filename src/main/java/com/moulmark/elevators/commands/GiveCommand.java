package com.moulmark.elevators.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.moulmark.elevators.ElevatorsManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand {
    public static LiteralCommandNode<CommandSourceStack> brigadier() {
        return Commands.literal("give")
            .requires(sender -> sender.getSender().hasPermission("elevators.give"))
            .then(Commands.argument("player", ArgumentTypes.player())
                .executes(ctx -> {
                    PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                    Player target = targetResolver.resolve(ctx.getSource()).getFirst();
                    Player sender = (Player) ctx.getSource().getSender();

                    if (target == null) {
                        sender.sendMessage(Component.text("Invalid player!", NamedTextColor.RED));
                        return Command.SINGLE_SUCCESS;
                    }

                    ItemStack elevatorStack = ElevatorsManager.createElevatorItem();
                    target.getInventory().addItem(elevatorStack);

                    sender.sendMessage(Component.text("Elevator given to specified player!", NamedTextColor.GREEN));
                    return Command.SINGLE_SUCCESS;
                })
            )
            .build();
    }
}
