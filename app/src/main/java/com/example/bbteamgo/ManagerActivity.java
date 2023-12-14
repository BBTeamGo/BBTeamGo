package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class ManagerActivity extends AppCompatActivity {
    private static final String TAG = "ManagerActivity";
    String booth = null;
    String university = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Intent intent = getIntent();
        university = intent.getStringExtra("UNIVERSITY_NAME");
        booth = intent.getStringExtra("BOOTH_NAME");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view_tag,
                        ManagerTableFragment.newInstance(university, booth))
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
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view_tag,
                                    ManagerReservationFragment.newInstance(university, booth))
                            .commit();
                    return true;
                } else if (itemId == R.id.order_menu) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view_tag,
                                    CustomerOrderFragment.newInstance(university, booth))
                            .commit();
                    return true;
                } else if( itemId == R.id.myinfo_menu){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view_tag,
                                    new CustomerMyprofileFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }
}