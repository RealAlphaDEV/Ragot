package ua.realalpha.ragot.inventory;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface RInventoryEvent {

    default void onClose(InventoryCloseEvent event){ }
    default void onOpen(InventoryOpenEvent event){ }

}
