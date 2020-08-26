package ua.realalpha.ragot;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ua.realalpha.ragot.inventory.RInventoryHandler;
import ua.realalpha.ragot.inventory.RInventoryManager;
import ua.realalpha.ragot.inventory.RInventoryTask;
import ua.realalpha.ragot.item.RItemManager;
import ua.realalpha.ragot.parameter.CraftRParameterManager;
import ua.realalpha.ragot.parameter.RParameterManager;

public final class RagotApplication {

    private final CraftRParameterManager craftRParameterManager = new CraftRParameterManager();

    public RagotApplication(JavaPlugin javaPlugin) {
        final RInventoryManager rInventoryManager = new RInventoryManager();

        final PluginManager pluginManager = javaPlugin.getServer().getPluginManager();
        pluginManager.registerEvents(new RInventoryHandler(javaPlugin, rInventoryManager), javaPlugin);

        new RItemManager(javaPlugin).registerListeners();
        new RInventoryTask(rInventoryManager).runTaskTimer(javaPlugin, 0, 1);
    }

    public RParameterManager getCraftRParameterManager() {
        return craftRParameterManager;
    }

}
