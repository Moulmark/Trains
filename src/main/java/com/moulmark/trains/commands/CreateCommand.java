package com.moulmark.trains.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;

public class CreateCommand {
    public static LiteralCommandNode<CommandSourceStack> brigadier() {
        return Commands.literal("create")
            .requires(sender -> sender.getSender().hasPermission("trains.create"))
            .then(Commands.argument("type", StringArgumentType.word())
                .suggests((ctx, builder) -> {
                    builder.suggest("line");
                    builder.suggest("station");
                    builder.suggest("train");
                    return builder.buildFuture();
                })
                .executes((ctx) -> {
                    final String type = StringArgumentType.getString(ctx, "type");
                    final Player player = (Player) ctx.getSource().getSender();

                    switch (type.toLowerCase()) {
                        case "line" -> {}
                        case "station" -> {}
                        case "train" -> {}
                        default -> player.sendMessage("Invalid type argument!");
                    }

                    return Command.SINGLE_SUCCESS;
                })
            )
            .build();
    }
}
