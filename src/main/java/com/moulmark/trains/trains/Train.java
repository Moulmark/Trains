package com.moulmark.trains.trains;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class Train {
    private final List<Minecart> wagons;
    private Station station;
    private int size;
    private boolean stopped;

    public Train(int size, Station station) {
        this.size = size;
        this.station = station;
        this.wagons = new ArrayList<>();
        this.stopped = true;
    }

    public void spawn(Location location) {
        for (int i = 0; i <= size; i++) {
            Minecart minecart = location.getWorld().spawn(location, Minecart.class);
            minecart.setInvulnerable(true);
            minecart.setSlowWhenEmpty(false);
            wagons.add(minecart);
        }
    }

    public void mount() {
        for (Entity entity : station.getPassengers()) {
            for (Minecart minecart : wagons) {
                if (!minecart.getPassengers().isEmpty()) continue;
                minecart.addPassenger(entity);
            }
        }
    }

    public void unmount() {
        for (Minecart minecart : wagons) {
            List<Entity> passengers = minecart.getPassengers();
            passengers.forEach(minecart::removePassenger);
        }
    }

    public void move() {
        for (Minecart minecart : wagons)
            minecart.setVelocity(new Vector(1, 0, 0));
        this.stopped = false;
    }

    public void stop() {
        if (isStopped()) return;
        this.stopped = true;
    }

    public void delete() {
        unmount();
        wagons.forEach(Entity::remove);
    }
}
