/*
Jessica Zhu
January 12 2020
Window that displays the credits for the application.
 */
package com.example.zhuthomasfinalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Credits extends AppCompatActivity {
    /**
     * called when Credits window starts up
     * @param savedInstanceState - used by the system
     */
    protected void onCreate(Bundle savedInstanceState){
        //set up screen from xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
    }
}
