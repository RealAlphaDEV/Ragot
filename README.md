# Ragot
Ragot is an api that serves you as a toolbox to use it in your plugin you have to do.

```java
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
Now in order to be able to put actions to your item you create a class that extends from RItemProvider.
```java
public class ExampleItem extends RItemProvider {

    @Override
    public RItemBuilder getRItemBuilder() {
        return new RItemBuilder(Material.STICK)
                .setName(ChatColor.GOLD + "Item")
                .setLore("Hey", "Hello");
    }
    
    @Override
    public void setInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("It works");
    }
}
```
You can put different actions such as
- setInteractEvent
- setDropEvent
- setItemBreakEvent
- setItemConsumeEvent
- setItemDamageEvent
- setPickupItemEvent

Now you have to register the class in the onEnable.
To retrieve your item you do a getItem.

```java
public class ExamplePlugin extends JavaPlugin implements Listener {

    private RItemManager rItemManager;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        RagotProvider ragotProvider = Ragot.get();

        rItemManager = ragotProvider.getRItemManager();
        rItemManager.registerItem(new ExampleItem());
        super.onEnable();
    }

    @EventHandler
    public void onJoi(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.getInventory().addItem(rItemManager.getItem(ExampleItem.class));
    }
}
```

