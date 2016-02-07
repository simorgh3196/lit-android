package com.lifeistech.android.tapamole;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView scoreText, timeText;

    int[] imageResources = {
            R.id.imageView1, R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9,
            R.id.imageView10, R.id.imageView11, R.id.imageView12
    };

    Mole[] moles;

    int time, score;

    Timer timer;
    TimerTask timerTask;
    Handler h;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = (TextView) findViewById(R.id.scoreText);
        timeText = (TextView) findViewById(R.id.timeText);

        moles = new Mole[12];
        for(int i = 0; i < 12; i++) {
            ImageView imageView = (ImageView) findViewById(imageResources[i]);
            moles[i] = new Mole(imageView);
        }

        h = new Handler();
    }

    public void start(View v) {

        time = 60;
        score = 0;
        timeText.setText(String.valueOf(time));
        scoreText.setText(String.valueOf(score));

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // TODO 一定時間ごとに行う処理を書く
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        int r = random.nextInt(12);
                        moles[r].start();   // r番目のモグラが飛び出す

                        time--;
                        timeText.setText(String.valueOf(time));

                        if(time <= 0) {
                            timer.cancel(); //timer 終了
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void tapMole(View v) {
        String tag_str = (String) v.getTag();   // tagを取得
        int tag_int = Integer.valueOf(tag_str); // intへ変換

        score += moles[tag_int].tapMole();
        scoreText.setText(String.valueOf(score));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
