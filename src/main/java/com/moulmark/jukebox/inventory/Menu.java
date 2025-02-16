package com.moulmark.jukebox.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {
    private final Inventory inventory;
    private final Map<Integer, Button> buttons;
    private boolean isTransferable;

    public Menu(int size, Component title) {
        this.inventory = Bukkit.createInventory(this, size, title);
        this.buttons = new HashMap<>();
        this.isTransferable = false;
    }

    public Menu(InventoryType type, Component title) {
        this.inventory = Bukkit.createInventory(this, type, title);
        this.buttons = new HashMap<>();
        this.isTransferable = false;
    }

    public void addItem(int slot, Button button) {
        inventory.setItem(slot, button.getItemStack());
        buttons.put(slot, button);
    }

    public void removeItem(int slot) {
        ItemStack itemStack = inventory.getItem(slot);
        if (itemStack == null) return;
        inventory.remove(itemStack);
        buttons.remove(slot);
    }

    public Map<Integer, Button> getButtons() {
        return buttons;
    }

    public boolean isTransferable() {
        return isTransferable;
    }

    public void setTransferable(boolean transferable) {
        isTransferable = transferable;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
