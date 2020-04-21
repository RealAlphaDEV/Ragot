package ua.realalpha.ragot.item;

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

public interface RItemEvent {

    default void setPlayerInteractEvent(PlayerInteractEvent event){}

    default void setPlayerDropEvent(PlayerDropItemEvent event){}

    default void setPlayerItemBreakEvent(PlayerItemBreakEvent event){}

    default void setPlayerItemConsumeEvent(PlayerItemConsumeEvent event){}

    default void setPlayerItemDamageEvent(PlayerItemDamageEvent event){}

    default void setPickupItemEvent(PlayerPickupItemEvent event){}

    default void setInventoryClickEvent(InventoryClickEvent event){}

    default void setBlockDispenseEvent(BlockDispenseEvent event){}

    default void setEnchantItemEvent(EnchantItemEvent event){}

    default void setPrepareItemEnchantEvent (PrepareItemEnchantEvent event){}

    default void setItemDespawnEvent(ItemDespawnEvent event){}

    default void setFurnaceBurnEvent(FurnaceBurnEvent event){}

    default void setFurnaceSmeltEvent(FurnaceSmeltEvent event){}

    default void setPlayerItemHeldEvent(PlayerItemHeldEvent event){}

    default void setPlayerFishEvent(PlayerFishEvent event){}

    default void setPotionSplashEvent(PotionSplashEvent event){}

    default void setBlockBreakEvent(BlockBreakEvent event){}

    default void setBlockPlaceEvent(BlockPlaceEvent event){}


}
