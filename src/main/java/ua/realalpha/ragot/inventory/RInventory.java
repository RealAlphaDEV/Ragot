package ua.realalpha.ragot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class RInventory implements InventoryHolder, RInventoryEvent {

    private Inventory inventory;
    private PageController pageController;
    private Map<Integer, Consumer<InventoryClickEvent>> mapShare;

    public RInventory(String name, int size) {
        this.inventory = Bukkit.createInventory(this, size, name);
        this.mapShare = new HashMap<>();
        this.pageController = new PageController(this);
    }


    public RInventory(String name, InventoryType inventoryType) {
        this.inventory = Bukkit.createInventory(this, inventoryType, name);
        this.mapShare = new HashMap<>();
        this.pageController = new PageController(this);
    }

    public void addItem(ItemStack itemStack){
        int slot = inventory.firstEmpty();
        this.setItem(slot, itemStack);
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
        this.mapShare.put(slot, consumer);
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
            if(consumer != null) this.mapShare.put(i, consumer);
        }
    }

    public void setVerticalLine(int from, int to, ItemStack itemStack) {
        setVerticalLine(from, to, itemStack, null);
    }

    public void setVerticalLine(int from, int to, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        for (int i = from; i <= to; i += 9) {
            inventory.setItem(i, itemStack);
            if(consumer != null) this.mapShare.put(i, consumer);
        }
    }

    public void setPageController(Consumer<PageController> pageController){
        pageController.accept(this.pageController);
        this.pageController.setUp();
    }

    public PageController getPageController(){
        return this.pageController;
    }

    public int[] getBoard(int slotfrom, int lenth, int width){
        int[] board = new int[lenth*width];
        int size = 0;
        int adder = 0;
        int multiplicateur = 1;
        int l = 0;
        while (size != board.length){
            board[size] = slotfrom + adder;
            adder++;
            size++;
            l++;
            if(l == lenth){
                adder = multiplicateur*9;
                multiplicateur++;
                l = 0;
            }
        }
        return board;
    }


    public void open(Player player){
        player.openInventory(this.inventory);
    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }

     final Map<Integer, Consumer<InventoryClickEvent>> getMapShare() {
        return mapShare;
    }

}
