package ua.realalpha.ragot.item;

import org.bukkit.event.Event;

public enum RItemEvent {

    PlayerInteract("org.bukkit.event.player.PlayerInteractEvent"),

    InventoryClick("org.bukkit.event.inventory.InventoryClickEvent"),

    PlayerDropItem("org.bukkit.event.player.PlayerDropItemEvent"),

    PlayerItemBreak("org.bukkit.event.player.PlayerItemBreakEvent"),

    PlayerItemConsume("org.bukkit.event.player.PlayerItemConsumeEvent"),

    PlayerItemDamage("org.bukkit.event.player.PlayerItemDamageEvent"),

    PlayerPickupItem("org.bukkit.event.player.PlayerPickupItemEvent"),

    BlockDispense("org.bukkit.event.block.BlockDispenseEvent"),

    EnchantItem("org.bukkit.event.enchantment.EnchantItemEvent"),

    ItemDespawn("org.bukkit.event.entity.ItemDespawnEvent"),

    FurnaceBurn("org.bukkit.event.inventory.FurnaceBurnEvent"),

    FurnaceSmelt("org.bukkit.event.inventory.FurnaceSmeltEvent"),

    PlayerItemHeld("org.bukkit.event.player.PlayerItemHeldEvent"),

    PlayerFish("org.bukkit.event.player.PlayerFishEvent"),

    PotionSplash("org.bukkit.event.entity.PotionSplashEvent"),

    PrepareItemEnchant("org.bukkit.event.enchantment.PrepareItemEnchantEvent"),

    BlockPlace("org.bukkit.event.block.BlockPlaceEvent"),

    BlockBreak("org.bukkit.event.block.BlockBreakEvent"),

    PlayerSwapHandItems("org.bukkit.event.player.PlayerSwapHandItemsEvent");

    private final String aClass;

    RItemEvent(String aClass) {
        this.aClass = aClass;
    }

    public Class<? extends Event> getClazz() throws ClassNotFoundException {
        return (Class<? extends Event>) Class.forName(aClass);
    }
}
