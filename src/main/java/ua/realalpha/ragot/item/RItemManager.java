package ua.realalpha.ragot.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import ua.realalpha.ragot.Ragot;

import java.util.HashMap;
import java.util.Map;

public class RItemManager {

    private Map<Class<? extends RItemProvider>, RItemProvider> map;
    public RItemManager(Ragot ragot) {
        this.map = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new RItemHandler(this), ragot);
    }

    public void registerItem(RItemProvider itemProvider){
        this.map.put(itemProvider.getClass(), itemProvider);
    }

    public RItemBuilder getItem(Class<? extends RItemProvider> aClass)  {
            return this.map.getOrDefault(aClass, new DefaultRItem(Material.STONE)).getRItemBuilder().unsafe().setString("RItemProvider", aClass.getName()).toItemBuilder();
    }

    public RItemProvider getItemProvider(Class<? extends RItemProvider> aClass)  {
        return this.map.getOrDefault(aClass, new DefaultRItem(Material.STONE));
    }

    private class DefaultRItem extends RItemProvider {
        public DefaultRItem(Material m) {
            super(m);
        }

        @Override
        public void build(RItemBuilder RItemBuilder) {

        }
    }
}
