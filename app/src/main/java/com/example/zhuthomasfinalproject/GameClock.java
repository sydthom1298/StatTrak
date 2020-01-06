package com.example.zhuthomasfinalproject;

import java.util.Timer;
import java.util.TimerTask;

/*
new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                textView.setText("FINISH!!");
            }
        }.start();
 */

public class GameClock {

    private long startTime;
    private long currentTime = 0;
    private long decrement;
    private boolean running = false;
    private Timer timer = new Timer( );

    private int t = 1;


    private TimerTask task;

     private void createTask() {
         task = new TimerTask() {
             public void run() {
                 if (running) {
                     System.out.println("Timer went off " + t++);
                     currentTime--; 
                 }
             }
         };
     }

    public GameClock( long start, long interval) {
        startTime = start;
        currentTime = start;
        decrement = interval;
    }

    public void start() {
        createTask();
        timer.schedule(task, 0, decrement);
        running = true;
    }

    public void pause() {
        task.cancel();
        running = false;
        System.out.println("Paused");

    }

    public void reset() {
        t = 1;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
