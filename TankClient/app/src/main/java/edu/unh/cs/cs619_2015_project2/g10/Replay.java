package edu.unh.cs.cs619_2015_project2.g10;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Replay extends AppCompatActivity {

    private int replaySpeed = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);

        Button button = (Button)findViewById(R.id.play_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReplay();
            }
        });

        Button button1 = (Button)findViewById(R.id.double_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaySpeed =2;
            }
        });

        Button button2 = (Button)findViewById(R.id.four_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaySpeed = 4;
            }
        });
    }

    /**
     * Starts the main activity.
     */
    private void startReplay( ){

    }
}
