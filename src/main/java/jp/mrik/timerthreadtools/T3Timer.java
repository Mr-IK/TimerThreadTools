package jp.mrik.timerthreadtools;

import java.util.HashMap;

public class T3Timer {

    public static T3Timer create(){
        return new T3Timer(true);
    }

    public static T3Timer create(boolean isAsync){
        return new T3Timer(isAsync);
    }


    private HashMap<Integer,Runnable> timerTasks = new HashMap<>();


    private final boolean isAsync;

    private T3Timer(boolean isAsync){
        this.isAsync = isAsync;
    }

    public void putTask(int delay,Runnable r){
        timerTasks.put(delay,r);
    }

    public boolean containTask(int delay){
        return timerTasks.containsKey(delay);
    }

    public void start(){
        for(int delay : timerTasks.keySet()){
            Runnable r = timerTasks.get(delay);

            if (isAsync) {
                T3Util.async(delay, r);
            } else {
                T3Util.sync(delay, r);
            }

        }
    }
}
