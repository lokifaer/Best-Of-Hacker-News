package com.example.lokifaer.bestofhackernews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oc.hnapp.android.HNQueryTask;

public class MainActivity extends AppCompatActivity
{
    HNQueryTask myTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HNArticlesAdapter myAdapter = new HNArticlesAdapter();
        recyclerView.setAdapter(myAdapter);

        myTask = new HNQueryTask(myAdapter, 80, 1);
        myTask.execute();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        myTask.cancel(true);
    }
}