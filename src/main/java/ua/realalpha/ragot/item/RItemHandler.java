package ua.realalpha.ragot.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class RItemHandler implements Listener {

    private RItemManager itemManager;
    public RItemHandler(RItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        ItemStack itemStack = event.getItem();
        if(itemStack == null) return;
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerInteractEvent(event));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof Player) {
            ItemStack itemStack = event.getCurrentItem();
            if (itemStack == null || itemStack.getType() == Material.AIR) return;
            RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
            callListener(rItemBuilder, rItemEvent -> rItemEvent.setInventoryClickEvent(event));
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        ItemStack itemStack = event.getItemDrop().getItemStack();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerDropEvent(event));

    }

    @EventHandler
    public void itemBreakEvent(PlayerItemBreakEvent event){
        ItemStack itemStack = event.getBrokenItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerItemBreakEvent(event));
    }

    @EventHandler
    public void itemConsumeEvent(PlayerItemConsumeEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerItemConsumeEvent(event));
    }

    @EventHandler
    public void itemDamageEvent(PlayerItemDamageEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerItemDamageEvent(event));
    }

    @EventHandler
    public void pickupItemEvent(PlayerPickupItemEvent event){
        ItemStack itemStack = event.getItem().getItemStack();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPickupItemEvent(event));
    }

    @EventHandler
    public void blockDispenseEvent(BlockDispenseEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setBlockDispenseEvent(event));
    }

    @EventHandler
    public void enchantItemEvent(EnchantItemEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setEnchantItemEvent(event));
    }


    @EventHandler
    public void itemDespawnEvent(ItemDespawnEvent event){
        ItemStack itemStack = event.getEntity().getItemStack();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setItemDespawnEvent(event));
    }

    @EventHandler
    public void furnaceBurnEvent(FurnaceBurnEvent event){
        ItemStack itemStack = event.getFuel();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setFurnaceBurnEvent(event));
    }

    @EventHandler
    public void furnaceSmeltEvent(FurnaceSmeltEvent event){
        ItemStack itemStack = event.getResult();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setFurnaceSmeltEvent(event));
    }

    @EventHandler
    public void playerItemHeldEvent(PlayerItemHeldEvent event){
        Inventory inventory = event.getPlayer().getInventory();
        if (isRItem(inventory.getItem(event.getPreviousSlot())) || isRItem(inventory.getItem(event.getNewSlot()))) {
            RItemBuilder rItemBuilder = new RItemBuilder((isRItem(inventory.getItem(event.getPreviousSlot())) ? inventory.getItem(event.getPreviousSlot()) : inventory.getItem(event.getNewSlot())));
            callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerItemHeldEvent(event));
        }
    }

    @EventHandler
    public void playerFishEvent(PlayerFishEvent event){
        ItemStack itemStack = event.getPlayer().getItemInHand();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPlayerFishEvent(event));
    }

    @EventHandler
    public void potionSplashEvent(PotionSplashEvent event){
        ItemStack itemStack = event.getPotion().getItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPotionSplashEvent(event));
    }

    @EventHandler
    public void prepareItemEnchantEvent(PrepareItemEnchantEvent event){
        ItemStack itemStack = event.getItem();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setPrepareItemEnchantEvent(event));
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event){
        ItemStack itemStack = event.getItemInHand();
        RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
        callListener(rItemBuilder, rItemEvent -> rItemEvent.setBlockPlaceEvent(event));
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event){
        ItemStack itemStack = event.getPlayer().getItemInHand();
        if (itemStack != null) {
            RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
            callListener(rItemBuilder, rItemEvent -> rItemEvent.setBlockBreakEvent(event));
        }
    }

    private void callListener(RItemBuilder rItemBuilder, Consumer<RItemEvent> consumer){
        if (isRItem(rItemBuilder)) {
            try {
                Class<RItemProvider> itemProvider = (Class<RItemProvider>) Class.forName(rItemBuilder.unsafe().getString("RItemProvider"));
                consumer.accept(itemManager.getItemProvider(itemProvider));
            }catch (Exception ignored){
            }
        }
    }

    private boolean isRItem(ItemStack itemStack){
        return itemStack != null && new RItemBuilder(itemStack).unsafe().containsTag("RItemProvider");
    }



}
