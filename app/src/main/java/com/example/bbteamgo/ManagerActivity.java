package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bbteamgo.databinding.ActivityManagerBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ManagerActivity extends AppCompatActivity {


    private ActivityManagerBinding binding;
    ManagerOrderlistFragment managerOrderlistFragment;
    ManagerReservationFragment managerReservationFragment;

    CustomerMyprofileFragment myprofileFragment;

    Manager_fragment managerFragment;
    Revenue revenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managerOrderlistFragment = new ManagerOrderlistFragment();
        managerReservationFragment = new ManagerReservationFragment();
        myprofileFragment  = new CustomerMyprofileFragment();
        revenue = new Revenue();
        managerFragment  = new Manager_fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,myprofileFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.navigation_bar);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if( itemId == R.id.table_button ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,managerFragment).commit();
                    return true;
                }
                else if(itemId == R.id.income_button ){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, revenue).commit();
                    return true;
                }
                else if(itemId== R.id.reservation_manager_button){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,managerReservationFragment).commit();
                    return true;
                } else if (itemId == R.id.order_button) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,managerOrderlistFragment).commit();
                    return true;
                } else if( itemId == R.id.myinfo_button){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,myprofileFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}