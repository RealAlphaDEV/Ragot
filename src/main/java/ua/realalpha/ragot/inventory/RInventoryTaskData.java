package ua.realalpha.ragot.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RInventoryData {

    private final Map<UUID, Integer> runnableMap;
    public RInventoryData() {
        this.runnableMap = new HashMap<>();
    }

    public Map<UUID, Integer> getRunnableMap() {
        return runnableMap;
    }

}
