package jp.mrik.timerthreadtools;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class T3Task {

    public static T3Task create(boolean isAsync,BukkitRunnable runTask){
        return new T3Task(isAsync,-1,-1,runTask);
    }

    public static T3Task create(boolean isAsync,int delay,BukkitRunnable runTask){
        return new T3Task(isAsync, delay,-1,runTask);
    }

    public static T3Task create(boolean isAsync,int delay, int timer,BukkitRunnable runTask){
        return new T3Task(isAsync, delay, timer, runTask);
    }

    private final BukkitRunnable runTask;
    private final boolean isAsync;
    private final int delay;
    private final int timer;

    private BukkitTask runningTask = null;

    private T3Task(boolean isAsync,int delay,int timer,BukkitRunnable runTask){
        this.isAsync = isAsync;
        this.runTask = runTask;
        this.delay = delay;
        this.timer = timer;
    }

    public void start(){
        if(delay==-1&&timer==-1){
            if(isAsync){
                runningTask = runTask.runTaskAsynchronously(TimerThreadTools.plugin);
            }else{
                runningTask = runTask.runTask(TimerThreadTools.plugin);
            }
        }else if(delay!=-1&&timer==-1){
            if(isAsync){
                runningTask = runTask.runTaskLaterAsynchronously(TimerThreadTools.plugin,delay);
            }else{
                runningTask = runTask.runTaskLater(TimerThreadTools.plugin,delay);
            }
        }else{
            if(isAsync){
                runningTask = runTask.runTaskTimerAsynchronously(TimerThreadTools.plugin,delay,timer);
            }else{
                runningTask = runTask.runTaskTimer(TimerThreadTools.plugin,delay,timer);
            }
        }
    }

    public void cancel(){
        if(runningTask!=null&&!runningTask.isCancelled()){
            runningTask.cancel();
            runningTask = null;
        }
    }
}
