package com.example.bbteamgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ManagerSelectBoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_select_booth);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        List<BoothItem> booths = new ArrayList<BoothItem>();

        booths.add(new BoothItem("a", "제목", "설명", 10, 1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BoothAdapter(getApplicationContext(), booths));
    }
}