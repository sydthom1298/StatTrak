/*
Jessica Zhu
January 9 2019
Class that...
 */
package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameStatsSummary extends AppCompatActivity {
    private TextView statsDisplay;

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_stats);
    }
}
