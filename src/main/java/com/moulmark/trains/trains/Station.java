package com.moulmark.trains.trains;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class Station {
    private final String name;
    private final List<Entity> passengers;
    private Location stop;
    private Location platform;
    private boolean opened;
    private boolean skippable;

    public Station(String name, Location stop, Location platform) {
        this.name = name;
        this.passengers = new ArrayList<>();
        this.stop = stop;
        this.platform = platform;
        this.opened = true;
        this.skippable = false;
    }
}
