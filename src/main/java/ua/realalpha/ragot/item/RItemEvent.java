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

import java.io.Serializable;

public final class RItemEvent implements Serializable {

    private RConsumer<PlayerInteractEvent> playerInteractEvent;
    private RConsumer<PlayerDropItemEvent> playerDropItemEvent;
    private RConsumer<PlayerItemBreakEvent> playerItemBreakEvent;
    private RConsumer<PlayerItemConsumeEvent> playerItemConsumeEvent;
    private RConsumer<PlayerItemDamageEvent> playerItemDamageEvent;
    private RConsumer<PlayerPickupItemEvent> playerPickupItemEvent;
    private RConsumer<InventoryClickEvent> inventoryClickEvent;
    private RConsumer<BlockDispenseEvent> blockDispenseEvent;
    private RConsumer<EnchantItemEvent> enchantItemEvent;
    private RConsumer<PrepareItemEnchantEvent> prepareItemEnchantEvent;
    private RConsumer<ItemDespawnEvent> itemDespawnEvent;
    private RConsumer<FurnaceBurnEvent> furnaceBurnEvent;
    private RConsumer<FurnaceSmeltEvent> furnaceSmeltEvent;
    private RConsumer<PlayerItemHeldEvent> playerItemHeldEvent;
    private RConsumer<PlayerFishEvent> playerFishEvent;
    private RConsumer<PotionSplashEvent> potionSplashEvent;
    private RConsumer<BlockBreakEvent> blockBreakEvent;
    private RConsumer<BlockPlaceEvent> blockPlaceEvent;
    private RConsumer<PlayerSwapHandItemsEvent> playerSwapHandItemsEvent;

    public RItemEvent() {
        this.playerInteractEvent = playerInteractEvent -> {};
        this.playerDropItemEvent = playerDropItemEvent -> {};
        this.playerItemBreakEvent = playerItemBreakEvent -> {};
        this.playerItemConsumeEvent = playerItemConsumeEvent -> {};
        this.playerItemDamageEvent = playerItemDamageEvent -> {};
        this.playerPickupItemEvent = playerPickupItemEvent -> {};
        this.inventoryClickEvent = inventoryClickEvent -> {};
        this.blockDispenseEvent = blockBreakEvent -> {};
        this.enchantItemEvent = enchantItemEvent -> {};
        this.prepareItemEnchantEvent = prepareItemEnchantEvent -> {};
        this.itemDespawnEvent = itemDespawnEvent -> {};
        this.furnaceBurnEvent = furnaceBurnEvent -> {};
        this.furnaceSmeltEvent = furnaceSmeltEvent -> {};
        this.playerItemHeldEvent = playerItemHeldEvent -> {};
        this.playerFishEvent = playerFishEvent -> {};
        this.potionSplashEvent = potionSplashEvent -> {};
        this.blockBreakEvent = blockBreakEvent -> {};
        this.blockPlaceEvent = blockPlaceEvent -> {};
        this.playerSwapHandItemsEvent = playerSwapHandItemsEvent -> {};
    }

    public RConsumer<PlayerInteractEvent> getPlayerInteractEvent() {
        return playerInteractEvent;
    }

    public RItemEvent setPlayerInteractEvent(RConsumer<PlayerInteractEvent> playerInteractEvent) {
        this.playerInteractEvent = playerInteractEvent;
        return this;
    }

    public RConsumer<PlayerDropItemEvent> getPlayerDropItemEvent() {
        return playerDropItemEvent;
    }

    public RItemEvent setPlayerDropItemEvent(RConsumer<PlayerDropItemEvent> playerDropItemEvent) {
        this.playerDropItemEvent = playerDropItemEvent;
        return this;
    }

    public RConsumer<PlayerItemBreakEvent> getPlayerItemBreakEvent() {
        return playerItemBreakEvent;
    }

    public RItemEvent setPlayerItemBreakEvent(RConsumer<PlayerItemBreakEvent> playerItemBreakEvent) {
        this.playerItemBreakEvent = playerItemBreakEvent;
        return this;
    }

    public RConsumer<PlayerItemConsumeEvent> getPlayerItemConsumeEvent() {
        return playerItemConsumeEvent;
    }

    public RItemEvent setPlayerItemConsumeEvent(RConsumer<PlayerItemConsumeEvent> playerItemConsumeEvent) {
        this.playerItemConsumeEvent = playerItemConsumeEvent;
        return this;
    }

    public RConsumer<PlayerItemDamageEvent> getPlayerItemDamageEvent() {
        return playerItemDamageEvent;
    }

    public RItemEvent setPlayerItemDamageEvent(RConsumer<PlayerItemDamageEvent> playerItemDamageEvent) {
        this.playerItemDamageEvent = playerItemDamageEvent;
        return this;
    }

    public RConsumer<PlayerPickupItemEvent> getPlayerPickupItemEvent() {
        return playerPickupItemEvent;
    }

    public RItemEvent setPlayerPickupItemEvent(RConsumer<PlayerPickupItemEvent> playerPickupItemEvent) {
        this.playerPickupItemEvent = playerPickupItemEvent;
        return this;
    }

    public RConsumer<InventoryClickEvent> getInventoryClickEvent() {
        return inventoryClickEvent;
    }

    public RItemEvent setInventoryClickEvent(RConsumer<InventoryClickEvent> inventoryClickEvent) {
        this.inventoryClickEvent = inventoryClickEvent;
        return this;
    }

    public RConsumer<BlockDispenseEvent> getBlockDispenseEvent() {
        return blockDispenseEvent;
    }

    public RItemEvent setBlockDispenseEvent(RConsumer<BlockDispenseEvent> blockDispenseEvent) {
        this.blockDispenseEvent = blockDispenseEvent;
        return this;
    }

    public RConsumer<EnchantItemEvent> getEnchantItemEvent() {
        return enchantItemEvent;
    }

    public RItemEvent setEnchantItemEvent(RConsumer<EnchantItemEvent> enchantItemEvent) {
        this.enchantItemEvent = enchantItemEvent;
        return this;
    }

    public RConsumer<PrepareItemEnchantEvent> getPrepareItemEnchantEvent() {
        return prepareItemEnchantEvent;
    }

    public RItemEvent setPrepareItemEnchantEvent(RConsumer<PrepareItemEnchantEvent> prepareItemEnchantEvent) {
        this.prepareItemEnchantEvent = prepareItemEnchantEvent;
        return this;
    }

    public RConsumer<ItemDespawnEvent> getItemDespawnEvent() {
        return itemDespawnEvent;
    }

    public RItemEvent setItemDespawnEvent(RConsumer<ItemDespawnEvent> itemDespawnEvent) {
        this.itemDespawnEvent = itemDespawnEvent;
        return this;
    }

    public RConsumer<FurnaceBurnEvent> getFurnaceBurnEvent() {
        return furnaceBurnEvent;
    }

    public RItemEvent setFurnaceBurnEvent(RConsumer<FurnaceBurnEvent> furnaceBurnEvent) {
        this.furnaceBurnEvent = furnaceBurnEvent;
        return this;
    }

    public RConsumer<FurnaceSmeltEvent> getFurnaceSmeltEvent() {
        return furnaceSmeltEvent;
    }

    public RItemEvent setFurnaceSmeltEvent(RConsumer<FurnaceSmeltEvent> furnaceSmeltEvent) {
        this.furnaceSmeltEvent = furnaceSmeltEvent;
        return this;
    }

    public RConsumer<PlayerItemHeldEvent> getPlayerItemHeldEvent() {
        return playerItemHeldEvent;
    }

    public RItemEvent setPlayerItemHeldEvent(RConsumer<PlayerItemHeldEvent> playerItemHeldEvent) {
        this.playerItemHeldEvent = playerItemHeldEvent;
        return this;
    }

    public RConsumer<PlayerFishEvent> getPlayerFishEvent() {
        return playerFishEvent;
    }

    public RItemEvent setPlayerFishEvent(RConsumer<PlayerFishEvent> playerFishEvent) {
        this.playerFishEvent = playerFishEvent;
        return this;
    }

    public RConsumer<PotionSplashEvent> getPotionSplashEvent() {
        return potionSplashEvent;
    }

    public RItemEvent setPotionSplashEvent(RConsumer<PotionSplashEvent> potionSplashEvent) {
        this.potionSplashEvent = potionSplashEvent;
        return this;
    }

    public RConsumer<BlockBreakEvent> getBlockBreakEvent() {
        return blockBreakEvent;
    }

    public RItemEvent setBlockBreakEvent(RConsumer<BlockBreakEvent> blockBreakEvent) {
        this.blockBreakEvent = blockBreakEvent;
        return this;
    }

    public RConsumer<BlockPlaceEvent> getBlockPlaceEvent() {
        return blockPlaceEvent;
    }

    public RItemEvent setBlockPlaceEvent(RConsumer<BlockPlaceEvent> blockPlaceEvent) {
        this.blockPlaceEvent = blockPlaceEvent;
        return this;
    }

    public RConsumer<PlayerSwapHandItemsEvent> getPlayerSwapHandItemsEvent() {
        return playerSwapHandItemsEvent;
    }

    public RItemEvent setPlayerSwapHandItemsEvent(RConsumer<PlayerSwapHandItemsEvent> playerSwapHandItemsEvent) {
        this.playerSwapHandItemsEvent = playerSwapHandItemsEvent;
        return this;
    }
}
