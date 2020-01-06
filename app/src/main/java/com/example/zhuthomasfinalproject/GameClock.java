package com.example.zhuthomasfinalproject;
import java.util.Timer;
import java.util.TimerTask;
public class GameClock {

    private long startTime;
    private long currentTime = 0;
    private long decrement;
    private boolean running = false;
    private Timer timer = new Timer( );

    private int t = 1;


    private TimerTask task;


    public GameClock(){

    }
    /**
     * constructor that constructs a GameClock class with parameters and initializes attributes
     * @param start - time remaining in quarter
     * @param interval - how often timer is updated (1s)
     */
    public GameClock(long start, long interval) {
        startTime = start;
        currentTime = start;
        decrement = interval;
    }
    /**
     * method that sets up a task that is called every one second as the timer goes off
     */
    private void createTask() {
        task = new TimerTask() {
            public void run() {
                if(running){
                    System.out.println("Timer went off " + t++);
                    currentTime--;
                }
            }
        };
    }

    /**
     * method that starts the clock running
     */
    public void start() {
        //create task that gets called every 1 second
        createTask();
        //schedules a timer to go off every decrement milliseconds
        timer.schedule(task, 0, decrement);
        //clock is running
        running = true;
    }

    /**
     * method that pauses the clock
     */
    public void pause() {
        //cancel the timer task
        task.cancel();
        running = false;
        System.out.println("Paused");
    }

    /**
     * method that resets the clock
     */
    public void reset() {
        t = 1;
    }

    /**
     * accessor that gets the start time of game clock
     * @return - start time
     */
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long sT) {
        startTime = sT;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long cT) {
        currentTime = cT;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean r) {
        running = r;
    }
}
