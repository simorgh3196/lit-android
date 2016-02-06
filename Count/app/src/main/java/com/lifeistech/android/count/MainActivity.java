package com.lifeistech.android.count;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textView);
        text.setText("0");
    }

    private void setTextColor() {

        if(number >= 10) {
            text.setTextColor(Color.parseColor("#FF0000"));
        }else if(number <= -10) {
            text.setTextColor(Color.parseColor("#0000FF"));
        }else {
            text.setTextColor(Color.parseColor("#000000"));
        }
    }

    public void plus(View v) {

        number = number + 1;
        text.setText(String.valueOf(number));

        setTextColor();
    }

    public void minus(View v) {

        number = number - 1;
        text.setText(String.valueOf(number));

        setTextColor();
    }

    public void mul(View v) {

        number = number * 2;
        text.setText(String.valueOf(number));

        setTextColor();
    }

    public void div(View v) {

        number = number / 2;
        text.setText(String.valueOf(number));

        setTextColor();
    }

    public void clear(View v) {

        number = 0;
        text.setText(String.valueOf(number));
        text.setTextColor(Color.parseColor("#000000"));
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
