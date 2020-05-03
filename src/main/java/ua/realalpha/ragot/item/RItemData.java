package ua.realalpha.ragot.item;

import org.bukkit.event.Event;

import java.io.Serializable;
import java.util.HashMap;

public final class RItemData implements Serializable {

    private final HashMap<Class<? extends Event>, RConsumer<? extends Event>> rConsumerHashMap;

    public RItemData() {
        this.rConsumerHashMap = new HashMap<>();
    }

    public void put(Class<? extends Event> aClass, RConsumer<? extends Event> rConsumer){
        this.rConsumerHashMap.put(aClass, rConsumer);
    }

    public RConsumer<? extends Event> get(Class<? extends Event> aClass){
        return this.rConsumerHashMap.getOrDefault(aClass, o -> {});
    }

}
