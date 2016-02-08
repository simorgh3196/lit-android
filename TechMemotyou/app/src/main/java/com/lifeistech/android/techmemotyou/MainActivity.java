package com.lifeistech.android.techmemotyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.memo_list);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MemoDB data = (MemoDB) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, MemoDetailActivity.class);
                intent.putExtra("date", data.date);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setMemoList();
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

        if (R.id.main_create == id) {
            Intent i = new Intent(MainActivity.this, MemoCreateActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    void setMemoList() {

        List<MemoDB> memoList = new Select().from(MemoDB.class).execute();
        ArrayAdapter<MemoDB> adapter = new ArrayAdapter<MemoDB>(
                getApplicationContext(),
                R.layout.memo_row,
                memoList
        );
        mListView.setAdapter(adapter);
    }
}
