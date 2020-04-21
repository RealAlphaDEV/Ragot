package ua.realalpha.ragot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import ua.realalpha.ragot.Ragot;

public class RInventoryHandler implements Listener {

    private Ragot ragot;
    public RInventoryHandler(Ragot ragot) {
        this.ragot = ragot;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onClick(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getInventory().getHolder() instanceof RInventory) {
            RInventory rInventory = (RInventory) event.getInventory().getHolder();
            int slot = event.getRawSlot();
            if (rInventory.getMapShare().containsKey(slot)) {
                event.setCancelled(true);
                rInventory.getMapShare().get(slot).accept(event);
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        if (event.getInventory() != null && event.getInventory().getHolder() instanceof RInventory) {
            RInventory rInventory = (RInventory) event.getInventory().getHolder();
            rInventory.onOpen(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory() != null && event.getInventory().getHolder() instanceof RInventory) {
            RInventory rInventory = (RInventory) event.getInventory().getHolder();
            Bukkit.getScheduler().runTask(ragot, ()-> rInventory.onClose(event));
        }
    }


}
