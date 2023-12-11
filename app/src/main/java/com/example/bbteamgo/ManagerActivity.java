package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bbteamgo.databinding.ActivityManagerBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ManagerActivity extends AppCompatActivity {
    String university;
    String booth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Intent intent = getIntent();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view_tag, ManagerTableFragment.newInstance(university, booth))
                .commit();

        NavigationBarView navigationBarView = findViewById(R.id.navigation_bar);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if( itemId == R.id.table_menu ) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view_tag, ManagerTableFragment.newInstance(university, booth))
                            .commit();
                    return true;
                }
                else if(itemId == R.id.income_menu){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view_tag,
                                    ManagerIncomeFragment.newInstance(university, booth))
                            .commit();
                    return true;
                }
                else if(itemId== R.id.reservation_menu){
                    return true;
                } else if (itemId == R.id.order_menu) {
                    return true;
                } else if( itemId == R.id.myinfo_menu){
                    return true;
                }
                return false;
            }
        });
    }
}