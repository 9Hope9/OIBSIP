package com.example.stop_watch_internship;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;


    private Handler handler = new Handler();
    private long startTime = 0L, timeInMillis = 0L, timeSwapBuff = 0L, updateTime = 0L;

    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvTimer = findViewById(R.id.tvTimer);
        ImageButton btnStart = findViewById(R.id.btnStart);
        ImageButton btnHold = findViewById(R.id.btnHold);
        ImageButton btnStop = findViewById(R.id.btnStop);

        // Start Button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    isRunning = true;
                }
                else{
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    isRunning = true;
                }
            }
        });

        // Pause Button
        btnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    timeSwapBuff += timeInMillis;
                    handler.removeCallbacks(runnable);
                    isRunning = false;
                } else if (isRunning) {
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    isRunning = true;

                } else  {
                        startTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                        isRunning = true;
                }
            }
        });

        // Stop Button (reset)
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = 0L;
                timeInMillis = 0L;
                timeSwapBuff = 0L;
                updateTime = 0L;
                handler.removeCallbacks(runnable);
                isRunning = false;
                tvTimer.setText("00:00:00");
            }
        });
    }

    // Runnable to update time
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeInMillis = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMillis;

            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            int hrs = mins / 60;
            secs = secs % 60;
            mins = mins % 60;

            tvTimer.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
            handler.postDelayed(this, 1000);
        }
    };

}
