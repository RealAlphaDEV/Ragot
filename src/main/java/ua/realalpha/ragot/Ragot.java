package ua.realalpha.ragot;

import org.bukkit.plugin.java.JavaPlugin;

public class Ragot extends JavaPlugin {

    private static RagotProvider ragotProvider;
    @Override
    public void onEnable() {
        ragotProvider = new RagotConsumer(this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static RagotProvider get(){
        return ragotProvider;
    }



}
