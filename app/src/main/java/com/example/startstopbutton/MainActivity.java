package com.example.startstopbutton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button start,stop,restart;
    private TextView timer ;
    private int counter=0,minutes=0;
    private Thread counterThread;
    private boolean click=true;
//first update
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.buttonStart);
        stop=findViewById(R.id.buttonStop);
        timer=findViewById(R.id.textView);
        restart=findViewById(R.id.buttonRestart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click){
                    click=false;
                    counterThread=new Thread(()-> {
                        try {
                            while (true) {
                                counter++;
                                if(counter==60){
                                    counter=0;
                                    minutes++;
                                }
                                timer.setText(minutes+":"+counter);
                                Thread.sleep(1000);
                            }
                        }
                        catch (Exception e) {

                        }
                    });
                    counterThread.start();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterThread.interrupt();
                click=true;
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter=0;
                minutes=0;
                timer.setText(minutes+":"+counter);
            }
        });
    }
}