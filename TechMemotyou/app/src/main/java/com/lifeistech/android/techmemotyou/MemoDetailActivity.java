package com.lifeistech.android.techmemotyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MemoDetailActivity extends AppCompatActivity {

    MemoDB mMemoDB;
    EditText mTitle, mMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = (EditText) findViewById(R.id.detail_title);
        mMemo = (EditText) findViewById(R.id.detail_memo);
        setMemo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (R.id.detail_save == id) {
            updateMemo();
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void setMemo() {

        Intent intent = getIntent();
        List<MemoDB> memoList = new Select().from(MemoDB.class).where("date = ?", intent.getStringExtra("date")).execute();
        mMemoDB = memoList.get(0);
        mTitle.setText(mMemoDB.title);
        mMemo.setText(mMemoDB.memo);
    }

    void updateMemo() {

        mMemoDB.title = mTitle.getText().toString();
        mMemoDB.memo = mMemo.getText().toString();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        mMemoDB.date = sdf.format(date);
        mMemoDB.save();
    }

}
