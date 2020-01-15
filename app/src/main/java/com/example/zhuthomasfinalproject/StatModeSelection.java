/**
 * Jessica Zhu and Sydney Thomas
 * January 9 2019
 * Window that extends AppCompatActivity.
 * Directs user to view either Game or Team/Season statistics.
 * Launchpad for both Stat Summaries.
 */
package com.example.zhuthomasfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StatModeSelection extends AppCompatActivity {
    /**
     * called when StatModeSelection window starts up
     * @param savedInstanceState - used by the system
     */
    public void onCreate(final Bundle savedInstanceState){
        // setup screen from XML
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_mode_selection);
    }


    /**
     * method that launches the game statistic summary display window
     * @param v - the current window
     */
    public void launchGameSummary(View v) {
        Intent i = new Intent(this, GameStatsSummary.class);
        startActivity(i);
    }

    /**
     * method that launches the season statistic summary display window
     * @param v - the current window
     */
    public void launchSeasonSummary(View v) {
        Intent j = new Intent(this, SeasonStatsSummary.class);
        startActivity(j);
    }

    /**
     * method that launches the instructions window
     * @param v - the current window
     */
    public void launchInstructions(View v) {
        Intent k = new Intent(this, Instructions.class);
        startActivity(k);
    }
}

