package jp.mrik.timerthreadtools;

import org.bukkit.plugin.java.JavaPlugin;

public final class TimerThreadTools extends JavaPlugin {

    static TimerThreadTools plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
