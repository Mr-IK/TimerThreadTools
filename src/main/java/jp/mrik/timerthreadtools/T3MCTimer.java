package jp.mrik.timerthreadtools;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class T3MCTimer {

    public static T3MCTimer create(boolean isAsync,String worldName){
        return new T3MCTimer(isAsync,worldName);
    }

    /*
    start時点から時間経過を確認し続け、該当する日付・時間に特定の処理を実行する
     */

    private final String worldName;
    private final boolean isAsync;

    private long lowTime = 25000;
    private int day = 0;

    private HashMap<Integer,HashMap<Integer,Runnable>> taskMap = new HashMap<>();

    private BukkitTask runningTask = null;

    private T3MCTimer(boolean isAsync,String worldName){
        this.isAsync = isAsync;
        this.worldName = worldName;
    }

    public void putTask(int day,int time,Runnable r){
        HashMap<Integer,Runnable> map;
        if(taskMap.containsKey(day)){
             map = taskMap.get(day);
        }else{
            map = new HashMap<>();
        }
        map.put(time,r);
        taskMap.put(day,map);
    }

    public boolean containTask(int day,int time){
        if(taskMap.containsKey(day)){
            return taskMap.get(day).containsKey(time);
        }
        return false;
    }

    public void start(){
        if(isAsync){
            runningTask =  new BukkitRunnable() {
                @Override
                public void run() {
                    long time = Bukkit.getWorld(worldName).getTime();
                    if(lowTime>time) {
                        day++;

                        if(taskMap.containsKey(day-1)){
                            for(int tarTime : taskMap.get(day-1).keySet()){
                                if(lowTime<tarTime&&tarTime<25000){
                                    taskMap.get(day-1).get(tarTime).run();
                                }
                            }
                        }

                        if(taskMap.containsKey(day)){
                            for(int tarTime : taskMap.get(day).keySet()){
                                if(-1<tarTime&&tarTime<time){
                                    taskMap.get(day).get(tarTime).run();
                                }
                            }
                        }
                    }else{
                        if(taskMap.containsKey(day)){
                            for(int tarTime : taskMap.get(day).keySet()){
                                if(lowTime<tarTime&&tarTime<time){
                                    taskMap.get(day).get(tarTime).run();
                                }
                            }
                        }
                    }
                    lowTime = time;
                }
            }.runTaskTimerAsynchronously(TimerThreadTools.plugin,60,60);
        }else{
            runningTask =  new BukkitRunnable() {
                @Override
                public void run() {

                }
            }.runTaskTimer(TimerThreadTools.plugin,60,60);
        }
    }

    public void cancel(){
        if(runningTask!=null&&!runningTask.isCancelled()){
            runningTask.cancel();
            runningTask = null;
        }
    }

}
