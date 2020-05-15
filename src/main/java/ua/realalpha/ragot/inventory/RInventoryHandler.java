package ua.realalpha.ragot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import ua.realalpha.ragot.Ragot;

public final class RInventoryHandler implements Listener {

    private final Ragot ragot;
    private final RInventoryManager rInventoryManager;
    public RInventoryHandler(Ragot ragot, RInventoryManager rInventoryManager) {
        this.ragot = ragot;
        this.rInventoryManager = rInventoryManager;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onClick(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getInventory().getHolder() instanceof RInventory) {
            RInventory rInventory = (RInventory) event.getInventory().getHolder();
            int slot = event.getRawSlot();
            event.setCancelled(true);
            if (rInventory.getMapShare().containsKey(slot)) {
                rInventory.getMapShare().get(slot).accept(event);
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof RInventory) {
            RInventory rInventory = (RInventory) event.getInventory().getHolder();
            rInventory.getRunnableList().forEach(rInventoryRunnable -> rInventoryRunnable.getRunnable().run());
            this.rInventoryManager.put(rInventory);
            rInventory.onOpen(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof RInventory) {
            RInventory rInventory = (RInventory) event.getInventory().getHolder();
            this.rInventoryManager.remove(rInventory);
            Bukkit.getScheduler().runTask(ragot, ()-> rInventory.onClose(event));
        }
    }


}
