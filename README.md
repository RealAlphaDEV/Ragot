#Ragot
Ragot is an api that serves you as a toolbox to use it in your plugin you have to do.

```
RagotProvider ragotProvider = Ragot.get();
```

#Tools

* [RItem](#RItem) system to create items quickly and the possibility to add shares.

#RItem

For an easy use you create your item using the RItemBuilder class.
```java
import ua.realalpha.ragot.item.RItemBuilder;

new RItemBuilder(Materkial.STICK)
        .setName(ChatColor.GOLD + "Item")
        .setLore("Hey", "Hello");
```

To give it to the player.
```java
import ua.realalpha.ragot.item.RItemBuilder;

player.getInventory().addItem(
        new RItemBuilder(Material.STICK)
                .setName(ChatColor.GOLD + "Item")
                .setLore("Hey", "Hello"));
```

