package ua.realalpha.ragot.item;

public class RUnsafe {

    private RItemBuilder RItemBuilder;
    public RUnsafe(RItemBuilder RItemBuilder) {
        this.RItemBuilder = RItemBuilder;
    }

    public String getString(String tag){
        return new RNBTItem(this.RItemBuilder.toItemStack()).getString(tag);
    }

    public RUnsafe setString(String tag, String value){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).setString(tag, value).build());
        return this;
    }

    public RUnsafe setByte(String tag, byte b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).setByte(tag, b).build());
        return this;
    }

    public byte getByte(String tag){
        return new RNBTItem(this.RItemBuilder.toItemStack()).getByte(tag);
    }

    public RUnsafe setShort(String tag, short b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).setShort(tag, b).build());
        return this;
    }

    public short getShort(String tag){
        return new RNBTItem(this.RItemBuilder.toItemStack()).getShort(tag);
    }

    public RUnsafe setInt(String tag, int b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).setInt(tag, b).build());
        return this;
    }

    public int getInt(String tag){
        return new RNBTItem(this.RItemBuilder.toItemStack()).getInt(tag);
    }

    public RUnsafe setByteArray(String tag, byte[] b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).setByteArray(tag, b).build());
        return this;
    }

    public byte[] getByteArray(String tag){
        return new RNBTItem(this.RItemBuilder.toItemStack()).getByteArray(tag);
    }

    public RUnsafe setIntArray(String tag, int[] b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).setIntArray(tag, b).build());
        return this;
    }

    public int[] getIntArray(String tag){
        return new RNBTItem(this.RItemBuilder.toItemStack()).getIntArray(tag);
    }

    public boolean containsTag(String s){
        return new RNBTItem(this.RItemBuilder.toItemStack()).containsTag(s);
    }

    public RUnsafe remove(String tag){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder.toItemStack()).removeTag(tag).build());
        return this;
    }

    public RItemBuilder toItemBuilder(){
        return this.RItemBuilder;
    }
}
