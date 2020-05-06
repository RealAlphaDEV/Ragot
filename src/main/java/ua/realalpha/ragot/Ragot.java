package ua.realalpha.ragot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ua.realalpha.ragot.inventory.RInventoryHandler;
import ua.realalpha.ragot.inventory.RInventoryManager;
import ua.realalpha.ragot.inventory.RInventoryTask;
import ua.realalpha.ragot.item.RItemManager;

public class Ragot extends JavaPlugin {

    @Override
    public void onEnable() {
        final RInventoryManager rInventoryManager = new RInventoryManager();

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new RInventoryHandler(this, rInventoryManager), this);

        new RItemManager(this).registerListeners();
        new RInventoryTask(rInventoryManager).runTaskTimer(this, 0, 1);
        
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}
