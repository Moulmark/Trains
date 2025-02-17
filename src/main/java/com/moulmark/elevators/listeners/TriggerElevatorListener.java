package com.moulmark.elevators.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.moulmark.elevators.ElevatorsManager;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class TriggerElevatorListener implements Listener {
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        Block block = getBlockBelowPlayer(player);
        if (!ElevatorsManager.isElevator(block)) return;

        Location aboveLocation = ElevatorsManager.findAboveLocation(block);
        if (aboveLocation == null) return;
        player.teleport(aboveLocation);
        player.playSound(player.getLocation(), Sound.BLOCK_BARREL_OPEN, 0.1F, 0.1F);
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Block block = getBlockBelowPlayer(player);
        if (!ElevatorsManager.isElevator(block)) return;

        Location belowLocation = ElevatorsManager.findBellowLocation(block);
        if (belowLocation == null) return;
        player.teleport(belowLocation);
        player.playSound(player.getLocation(), Sound.BLOCK_BARREL_OPEN, 0.1F, 0.1F);
    }

    private Block getBlockBelowPlayer(Player player) {
        Location playerLocation = player.getLocation();
        Location blockBelowLocation = playerLocation.clone().subtract(0, 1, 0);
        return blockBelowLocation.getBlock();
    }
}
