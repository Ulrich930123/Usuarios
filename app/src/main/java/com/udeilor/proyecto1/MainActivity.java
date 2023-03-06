package com.udeilor.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this, Log_in.class);
                startActivity(i);
                finish();

            }
        };
        Timer tiempo=new Timer();
        tiempo.schedule(tarea,5000);

    }
}