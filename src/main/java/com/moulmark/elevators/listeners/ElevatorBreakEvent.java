package com.moulmark.elevators.listeners;

import com.moulmark.elevators.ElevatorsManager;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ElevatorBreakEvent implements Listener {
    @EventHandler
    public void onElevatorBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!ElevatorsManager.isElevator(block)) return;
        event.setDropItems(false);
        block.breakNaturally();
        ItemStack itemStack = ElevatorsManager.createElevatorItem();
        block.getWorld().dropItem(block.getLocation(), itemStack);
    }
}
