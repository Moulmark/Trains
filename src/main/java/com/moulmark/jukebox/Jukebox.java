package com.moulmark.jukebox;

import com.moulmark.jukebox.listeners.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Jukebox {
    private static JavaPlugin plugin;

    public static void init(@NotNull JavaPlugin plugin) {
        Jukebox.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
        plugin.getLogger().info("Jukebox registered!");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
