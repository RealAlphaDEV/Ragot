package ua.realalpha.ragot.item;

import org.bukkit.event.player.*;

public interface RItemEvent {

    default void setInteractEvent(PlayerInteractEvent event){}

    default void setDropEvent(PlayerDropItemEvent event){}

    default void setItemBreakEvent(PlayerItemBreakEvent event){}

    default void setItemConsumeEvent(PlayerItemConsumeEvent event){ }

    default void setItemDamageEvent(PlayerItemDamageEvent event){}

    default void setPickupItemEvent(PlayerPickupItemEvent event){ }

}
