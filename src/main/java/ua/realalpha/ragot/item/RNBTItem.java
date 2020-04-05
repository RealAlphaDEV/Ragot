package ua.realalpha.ragot.item;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

class RNBTItem {

    private ItemStack itemStack;
    private Object craftItemStack;
    private Object nbtTagCompound;

    public RNBTItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.craftItemStack = getCraftItemStack();
        this.nbtTagCompound = getNBTTagCompound();

    }

    public String getString(String tag) {
        return (String) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "getString", new Class[]{String.class}, tag);
    }

    public RNBTItem setString(String tag, String value) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "setString", new Class[]{String.class, String.class}, tag, value);
        return this;
    }

    public RNBTItem setByte(String tag, byte b) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "setByte", new Class[]{String.class, byte.class}, tag, b);
        return this;
    }

    public byte getByte(String tag) {
        return (byte) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "getByte", new Class[]{String.class}, tag);
    }

    public RNBTItem setShort(String tag, short b) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "setShort", new Class[]{String.class, short.class}, tag, b);
        return this;
    }

    public short getShort(String tag) {
        return (short) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "getShort", new Class[]{String.class}, tag);
    }

    public RNBTItem setInt(String tag, int b) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "setInt", new Class[]{String.class, int.class}, tag, b);
        return this;
    }

    public int getInt(String tag) {
        return (short) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "getInt", new Class[]{String.class}, tag);
    }

    public RNBTItem setByteArray(String tag, byte[] b) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "setByteArray", new Class[]{String.class, byte[].class}, tag, b);
        return this;
    }

    public byte[] getByteArray(String tag) {
        return (byte[]) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "getByteArray", new Class[]{String.class}, tag);
    }

    public RNBTItem setIntArray(String tag, int[] b) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "setIntArray", new Class[]{String.class, int[].class}, tag, b);
        return this;
    }

    public int[] getIntArray(String tag) {
        return (int[]) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "getIntArray", new Class[]{String.class}, tag);
    }


    public boolean containsTag(String tag) {
        return (boolean) invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "hasKey", new Class[]{String.class}, tag);
    }

    public RNBTItem removeTag(String s) {
        invokeMethod(this.nbtTagCompound.getClass(), nbtTagCompound, "remove", new Class[]{String.class}, s);
        return this;
    }

    public ItemStack build() {
        setTag(this.nbtTagCompound);
        return (ItemStack) invokeMethod(getCraftBukkitClass("inventory.CraftItemStack"), null, "asBukkitCopy", new Class[]{this.craftItemStack.getClass()}, this.craftItemStack);
    }

    public Object getNBTTagCompound() {
        try {
            Class<?> aClass = getNMSClass("NBTTagCompound");
            Object nbtTagCompound;
            if (!hasTag()) {
                nbtTagCompound = aClass.newInstance();
            } else {
                nbtTagCompound = getTag();
            }
            return nbtTagCompound;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object getCraftItemStack() {
        return invokeMethod(getCraftBukkitClass("inventory.CraftItemStack"), null, "asNMSCopy", new Class[]{ItemStack.class}, itemStack);
    }


    private void setTag(Object object) {
        invokeMethod(craftItemStack.getClass(), craftItemStack, "setTag", new Class[]{getNMSClass("NBTTagCompound")}, object);
    }

    private Object getTag() {
        return invokeMethod(craftItemStack.getClass(), craftItemStack, "getTag");
    }

    private boolean hasTag() {
        return (boolean) invokeMethod(craftItemStack.getClass(), craftItemStack, "hasTag");
    }

    private Object invokeMethod(Class<?> aClass, Object o, String method, Class<?>[] classes, Object... objects) {
        try {
            return aClass.getMethod(method, classes).invoke(o, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object invokeMethod(Class<?> aClass, Object o, String method) {
        try {
            return aClass.getMethod(method).invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?> getNMSClass(String nmsClassString) {
        try {
            String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
            String name = "net.minecraft.server." + version + nmsClassString;
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?> getCraftBukkitClass(String nmsClassString) {
        try {
            String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
            String name = "org.bukkit.craftbukkit." + version + nmsClassString;
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
