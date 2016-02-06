package com.lifeistech.android.calculator;

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

    int num1, num2, ans;
    int ope;
    TextView num1Text, num2Text, opeText, ansText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1Text = (TextView) findViewById(R.id.num1);
        num2Text = (TextView) findViewById(R.id.num2);
        opeText = (TextView) findViewById(R.id.ope);
        ansText = (TextView) findViewById(R.id.answer);
        num1Text.setText(" _ ");
        num2Text.setText(" _ ");
        opeText.setText("・");
        ansText.setText("= _ ");

        ope = 0;
    }

    private void setNumber(int num) {

        if(ope < 0) {
            num1 = 0;
            num2 = 0;
            num2Text.setText(" _ ");
            ansText.setText("= _ ");
            ope = 0;
        }

        if(ope == 0) {
            num1 = num1 * 10 + num;
            num1Text.setText(String.valueOf(num1));

        }else {
            num2 = num2 * 10 + num;
            num2Text.setText(String.valueOf(num2));
        }
    }

    public void bt0(View v) {

        setNumber(0);
    }

    public void bt1(View v) {

        setNumber(1);
    }

    public void bt2(View v) {

        setNumber(2);
    }

    public void bt3(View v) {

        setNumber(3);
    }

    public void bt4(View v) {

        setNumber(4);
    }

    public void bt5(View v) {

        setNumber(5);
    }

    public void bt6(View v) {

        setNumber(6);
    }

    public void bt7(View v) {

        setNumber(7);
    }

    public void bt8(View v) {

        setNumber(8);
    }

    public void bt9(View v) {

        setNumber(9);
    }

    public void btequal(View v) {

        ans = 0;

        if(ope == 1) {
            ans = num1 + num2;
        }else if(ope == 2) {
            ans = num1 - num2;
        }else if(ope == 3) {
            ans = num1 * num2;
        }

        ope = -1;
        ansText.setText(" = " + String.valueOf(ans));
    }

    public void btplus(View v) {

        ope = 1;
        opeText.setText(" + ");
    }

    public void btmul(View v) {

        ope = 3;
        opeText.setText(" × ");
    }

    public void btminus(View v) {

        ope = 2;
        opeText.setText(" - ");
    }

    public void btclear(View v) {

        ope = 0;
        num1 = 0;
        num2 = 0;
        num1Text.setText(" _ ");
        num2Text.setText(" _ ");
        ansText.setText("= _ ");
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
