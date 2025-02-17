package com.moulmark.elevators;

import com.jeff_media.customblockdata.CustomBlockData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ElevatorsManager {
    private static final NamespacedKey key = new NamespacedKey(Elevators.getInstance(), "elevator");

    public static ItemStack createElevatorItem() {
        String configMaterial = Elevators.getInstance().getConfig().getString("elevators.material");
        String elevatorDisplayName = Elevators.getInstance().getConfig().getString("elevators.display-name");

        if (configMaterial == null) {
            throw new IllegalArgumentException("Material must be set!");
        }

        if (elevatorDisplayName == null) {
            throw new IllegalArgumentException("DisplayName must be set!");
        }

        Material material = Material.getMaterial(configMaterial);
        if (material == null || !material.isBlock()) {
            throw new IllegalArgumentException("Material not found or is not block material!");
        }

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Component display = LegacyComponentSerializer.legacyAmpersand().deserialize(elevatorDisplayName);
        itemMeta.displayName(display);
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void setElevatorIdentifier(Block block) {
        PersistentDataContainer container = new CustomBlockData(block, Elevators.getInstance());
        container.set(key, PersistentDataType.BYTE, (byte) 1);
    }

    public static Location findAboveLocation(Block block) {
        Location blockLocation = block.getLocation();
        int blockY = blockLocation.getBlockY();

        for (int y = blockY + 1; y <= block.getWorld().getMaxHeight(); y++) {
            Location location = new Location(
                blockLocation.getWorld(),
                blockLocation.getX(),
                y,
                blockLocation.getZ()
            );

            Block aboveBlock = location.getBlock();
            if (isElevator(aboveBlock)) return location.add(0.5, 1, 0.5);
        }

        return null;
    }

    public static Location findBellowLocation(Block block) {
        Location blockLocation = block.getLocation();
        int blockY = blockLocation.getBlockY();

        for (int y = blockY - 1; y >= block.getWorld().getMinHeight(); y--) {
            Location location = new Location(
                blockLocation.getWorld(),
                blockLocation.getX(),
                y,
                blockLocation.getZ()
            );

            Block belowBlock = location.getBlock();
            if (isElevator(belowBlock)) return location.add(0.5, 1, 0.5);
        }

        return null;
    }

    public static boolean isElevator(Block block) {
        return CustomBlockData.hasCustomBlockData(block, Elevators.getInstance());
    }

    public static boolean isElevator(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return itemMeta.getPersistentDataContainer().has(key);
    }
}
