package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class HistoryListActivity extends AppCompatActivity {
    private LinkedList<ClientUtil> client = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        client.add(new ClientUtil(R.drawable.outline_construction_black_24, "Maconnerie", 10));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        HistoryListAdapter adapter = new HistoryListAdapter(this,client);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}