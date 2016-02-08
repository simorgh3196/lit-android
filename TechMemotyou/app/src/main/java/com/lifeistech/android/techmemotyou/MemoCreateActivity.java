package com.lifeistech.android.techmemotyou;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class MemoCreateActivity extends AppCompatActivity {

    MemoDB mMemoDB;
    EditText mTitle, mMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = (EditText) findViewById(R.id.create_title);
        mMemo = (EditText) findViewById(R.id.create_memo);
        mMemoDB = new MemoDB();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Saveのメニューが押された時
        if (R.id.create_save == id) {
            saveMemo();
            finish();   // Activityの終了

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memo_create, menu);
        return true;
    }

    void saveMemo() {

        mMemoDB.title = mTitle.getText().toString();
        mMemoDB.memo = mMemo.getText().toString();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        mMemoDB.date = sdf.format(date);
        mMemoDB.save();
    }
}
