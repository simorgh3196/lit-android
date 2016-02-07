package com.lifeistech.android.tapnumber;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] array;
    String question;
    int score;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        start();
    }

    public void start() {

        array = new int[4];
        Random rand = new Random();
        array[0] = rand.nextInt(4) + 1;
        array[1] = rand.nextInt(4) + 1;
        array[2] = rand.nextInt(4) + 1;
        array[3] = rand.nextInt(4) + 1;

        question = Integer.toString(array[0])
                + Integer.toString(array[1])
                + Integer.toString(array[2])
                + Integer.toString(array[3]);
        textView.setText(question);
        score = 0;
    }

    private void deleteNumber(int num) {

        if(array[score] == num) {
            question = question.substring(1);
            textView.setText(question);
            score++;

            if(score == 4) {
                start();
            }
        }else {
            Toast.makeText(this, "数字が違うよ！", Toast.LENGTH_SHORT).show();
        }
    }

    public void number1(View v) {

        deleteNumber(1);
    }

    public void number2(View v) {

        deleteNumber(2);
    }

    public void number3(View v) {

        deleteNumber(3);
    }

    public void number4(View v) {

        deleteNumber(4);
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
