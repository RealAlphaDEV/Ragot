package ua.realalpha.ragot.item;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RItemBuilder extends ItemStack {

    public RItemBuilder(Material type) {
        super(type);
    }

    public RItemBuilder(Material type, int amount) {
        super(type, amount);
    }

    public RItemBuilder(Material type, int amount, int damage) {
        super(type, amount, (short) damage);
    }

    public RItemBuilder(Material type, int amount, int damage, Byte data) {
        super(type, amount, (short) damage, data);
    }

    public RItemBuilder(ItemStack stack)  {
        super(stack);
    }

    public RItemBuilder clone() {
        return new RItemBuilder(this);
    }


    public RItemBuilder setName(String name) {
        ItemMeta im = this.getItemMeta();
        im.setDisplayName(name);
        this.setItemMeta(im);
        return this;
    }

    public String getName(){
        ItemMeta im = this.getItemMeta();
        return im.getDisplayName();
    }



    public RItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) this.getItemMeta();
            im.setOwner(owner);
            this.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public RItemBuilder addEnchant(Enchantment ench, int level, boolean show) {
        ItemMeta im = this.getItemMeta();
        im.addEnchant(ench, level, show);
        this.setItemMeta(im);
        return this;
    }

    public RItemBuilder addEnchant(Enchantment ench, int level) {
        this.addEnchant(ench, level, true);
        return this;
    }

    public Map<Enchantment, Integer> getEnchants(){
        ItemMeta im = this.getItemMeta();
        return im.getEnchants();
    }

    public RItemBuilder hideEnchants(){
        addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public RItemBuilder addItemFlags(ItemFlag... itemFlag){
        ItemMeta im = this.getItemMeta();
        im.addItemFlags(itemFlag);
        this.setItemMeta(im);
        return this;
    }

    public RItemBuilder setInfinityDurability() {
        this.setDurability(Short.MAX_VALUE);
        return this;
    }

    public RItemBuilder setLore(String... lore) {
        ItemMeta im = this.getItemMeta();
        im.setLore(Arrays.asList(lore));
        this.setItemMeta(im);
        return this;
    }

    public RItemBuilder setLore(List<String> lore) {
        ItemMeta im = this.getItemMeta();
        im.setLore(lore);
        this.setItemMeta(im);
        return this;
    }


    public List<String> getLore(){
        ItemMeta im = this.getItemMeta();
        return im.getLore();
    }

    public RItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) this.getItemMeta();
            im.setColor(color);
            this.setItemMeta(im);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return this;
    }


    public Color getLeatherArmorColor(){
        LeatherArmorMeta im = (LeatherArmorMeta) this.getItemMeta();
        return im.getColor();
    }

    public RItemBuilder setCustomHead(String name) {
        ItemMeta headM = this.getItemMeta();
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
        this.setItemMeta(headM);
        return this;
    }

    public RUnsafe unsafe(){
        return new RUnsafe(this);
    }

    private Object getGameProfile() throws Exception{
        Class<?> aClass = Class.forName("com.mojang.authlib.GameProfile");
        return aClass.getConstructor(UUID.class, String.class).newInstance(UUID.randomUUID(), null);
    }

    private Object getPropertyMap(Object o) throws Exception{
        Class<?> aClass = o.getClass();
        Method method = aClass.getMethod("getProperties");
        method.setAccessible(true);
        return method.invoke(o, null);
    }

    private void putTexture(Object object, byte[] bytes) throws Exception{
        Class<?> aClass = Class.forName("com.mojang.authlib.properties.Property");
        Object o = aClass.getConstructor(String.class, String.class).newInstance("textures", new String(bytes));
        object.getClass().getMethod("put", String.class, aClass).invoke(object,"textures", o);
    }
}
