package ua.realalpha.ragot.item;

public class RItemUnsafe {

    private RItemBuilder RItemBuilder;
    public RItemUnsafe(RItemBuilder RItemBuilder) {
        this.RItemBuilder = RItemBuilder;
    }

    public String getString(String tag){
        return new RNBTItem(this.RItemBuilder).getString(tag);
    }

    public RItemUnsafe setString(String tag, String value){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setString(tag, value).build());
        return this;
    }

    public RItemUnsafe setByte(String tag, byte b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setByte(tag, b).build());
        return this;
    }

    public byte getByte(String tag){
        return new RNBTItem(this.RItemBuilder).getByte(tag);
    }

    public RItemUnsafe setShort(String tag, short b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setShort(tag, b).build());
        return this;
    }

    public short getShort(String tag){
        return new RNBTItem(this.RItemBuilder).getShort(tag);
    }

    public RItemUnsafe setInt(String tag, int b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setInt(tag, b).build());
        return this;
    }

    public int getInt(String tag){
        return new RNBTItem(this.RItemBuilder).getInt(tag);
    }

    public RItemUnsafe setByteArray(String tag, byte[] b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setByteArray(tag, b).build());
        return this;
    }

    public byte[] getByteArray(String tag){
        return new RNBTItem(this.RItemBuilder).getByteArray(tag);
    }

    public RItemUnsafe setIntArray(String tag, int[] b){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).setIntArray(tag, b).build());
        return this;
    }

    public int[] getIntArray(String tag){
        return new RNBTItem(this.RItemBuilder).getIntArray(tag);
    }

    public boolean containsTag(String s){
        return new RNBTItem(this.RItemBuilder).containsTag(s);
    }

    public RItemUnsafe remove(String tag){
        this.RItemBuilder = new RItemBuilder(new RNBTItem(this.RItemBuilder).removeTag(tag).build());
        return this;
    }

    public RItemBuilder toItemBuilder(){
        return this.RItemBuilder;
    }
}
