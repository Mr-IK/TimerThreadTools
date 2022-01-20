package jp.mrik.timerthreadtools;

import org.bukkit.Bukkit;

public class T3Util {

    public static void async(Runnable r){
        Bukkit.getScheduler().runTaskAsynchronously(TimerThreadTools.plugin, r);
    }

    public static void async(int delay,Runnable r){
        Bukkit.getScheduler().runTaskLaterAsynchronously(TimerThreadTools.plugin, r,delay);
    }

    public static void async(int delay,int timer,Runnable r){
        Bukkit.getScheduler().runTaskTimerAsynchronously(TimerThreadTools.plugin, r,delay,timer);
    }

    public static void sync(Runnable r){
        Bukkit.getScheduler().runTask(TimerThreadTools.plugin, r);
    }

    public static void sync(int delay,Runnable r){
        Bukkit.getScheduler().runTaskLater(TimerThreadTools.plugin, r,delay);
    }

    public static void sync(int delay,int timer,Runnable r){
        Bukkit.getScheduler().runTaskTimer(TimerThreadTools.plugin, r,delay,timer);
    }
}
