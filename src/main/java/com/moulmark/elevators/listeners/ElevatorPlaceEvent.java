package com.moulmark.elevators.listeners;

import com.moulmark.elevators.ElevatorsManager;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class ElevatorPlaceEvent implements Listener {
    @EventHandler
    public void onElevatorPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        if (!ElevatorsManager.isElevator(itemStack)) return;
        Block block = event.getBlock();
        ElevatorsManager.setElevatorIdentifier(block);
    }
}
