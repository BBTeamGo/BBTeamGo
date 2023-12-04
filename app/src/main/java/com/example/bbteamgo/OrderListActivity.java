package com.example.bbteamgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bbteamgo.databinding.ActivityOrderListBinding;
import com.example.bbteamgo.databinding.OrderListUnfoldedItemViewBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {

    Dialog dialog;
    ImageButton btnDialogCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOrderListBinding binding = ActivityOrderListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<OrderListData> data = new ArrayList<>();
        data.add(new OrderListData("1-2", "1-2번 테이블", "주문경과 시간: 30분 28초", "순대볶음", "오뎅탕", "소주", 16000, 15000, 4500, 55000, false));
        data.add(new OrderListData("2-2", "2-2번 테이블", "주문경과 시간: 12분 28초", "퀘사디아", "오뎅탕", "맥주", 10000, 15000, 4000, 29000, false));


        binding.orderListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.orderListRecyclerview.setAdapter(new OrderListAdapter(data));

        binding.addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        dialog = new Dialog(OrderListActivity.this);
        dialog.setContentView(R.layout.add_order_dialog_box);
        btnDialogCancel =dialog.findViewById(R.id.cancel_btn);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box_background));
        dialog.setCancelable(false);

        btnDialogCancel.setOnClickListener(view ->{
                dialog.dismiss();
            }
        );













    }
}