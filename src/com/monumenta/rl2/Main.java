package com.monumenta.rl2;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main PluginInstance;

    public void onEnable() {
        PluginInstance = this;
        this.getCommand("roguelite").setExecutor(new RL2Command(this));
    }
    public void onDisable() {

    }

    public static Main getInstance() {
        return PluginInstance;
    }

}
