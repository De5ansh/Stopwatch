package com.adonis.stopwatch;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Loc

public class MainActivity extends Activity {

    private int seconds=0;
    private boolean running;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null) {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstsancestate) {
        savedInstsancestate.putInt("seconds",seconds);
        savedInstsancestate.putBoolean("running",running);
        savedInstsancestate.putBoolean("wasrunning",wasrunning);
    }
    @Override
//    public void onStop() {
//        super.onStop();
//        wasrunning=running;
//        running=false;
//    }
    public void onPause() {
        super.onPause();
        wasrunning=running;
        running=false;
    }
    @Override
//    public void onStart() {
//        super.onStart();
//        if (wasrunning) {
//            running=true;
//        }
//    }
    public void onResume() {
        super.onResume();
        if (wasrunning) {
            running=true;
        }
    }
    public void onClickStart(View view) {
        running=true;
    }
    public void onClickStop(View view) {
        running=false;
    }
    public void onClickReset(View view) {
        running=false;
        seconds=0;
    }
    public void runTimer() {
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}
