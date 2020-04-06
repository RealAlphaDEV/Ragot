package ua.realalpha.ragot.inventory;

import org.bukkit.event.inventory.InventoryType;

public abstract class RInventoryProvider extends RInventory {

    protected RInventoryProvider(String name, int size) {
        super(name, size);
    }

    protected RInventoryProvider(String name, InventoryType inventoryType) {
        super(name, inventoryType);
    }
}
