package ua.realalpha.ragot.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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
import ua.realalpha.ragot.Ragot;
import ua.realalpha.ragot.version.Version;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class RItemManager {

    private final List<Listener> list;
    private final Ragot ragot;
    public RItemManager(Ragot ragot) {
        this.ragot = ragot;
        this.list = new ArrayList<>();
        Version version = Version.getServerVersion();

        this.list.add(new Listener() {
            @EventHandler
            public void onInteract(PlayerInteractEvent event) {
                ItemStack itemStack = event.getItem();
                ((RConsumer<PlayerInteractEvent>) get(itemStack, PlayerInteractEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof Player) {
                    ItemStack itemStack = event.getCurrentItem();
                    ((RConsumer<InventoryClickEvent>) get(itemStack, InventoryClickEvent.class)).accept(event);
                }
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void onDrop(PlayerDropItemEvent event) {
                ItemStack itemStack = event.getItemDrop().getItemStack();
                ((RConsumer<PlayerDropItemEvent>) get(itemStack, PlayerDropItemEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void itemBreakEvent(PlayerItemBreakEvent event) {
                ItemStack itemStack = event.getBrokenItem();
                ((RConsumer<PlayerItemBreakEvent>) get(itemStack, PlayerItemBreakEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void itemConsumeEvent(PlayerItemConsumeEvent event) {
                ItemStack itemStack = event.getItem();
                ((RConsumer<PlayerItemConsumeEvent>) get(itemStack, PlayerItemConsumeEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void itemDamageEvent(PlayerItemDamageEvent event) {
                ItemStack itemStack = event.getItem();
                ((RConsumer<PlayerItemDamageEvent>) get(itemStack, PlayerItemDamageEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void pickupItemEvent(PlayerPickupItemEvent event) {
                ItemStack itemStack = event.getItem().getItemStack();
                ((RConsumer<PlayerPickupItemEvent>) get(itemStack, PlayerPickupItemEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void blockDispenseEvent(BlockDispenseEvent event) {
                ItemStack itemStack = event.getItem();
                ((RConsumer<BlockDispenseEvent>) get(itemStack, BlockDispenseEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void enchantItemEvent(EnchantItemEvent event) {
                ItemStack itemStack = event.getItem();
                ((RConsumer<EnchantItemEvent>) get(itemStack, EnchantItemEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void itemDespawnEvent(ItemDespawnEvent event) {
                ItemStack itemStack = event.getEntity().getItemStack();
                ((RConsumer<ItemDespawnEvent>) get(itemStack, ItemDespawnEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void furnaceBurnEvent(FurnaceBurnEvent event) {
                ItemStack itemStack = event.getFuel();
                ((RConsumer<FurnaceBurnEvent>) get(itemStack, FurnaceBurnEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void furnaceSmeltEvent(FurnaceSmeltEvent event) {
                ItemStack itemStack = event.getResult();
                ((RConsumer<FurnaceSmeltEvent>) get(itemStack, FurnaceSmeltEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void playerItemHeldEvent(PlayerItemHeldEvent event) {
                Inventory inventory = event.getPlayer().getInventory();
                if (isRItem(inventory.getItem(event.getPreviousSlot())) || isRItem(inventory.getItem(event.getNewSlot()))) {
                    ((RConsumer<PlayerItemHeldEvent>) get((isRItem(inventory.getItem(event.getPreviousSlot())) ? inventory.getItem(event.getPreviousSlot()) : inventory.getItem(event.getNewSlot())), PlayerItemHeldEvent.class)).accept(event);
                }
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void playerFishEvent(PlayerFishEvent event) {
                ItemStack itemStack = event.getPlayer().getItemInHand();
                ((RConsumer<PlayerFishEvent>) get(itemStack, PlayerFishEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void potionSplashEvent(PotionSplashEvent event){
                ItemStack itemStack = event.getPotion().getItem();
                ((RConsumer<PotionSplashEvent>) get(itemStack, PotionSplashEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void prepareItemEnchantEvent(PrepareItemEnchantEvent event){
                ItemStack itemStack = event.getItem();
                ((RConsumer<PrepareItemEnchantEvent>) get(itemStack, PrepareItemEnchantEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void blockPlaceEvent(BlockPlaceEvent event){
                ItemStack itemStack = event.getItemInHand();
                ((RConsumer<BlockPlaceEvent>) get(itemStack, BlockPlaceEvent.class)).accept(event);
            }
        });
        this.list.add(new Listener() {
            @EventHandler
            public void blockBreakEvent(BlockBreakEvent event){
                ItemStack itemStack = event.getPlayer().getItemInHand();
                ((RConsumer<BlockBreakEvent>) get(itemStack, BlockBreakEvent.class)).accept(event);
            }
        });
        if (version.getVersion() >= 9) {
            this.list.add(new Listener() {
                @EventHandler
                public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
                    if (isRItem(event.getMainHandItem()) || isRItem(event.getOffHandItem())) {
                        ItemStack itemStack = (isRItem(event.getMainHandItem()) ? event.getMainHandItem() : event.getOffHandItem());
                        ((RConsumer<PlayerSwapHandItemsEvent>) get(itemStack, PlayerSwapHandItemsEvent.class)).accept(event);
                    }
                }
            });
        }

    }


    public void registerListeners(){
        this.list.forEach(listener -> {
            ragot.getLogger().log(Level.INFO, "The "+getEvent(listener).getSimpleName()+" event was recorded");
            Bukkit.getPluginManager().registerEvents(listener, ragot);
        });
    }

    private Class<?> getEvent(Listener listener){
        return listener.getClass().getMethods()[0].getParameterTypes()[0];
    }

    private RConsumer<? extends Event> get(ItemStack itemStack, Class<? extends Event> aClass){
        if (isRItem(itemStack)) {
            RItemBuilder rItemBuilder = new RItemBuilder(itemStack);
            RItemData rItemData = (RItemData) deserialize(rItemBuilder.unsafe().getByteArray("RItemEvent"));
            return rItemData.get(aClass);
        }
        return event ->{};
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
