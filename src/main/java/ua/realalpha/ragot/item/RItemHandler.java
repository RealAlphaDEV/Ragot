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

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.function.Consumer;

public class RItemHandler implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        ItemStack itemStack = event.getItem();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerInteractEvent().accept(event));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof Player) {
            ItemStack itemStack = event.getCurrentItem();
            call(itemStack, rItemEvent -> rItemEvent.getInventoryClickEvent().accept(event));
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        ItemStack itemStack = event.getItemDrop().getItemStack();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerDropItemEvent().accept(event));

    }

    @EventHandler
    public void itemBreakEvent(PlayerItemBreakEvent event){
        ItemStack itemStack = event.getBrokenItem();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerItemBreakEvent().accept(event));
    }

    @EventHandler
    public void itemConsumeEvent(PlayerItemConsumeEvent event){
        ItemStack itemStack = event.getItem();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerItemConsumeEvent().accept(event));
    }

    @EventHandler
    public void itemDamageEvent(PlayerItemDamageEvent event){
        ItemStack itemStack = event.getItem();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerItemDamageEvent().accept(event));
    }

    @EventHandler
    public void pickupItemEvent(PlayerPickupItemEvent event){
        ItemStack itemStack = event.getItem().getItemStack();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerPickupItemEvent().accept(event));
    }

    @EventHandler
    public void blockDispenseEvent(BlockDispenseEvent event){
        ItemStack itemStack = event.getItem();
        call(itemStack, rItemEvent -> rItemEvent.getBlockDispenseEvent().accept(event));
    }

    @EventHandler
    public void enchantItemEvent(EnchantItemEvent event){
        ItemStack itemStack = event.getItem();
        call(itemStack, rItemEvent -> rItemEvent.getEnchantItemEvent().accept(event));
    }


    @EventHandler
    public void itemDespawnEvent(ItemDespawnEvent event){
        ItemStack itemStack = event.getEntity().getItemStack();
        call(itemStack, rItemEvent -> rItemEvent.getItemDespawnEvent().accept(event));
    }

    @EventHandler
    public void furnaceBurnEvent(FurnaceBurnEvent event){
        ItemStack itemStack = event.getFuel();
        call(itemStack, rItemEvent -> rItemEvent.getFurnaceBurnEvent().accept(event));
    }

    @EventHandler
    public void furnaceSmeltEvent(FurnaceSmeltEvent event){
        ItemStack itemStack = event.getResult();
        call(itemStack, rItemEvent -> rItemEvent.getFurnaceSmeltEvent().accept(event));
    }

    @EventHandler
    public void playerItemHeldEvent(PlayerItemHeldEvent event){
        Inventory inventory = event.getPlayer().getInventory();
        if (isRItem(inventory.getItem(event.getPreviousSlot())) || isRItem(inventory.getItem(event.getNewSlot()))) {
            call((isRItem(inventory.getItem(event.getPreviousSlot())) ? inventory.getItem(event.getPreviousSlot()) : inventory.getItem(event.getNewSlot())), rItemEvent -> rItemEvent.getPlayerItemHeldEvent().accept(event));
        }
    }

    @EventHandler
    public void playerFishEvent(PlayerFishEvent event){
        ItemStack itemStack = event.getPlayer().getItemInHand();
        call(itemStack, rItemEvent -> rItemEvent.getPlayerFishEvent().accept(event));
    }

    @EventHandler
    public void potionSplashEvent(PotionSplashEvent event){
        ItemStack itemStack = event.getPotion().getItem();
        call(itemStack, rItemEvent -> rItemEvent.getPotionSplashEvent().accept(event));
    }

    @EventHandler
    public void prepareItemEnchantEvent(PrepareItemEnchantEvent event){
        ItemStack itemStack = event.getItem();
        call(itemStack, rItemEvent -> rItemEvent.getPrepareItemEnchantEvent().accept(event));
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event){
        ItemStack itemStack = event.getItemInHand();
        call(itemStack, rItemEvent -> rItemEvent.getBlockPlaceEvent().accept(event));
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event){
        ItemStack itemStack = event.getPlayer().getItemInHand();
        call(itemStack, rItemEvent -> rItemEvent.getBlockBreakEvent().accept(event));
    }

    @EventHandler
    public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent event){
        if (isRItem(event.getMainHandItem()) || isRItem(event.getOffHandItem())) {
            ItemStack itemStack = (isRItem(event.getMainHandItem())? event.getMainHandItem(): event.getOffHandItem());
            call(itemStack, rItemEvent -> rItemEvent.getPlayerSwapHandItemsEvent().accept(event));
        }
    }


    private void call(ItemStack itemStack, Consumer<RItemEvent> consumer){
        if (isRItem(itemStack)) {
            RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
            RItemEvent rItemEvent = (RItemEvent) deserialize(rItemBuilder.unsafe().getByteArray("RItemEvent"));
            consumer.accept(rItemEvent);
        }
    }

    private boolean isRItem(ItemStack itemStack){
        return itemStack != null && itemStack.getType() != Material.AIR && new RItemBuilder(itemStack).unsafe().containsTag("RItemEvent");
    }

    private Object deserialize(byte[] bytes) throws NullPointerException{
        try{
            ByteArrayInputStream bais =new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

}
