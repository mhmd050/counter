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
    private Button start,stop;
    private TextView timer ;
    private int counter=0;
    private Thread counterThread;
//first update
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.buttonStart);
        stop=findViewById(R.id.buttonStop);
        timer=findViewById(R.id.textView);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterThread=new Thread(()-> {
                    try {
                        while (true) {
                            counter++;
                            timer.setText(counter+"");
                            Thread.sleep(1000);
                        }
                    }
                    catch (Exception e) {

                    }
                });
                counterThread.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterThread.interrupt();
            }
        });
    }
}