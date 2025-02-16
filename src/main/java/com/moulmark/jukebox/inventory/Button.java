package com.moulmark.jukebox.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class Button {
    private final ItemStack itemStack;
    private Consumer<Player> clickConsumer;

    public Button(Material material) {
        this.itemStack = new ItemStack(material);
        this.clickConsumer = null;
    }

    public Button onClick(Consumer<Player> player) {
        this.clickConsumer = player;
        return this;
    }

    public Consumer<Player> getClickConsumer() {
        return clickConsumer;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
