package com.example.bbteamgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.bbteamgo.databinding.ActivityMyReservationBinding;

public class MyReservationActivity extends AppCompatActivity {
    ActivityMyReservationBinding binding;
    Button moreReservationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        moreReservationBtn = findViewById(R.id.moreReservation);
        moreReservationBtn.setOnClickListener(view->{

           //여기에 item 추가하게끔 만들어주면 된다.

        }
        );




    }
}