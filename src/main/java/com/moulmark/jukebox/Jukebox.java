package com.moulmark.jukebox;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Jukebox {
    private static JavaPlugin plugin;

    public static void init(@NotNull JavaPlugin plugin) {
        Jukebox.plugin = plugin;
        plugin.getLogger().info("Jukebox registered!");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
