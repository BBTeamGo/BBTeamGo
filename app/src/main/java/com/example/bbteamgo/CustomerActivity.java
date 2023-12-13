package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bbteamgo.databinding.ActivityCustomerBinding;
import com.google.android.material.navigation.NavigationBarView;



    public class CustomerActivity extends AppCompatActivity {
        private ActivityCustomerBinding binding;
        CusotmerHomeFragment cusotmerHomeFragment;
        CusotmerReservationFragment cusotmerReservationFragment;
        CustomerMyprofileFragment customerMyprofileFragment;
        CustomerOrderFragment orderfragment;
//        CustomerOrderScreenFragment customerOrderScreenFragment;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityCustomerBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            cusotmerHomeFragment = new CusotmerHomeFragment();
            cusotmerReservationFragment = new CusotmerReservationFragment();
            customerMyprofileFragment = new CustomerMyprofileFragment();
            orderfragment = new CustomerOrderFragment();
//            customerOrderScreenFragment = new CustomerOrderScreenFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,cusotmerHomeFragment).commit();

            NavigationBarView navigationBarView = findViewById(R.id.navigation_bar);

            navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();

                    if( itemId == R.id.home_btn ) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, cusotmerHomeFragment).commit();
                        return true;
                    }
                    else if(itemId == R.id.reservation_button ){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, cusotmerReservationFragment).commit();
                        return true;
                    }
                    else if(itemId== R.id.my_order_button){
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,customerOrderScreenFragment).commit();
                        return true;
                    }
                    else if( itemId == R.id.mypage){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,customerMyprofileFragment).commit();
                        return true;
                    }


                    return false;
                }
            });


        }

}