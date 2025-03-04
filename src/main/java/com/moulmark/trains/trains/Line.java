package com.moulmark.trains.trains;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.data.Rail;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@Setter
public final class Line {
    private final String name;
    private List<Rail> path;
    private List<Station> stations;
    private Train train;

    public Line(String name) {
        this.name = name;
    }

    public @Nullable Station getNextStation() {
        return null;
    }

    public @Nullable Station getPreviousStation() {
        return null;
    }
}
