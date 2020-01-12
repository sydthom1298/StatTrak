package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class Instructions extends AppCompatActivity {
    private TextView instructions;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        //yourTextView.setMovementMethod(new ScrollingMovementMethod());
        instructions = findViewById(R.id.instruct_text);

        String text = "";

        try {
            InputStream is = getAssets().open("instructions.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        instructions.setText(text);
    }
}
