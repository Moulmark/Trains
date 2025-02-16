package com.moulmark.elevators;

import org.bukkit.plugin.java.JavaPlugin;

public class Elevators extends JavaPlugin {
    private static Elevators instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Elevators has been enabled!");
    }

    public static Elevators getInstance() {
        return instance;
    }
}
