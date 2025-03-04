package com.moulmark.trains;

import com.moulmark.trains.commands.CreateCommand;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Trains extends JavaPlugin {
    @Getter
    private static Trains instance;

    @Override
    public void onEnable() {
        instance = this;

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(
                Commands.literal("trains")
                    .then(CreateCommand.brigadier())
                    .build()
            );
        });

        getLogger().info("Trains has been enabled!");
    }
}
