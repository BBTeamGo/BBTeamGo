package com.example.bbteamgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bbteamgo.databinding.ActivityBookingManagementPageBinding;

import java.util.ArrayList;
import java.util.List;

public class BookingManagementPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookingManagementPageBinding binding = ActivityBookingManagementPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_booking_management_page);

        //RecyclerView recyclerView = findViewById(R.id.booking_management_recyclerview);

        List<BookingManagementData> data = new ArrayList<BookingManagementData>();
        data.add(new BookingManagementData(1, "조민혁", "20분 30초"));
        data.add(new BookingManagementData(2, "공민기", "15분 21초"));
        data.add(new BookingManagementData(3, "이종은", "12분 30초"));


        /*추가하고 싶은 데이터를 위의 양식 (순서(int), 예약자명, 경과시간(타이머로 나중에 바꿔야됌) 을 적어주면 리스트에 추가된다.*/

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new BookingManagementAdapter(getApplicationContext(),data));

        binding.bookingManagementRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.bookingManagementRecyclerview.setAdapter(new BookingManagementAdapter(data));
    }
}