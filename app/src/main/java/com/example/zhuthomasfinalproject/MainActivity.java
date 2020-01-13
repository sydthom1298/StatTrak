/*
Jessica Zhu and Sydney Thomas
January 12 2020
Window that displays the options in the main menu, allowing the user to access each via buttons.
Each button opens a new window (each has its own activity class), this window is mainly used for navigation.
 */
package com.example.zhuthomasfinalproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    static String path;
    private int season = 2020;

    //private Season s = new Season(new Team(t),2020, 2021); //create new season object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


        }
        path = getFilesDir().getPath();
        StatsManager.setDirectory(path);
        StatsManager.initStatsManager();


        /*
        StatsManager.addSeason(s);
        StatsManager.setCurrentSeason(s);*/


        /*
        if(StatsManager.getCurrentSeason().getEndYear() != season){
            season = StatsManager.getCurrentSeason().getEndYear();
        }*/


    }


    public void launchGameSetup(View v) {
        Intent i = new Intent(this, GameSetup.class);
        startActivity(i);
    }

    public void launchStatModeSelection(View v) {
        Intent i = new Intent(this, StatModeSelection.class);
        startActivity(i);
    }

    public void launchTeamManager(View v) {
        Intent i = new Intent(this, TeamManager.class);
        startActivity(i);
    }

    public void launchInstructions(View v) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }
    public void launchCredits(View v) {
        Intent i = new Intent(this, Credits.class);
        startActivity(i);
    }


}
