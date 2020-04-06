# Ragot
Ragot is an api that serves you as a toolbox to use it in your plugin you have to do.

```
RagotProvider ragotProvider = Ragot.get();
```

# Contents

- [RItem](#RItem) system to create items quickly and the possibility to add shares.
- [RInventory](#RInventory) system to create inventories quickly and the possibility to add shares.

# RItem

For an easy use you create your item using the RItemBuilder class.
```java
new RItemBuilder(Material.STICK)
        .setName(ChatColor.GOLD + "Item")
        .setLore("Hey", "Hello");
```

To give it to the player.
```java
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ua.realalpha.ragot.item.RItemBuilder;

public class Example implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.getInventory().addItem(
                new RItemBuilder(Material.STICK)
                        .setName(ChatColor.GOLD + "Item")
                        .setLore("Hey", "Hello"));
    }
}
```



