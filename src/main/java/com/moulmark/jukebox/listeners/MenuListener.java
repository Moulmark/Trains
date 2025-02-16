package com.moulmark.jukebox.listeners;

import com.moulmark.jukebox.Jukebox;
import com.moulmark.jukebox.inventory.Button;
import com.moulmark.jukebox.inventory.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class MenuListener implements Listener {
    private BukkitTask updateTask;

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder() instanceof Menu menu)) return;
        updateTask = Bukkit.getScheduler().runTaskTimer(Jukebox.getPlugin(), () -> {
            menu.getButtons().forEach((slot, button) -> {
                inventory.setItem(slot, button.getItemStack());
            });
        }, 0L, 20L);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (updateTask != null) updateTask.cancel();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        if (!(inventory.getHolder() instanceof Menu menu)) return;
        if (!menu.isTransferable()) event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        menu.getButtons().forEach((_, button) -> {
            if (!button.getItemStack().equals(item)) return;
            event.setCancelled(true);
            button.getClickConsumer().accept(player);
        });
    }
}
