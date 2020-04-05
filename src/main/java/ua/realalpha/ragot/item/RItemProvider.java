package ua.realalpha.ragot.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class RItemProvider implements RItemEvent {

    private RItemBuilder RItemBuilder;
    public RItemProvider(Material m) {
        RItemBuilder RItemBuilder = new RItemBuilder(m);
        build(RItemBuilder);
        this.RItemBuilder = RItemBuilder;
    }

    public RItemProvider(ItemStack is) {
        RItemBuilder RItemBuilder = new RItemBuilder(is);
        build(RItemBuilder);
        this.RItemBuilder = RItemBuilder;
    }

    public RItemProvider(Material m, int amount) {
        RItemBuilder RItemBuilder = new RItemBuilder(m, amount);
        build(RItemBuilder);
        this.RItemBuilder = RItemBuilder;
    }

    public RItemProvider(Material m, int amount, int data) {
        RItemBuilder RItemBuilder = new RItemBuilder(m, amount, data);
        build(RItemBuilder);
        this.RItemBuilder = RItemBuilder;
    }

    protected abstract void build(RItemBuilder RItemBuilder);

    public RItemBuilder getRItemBuilder() {
        return RItemBuilder;
    }
}
