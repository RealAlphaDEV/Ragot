package ua.realalpha.ragot.item;

import java.io.Serializable;
import java.util.HashMap;

public final class RItemEvent implements Serializable {

    private final HashMap<Class<?>, RConsumer<?>> rConsumerHashMap;

    public RItemEvent() {
        this.rConsumerHashMap = new HashMap<>();
    }

    public void put(Class<?> aClass, RConsumer<?> rConsumer){
        this.rConsumerHashMap.put(aClass, rConsumer);
    }

    public RConsumer<?> get(Class<?> aClass){
        return this.rConsumerHashMap.getOrDefault(aClass, o -> {});
    }

}
