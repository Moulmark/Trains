package com.moulmark.elevators;

import com.moulmark.elevators.commands.GiveCommand;
import com.moulmark.elevators.listeners.ElevatorBreakEvent;
import com.moulmark.elevators.listeners.ElevatorPlaceEvent;
import com.moulmark.elevators.listeners.TriggerElevatorListener;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class Elevators extends JavaPlugin {
    private static Elevators instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Commands
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(
                Commands.literal("elevators")
                    .then(GiveCommand.brigadier())
                    .build()
            );
        });

        // Plugin Listeners
        getServer().getPluginManager().registerEvents(new ElevatorBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new ElevatorPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new TriggerElevatorListener(), this);

        getLogger().info("Elevators has been enabled!");
    }

    public static Elevators getInstance() {
        return instance;
    }
}
