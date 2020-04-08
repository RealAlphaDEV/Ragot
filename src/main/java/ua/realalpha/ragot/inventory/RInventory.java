package ua.realalpha.ragot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class RInventory implements InventoryHolder {

    private Inventory inventory;
    private Map<Integer, Consumer<InventoryClickEvent>> map;

    public RInventory(String name, int size) {
        this.inventory = Bukkit.createInventory(null, size, name);
        this.map = new HashMap<>();
    }

    public RInventory(String name, InventoryType inventoryType) {
        this.inventory = Bukkit.createInventory(null, inventoryType, name);
        this.map = new HashMap<>();
    }

    public void addItem(ItemStack itemStack){
        int slot = inventory.firstEmpty();
        this.inventory.setItem(slot, itemStack);
    }

    public void addItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer){
        int slot = inventory.firstEmpty();
        this.setItem(slot, itemStack, consumer);
    }

    public void setItem(int slot, ItemStack itemStack){
        this.inventory.setItem(slot, itemStack);
    }

    public void setItem(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> consumer){
        this.inventory.setItem(slot, itemStack);
        this.map.put(slot, consumer);
    }

    public void setItem(int[] slot, ItemStack itemStack){
        this.setItem(slot, itemStack, null);
    }

    public void setItem(int[] slot, ItemStack itemStack, Consumer<InventoryClickEvent> consumer){
        for (int i : slot) {
            this.setItem(i, itemStack, consumer);
        }
    }
    public void setHorizontalLine(int from, int to, ItemStack itemStack) {
        setHorizontalLine(from, to, itemStack, null);
    }

    public void setHorizontalLine(int from, int to, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        for (int i = from; i <= to; i++) {
            inventory.setItem(i, itemStack);
            if(consumer != null) this.map.put(i, consumer);
        }
    }

    public void setVerticalLine(int from, int to, ItemStack itemStack) {
        setVerticalLine(from, to, itemStack, null);
    }

    public void setVerticalLine(int from, int to, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        for (int i = from; i <= to; i += 9) {
            inventory.setItem(i, itemStack);
            if(consumer != null) this.map.put(i, consumer);
        }
    }

    public void update(Consumer<?> consumer, int delay){

    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
