package com.moulmark.trains.trains;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.data.Rail;

import java.util.List;

@Getter
@Setter
public final class Line {
    private List<Rail> path;
    private List<Station> stations;
    private Train train;
}
