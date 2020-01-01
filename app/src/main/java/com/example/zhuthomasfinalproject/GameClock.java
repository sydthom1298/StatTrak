package com.example.zhuthomasfinalproject;

import android.os.CountDownTimer;

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
    private long decrement;

    private Timer timer = new Timer( );

    private int i = 0;

    private TimerTask task = new TimerTask( ) {
        public void run( ) {
            System.out.println( "Timer went off " + i++);
        }
    };

    public GameClock( long start, long interval) {
        super();
        startTime = start;
        decrement = interval;
    }

    public void start() {
        timer.schedule(task, 0, decrement);
    }

    public void pause() {

    }

    public void reset() {

    }




}
