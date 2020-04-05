package ua.realalpha.ragot.item;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class RItemHandler implements Listener {

    private RItemManager itemManager;
    public RItemHandler(RItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        ItemStack itemStack = event.getItem();
        if(itemStack == null) return;
        RItemBuilder RItemBuilder = new RItemBuilder(itemStack);
        if (RItemBuilder.unsafe().containsTag("RItemProvider")) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(RItemBuilder.unsafe().getString("RItemProvider"));
                itemManager.getItemProvider(itemProvider).setInteractEvent(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        ItemStack itemStack = event.getItemDrop().getItemStack();
        RItemBuilder RItemBuilder = new RItemBuilder(itemStack);
        if (RItemBuilder.unsafe().containsTag("RItemProvider")) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(RItemBuilder.unsafe().getString("RItemProvider"));
                itemManager.getItemProvider(itemProvider).setDropEvent(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @EventHandler
    public void itemBreakEvent(PlayerItemBreakEvent event){
        ItemStack itemStack = event.getBrokenItem();
        RItemBuilder RItemBuilder = new RItemBuilder(itemStack);
        if (RItemBuilder.unsafe().containsTag("RItemProvider")) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(RItemBuilder.unsafe().getString("RItemProvider"));
                itemManager.getItemProvider(itemProvider).setItemBreakEvent(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void itemConsumeEvent(PlayerItemConsumeEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder RItemBuilder = new RItemBuilder(itemStack);
        if (RItemBuilder.unsafe().containsTag("RItemProvider")) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(RItemBuilder.unsafe().getString("RItemProvider"));
                itemManager.getItemProvider(itemProvider).setItemConsumeEvent(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void itemDamageEvent(PlayerItemDamageEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder RItemBuilder = new RItemBuilder(itemStack);
        if (RItemBuilder.unsafe().containsTag("RItemProvider")) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(RItemBuilder.unsafe().getString("RItemProvider"));
                itemManager.getItemProvider(itemProvider).setItemDamageEvent(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void pickupItemEvent(PlayerPickupItemEvent event){
        ItemStack itemStack = event.getItem().getItemStack();
        RItemBuilder RItemBuilder = new RItemBuilder(itemStack);
        if (RItemBuilder.unsafe().containsTag("RItemProvider")) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(RItemBuilder.unsafe().getString("RItemProvider"));
                itemManager.getItemProvider(itemProvider).setPickupItemEvent(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
