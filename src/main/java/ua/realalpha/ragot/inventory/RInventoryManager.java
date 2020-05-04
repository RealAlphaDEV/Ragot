package ua.realalpha.ragot.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RInventoryManager {

    private final Map<RInventory, RInventoryTaskData> map;

    public RInventoryManager() {
        this.map = new HashMap<>();
    }

    public RInventoryTaskData put(RInventory rInventory){
        return this.map.put(rInventory, new RInventoryTaskData());
    }

    public RInventoryTaskData get(RInventory rInventory){
        if (this.map.containsKey(rInventory)) return this.map.get(rInventory);
        return put(rInventory);
    }

    public void remove(RInventory rInventory){
        this.map.remove(rInventory);
    }

    public Set<RInventory> get(){
        return map.keySet();
    }
}
