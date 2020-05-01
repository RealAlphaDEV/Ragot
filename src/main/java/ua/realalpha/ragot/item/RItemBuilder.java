package ua.realalpha.ragot.item;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RItemBuilder implements Serializable {

    private transient final ItemStack itemStack;
    private final RItemEvent rItemEvent;
    public RItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    public RItemBuilder(Material type, int amount, int data) {
        this(new ItemStack(type, amount, (short) data));
    }

    public RItemBuilder(ItemStack stack)  {
        this.itemStack = stack;
        this.rItemEvent = new RItemEvent();
    }

    public RItemBuilder clone() {
        return new RItemBuilder(itemStack);
    }

    public RItemBuilder(Material type) {
        this(type, 1);
    }

    public RItemBuilder setName(String name) {
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(name);
        itemStack.setItemMeta(im);
        return this;
    }

    public String getName(){
        ItemMeta im = itemStack.getItemMeta();
        return im.getDisplayName();
    }

    public RItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) itemStack.getItemMeta();
            im.setOwner(owner);
            itemStack.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public RItemBuilder addEnchant(Enchantment ench, int level, boolean show) {
        ItemMeta im = itemStack.getItemMeta();
        im.addEnchant(ench, level, show);
        itemStack.setItemMeta(im);
        return this;
    }

    public RItemBuilder addEnchant(Enchantment ench, int level) {
        this.addEnchant(ench, level, true);
        return this;
    }

    public Map<Enchantment, Integer> getEnchants(){
        ItemMeta im = itemStack.getItemMeta();
        return im.getEnchants();
    }

    public RItemBuilder hideEnchants(){
        addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public RItemBuilder addItemFlags(ItemFlag... itemFlag){
        ItemMeta im = itemStack.getItemMeta();
        im.addItemFlags(itemFlag);
        itemStack.setItemMeta(im);
        return this;
    }

    public RItemBuilder setInfinityDurability() {
        itemStack.setDurability(Short.MAX_VALUE);
        return this;
    }

    public RItemBuilder setLore(String... lore) {
        ItemMeta im = itemStack.getItemMeta();
        im.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(im);
        return this;
    }

    public RItemBuilder setLore(List<String> lore) {
        ItemMeta im = itemStack.getItemMeta();
        im.setLore(lore);
        itemStack.setItemMeta(im);
        return this;
    }


    public List<String> getLore(){
        ItemMeta im = itemStack.getItemMeta();
        return im.getLore();
    }

    public RItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) itemStack.getItemMeta();
            im.setColor(color);
            itemStack.setItemMeta(im);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return this;
    }


    public Color getLeatherArmorColor(){
        LeatherArmorMeta im = (LeatherArmorMeta) itemStack.getItemMeta();
        return im.getColor();
    }

    public RItemBuilder setCustomHead(String name) {
        ItemMeta headM = itemStack.getItemMeta();
        try {
            Field field = headM.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            Object profile = getGameProfile();
            Object propertyMap = getPropertyMap(profile);
            putTexture(propertyMap, name.getBytes());
            field.set(headM, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemStack.setItemMeta(headM);
        return this;
    }

    public RItemBuilder setColor(RItemColor rItemColor){
        itemStack.setDurability(rItemColor.getData());
        return this;
    }

    public RItemColor getColor(){
        return RItemColor.getByData(itemStack.getDurability());
    }

    public RItemUnsafe unsafe(){
        return new RItemUnsafe(this);
    }


    public ItemStack build(){
        if (this.getClass().getSuperclass().equals(RItemBuilder.class)) {
            return new RNBTItem(this.itemStack).setByteArray("RItemEvent", serialize(this.rItemEvent)).build();
        }
        return this.itemStack;
    }

     ItemStack toItemStack(){
        return this.itemStack;
    }

    private Object getGameProfile() throws Exception{
        Class<?> aClass = Class.forName("com.mojang.authlib.GameProfile");
        return aClass.getConstructor(UUID.class, String.class).newInstance(UUID.randomUUID(), null);
    }

    private Object getPropertyMap(Object o) throws Exception{
        Class<?> aClass = o.getClass();
        Method method = aClass.getMethod("getProperties");
        method.setAccessible(true);
        return method.invoke(o);
    }

    private void putTexture(Object object, byte[] bytes) throws Exception{
        Class<?> aClass = Class.forName("com.mojang.authlib.properties.Property");
        Object o = aClass.getConstructor(String.class, String.class).newInstance("textures", new String(bytes));
        object.getClass().getMethod("put", Object.class, Object.class).invoke(object,"textures", o);
    }

    private byte[] serialize(Object o){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[]{};
    }

    protected void setPlayerInteractEvent(RConsumer<PlayerInteractEvent> event){ this.rItemEvent.setPlayerInteractEvent(event); }

    protected void setPlayerDropEvent(RConsumer<PlayerDropItemEvent> event){ this.rItemEvent.setPlayerDropItemEvent(event); }

    protected void setPlayerItemBreakEvent(RConsumer<PlayerItemBreakEvent> event){ this.rItemEvent.setPlayerItemBreakEvent(event); }

    protected void setPlayerItemConsumeEvent(RConsumer<PlayerItemConsumeEvent> event){ this.rItemEvent.setPlayerItemConsumeEvent(event); }

    protected void setPlayerItemDamageEvent(RConsumer<PlayerItemDamageEvent> event){ this.rItemEvent.setPlayerItemDamageEvent(event); }

    protected void setPickupItemEvent(RConsumer<PlayerPickupItemEvent> event){ this.rItemEvent.setPlayerPickupItemEvent(event); }

    protected void setInventoryClickEvent(RConsumer<InventoryClickEvent> event){ this.rItemEvent.setInventoryClickEvent(event); }

    protected void setBlockDispenseEvent(RConsumer<BlockDispenseEvent> event){ this.rItemEvent.setBlockDispenseEvent(event); }

    protected void setEnchantItemEvent(RConsumer<EnchantItemEvent> event){ this.rItemEvent.setEnchantItemEvent(event); }

    protected void setPrepareItemEnchantEvent (RConsumer<PrepareItemEnchantEvent> event){ this.rItemEvent.setPrepareItemEnchantEvent(event); }

    protected void setItemDespawnEvent(RConsumer<ItemDespawnEvent> event){ this.rItemEvent.setItemDespawnEvent(event); }

    protected void setFurnaceBurnEvent(RConsumer<FurnaceBurnEvent> event){ this.rItemEvent.setFurnaceBurnEvent(event); }

    protected void setFurnaceSmeltEvent(RConsumer<FurnaceSmeltEvent> event){ this.rItemEvent.setFurnaceSmeltEvent(event); }

    protected void setPlayerItemHeldEvent(RConsumer<PlayerItemHeldEvent> event){ this.rItemEvent.setPlayerItemHeldEvent(event); }

    protected void setPlayerFishEvent(RConsumer<PlayerFishEvent> event){ this.rItemEvent.setPlayerFishEvent(event); }

    protected void setPotionSplashEvent(RConsumer<PotionSplashEvent> event){ this.rItemEvent.setPotionSplashEvent(event); }

    protected void setBlockBreakEvent(RConsumer<BlockBreakEvent> event){ this.rItemEvent.setBlockBreakEvent(event); }

    protected void setBlockPlaceEvent(RConsumer<BlockPlaceEvent> event){ this.rItemEvent.setBlockPlaceEvent(event); }

    protected void setPlayerSwapHandItemsEvent(RConsumer<PlayerSwapHandItemsEvent> event){ this.rItemEvent.setPlayerSwapHandItemsEvent(event); }

}
