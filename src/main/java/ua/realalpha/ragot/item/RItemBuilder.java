package ua.realalpha.ragot.item;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.Potion;
import ua.realalpha.ragot.utils.Reflections;
import ua.realalpha.ragot.version.Version;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class RItemBuilder implements Serializable {

    private transient final ItemStack itemStack;
    private final RItemData rItemData;
    public RItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    public RItemBuilder(Material type, int amount, int data) {
        this(new ItemStack(type, amount, (short) data));
    }

    public RItemBuilder(ItemStack stack)  {
        this.itemStack = stack;
        this.rItemData = new RItemData();
    }

    public RItemBuilder(Potion potion, int amount){
        this.itemStack = potion.toItemStack(amount);
        this.rItemData = new RItemData();
    }

    public RItemBuilder(Potion potion){
        this(potion, 1);
    }

    public RItemBuilder clone() {
        return new RItemBuilder(itemStack);
    }

    public RItemBuilder(Material type) {
        this(type, 1);
    }

    public RItemBuilder setBannerMeta(Consumer<BannerMeta> bannerMeta){
        BannerMeta itemMeta = (BannerMeta) itemStack.getItemMeta();
        bannerMeta.accept(itemMeta);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RItemBuilder setPotionMeta(Consumer<PotionMeta> potionMeta){
        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.accept(itemMeta);
        this.itemStack.setItemMeta(itemMeta);
        return this;
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

    public RItemBuilder setSkullOwner(UUID uuid) {
        SkullMeta im = (SkullMeta) itemStack.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (Version.getServerVersion().getVersion() < 13) {
            im.setOwner(offlinePlayer.getName());
        }else {
            Reflections.callMethod(im, "setOwningPlayer", offlinePlayer);
        }
        itemStack.setItemMeta(im);
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

    public RItemBuilder addItemFlags(){
        ItemMeta im = itemStack.getItemMeta();
        im.addItemFlags(ItemFlag.values());
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
        if (Version.getServerVersion().getVersion() < 13){
            itemStack.setDurability(rItemColor.getData());
            return this;
        }
        itemStack.setType(Material.valueOf(rItemColor.toString()+"_"+this.itemStack.getType().toString()));
        return this;
    }

    public RItemColor getColor(){
        if (Version.getServerVersion().getVersion() < 13) return RItemColor.getByData(this.itemStack.getDurability());
        String[] strings = this.itemStack.getType().toString().split("_");
        return RItemColor.valueOf(strings[0]);
    }

    public final RItemUnsafe unsafe(){
        return new RItemUnsafe(this);
    }

    public final ItemStack build(){
        if (this.getClass().getSuperclass().equals(RItemBuilder.class)) {
            return new RNBTItem(this.itemStack).setByteArray("RItemEvent", serialize(this.rItemData)).build();
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

    protected final void setEvent(RItemEvent rItemEvent, RConsumer<? extends Event> rConsumer){
        try {
            this.rItemData.put(rItemEvent.getClazz(), rConsumer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
