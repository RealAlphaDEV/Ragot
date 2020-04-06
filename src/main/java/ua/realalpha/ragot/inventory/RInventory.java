package ua.realalpha.ragot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class RInventory implements InventoryHolder {

    private Inventory inventory;

    protected RInventory(String name, int size) {
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    protected RInventory(String name, InventoryType inventoryType) {
        this.inventory = Bukkit.createInventory(null, inventoryType, name);
    }

    public void addItem(ItemStack itemStack){
        int slot = inventory.firstEmpty();
        this.inventory.setItem(slot, itemStack);
    }

    public void setItem(int slot, ItemStack itemStack){
        this.inventory.setItem(slot, itemStack);
    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
