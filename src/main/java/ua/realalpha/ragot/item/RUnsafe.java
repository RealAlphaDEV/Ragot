package ua.realalpha.ragot.item;

public class RUnsafe {

    private RItemBuilder RItemBuilder;
    public RUnsafe(RItemBuilder RItemBuilder) {
        this.RItemBuilder = RItemBuilder;
    }

    public String getString(String tag){
        return new RNBTItem(this.RItemBuilder).getString(tag);
    }

    public RUnsafe setString(String tag, String value){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setString(tag, value).build());
        return this;
    }

    public RUnsafe setByte(String tag, byte b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setByte(tag, b).build());
        return this;
    }

    public byte getByte(String tag){
        return new RNBTItem(this.RItemBuilder).getByte(tag);
    }

    public RUnsafe setShort(String tag, short b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setShort(tag, b).build());
        return this;
    }

    public short getShort(String tag){
        return new RNBTItem(this.RItemBuilder).getShort(tag);
    }

    public RUnsafe setInt(String tag, int b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setInt(tag, b).build());
        return this;
    }

    public int getInt(String tag){
        return new RNBTItem(this.RItemBuilder).getInt(tag);
    }

    public RUnsafe setByteArray(String tag, byte[] b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setByteArray(tag, b).build());
        return this;
    }

    public byte[] getByteArray(String tag){
        return new RNBTItem(this.RItemBuilder).getByteArray(tag);
    }

    public RUnsafe setIntArray(String tag, int[] b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setIntArray(tag, b).build());
        return this;
    }

    public int[] getIntArray(String tag){
        return new RNBTItem(this.RItemBuilder).getIntArray(tag);
    }

    public boolean containsTag(String s){
        return new RNBTItem(this.RItemBuilder).containsTag(s);
    }

    public RUnsafe remove(String tag){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).removeTag(tag).build());
        return this;
    }

    public RItemBuilder toItemBuilder(){
        return this.RItemBuilder;
    }
}
