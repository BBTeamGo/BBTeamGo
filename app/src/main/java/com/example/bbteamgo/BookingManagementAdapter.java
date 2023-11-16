package com.example.bbteamgo;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BookingManagementAdapter extends RecyclerView.Adapter<BookingManagmentViewHolder>{

    Context context;
    List<BookingManagementData> data;

    public BookingManagementAdapter(Context context, List<BookingManagementData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BookingManagmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookingManagmentViewHolder(LayoutInflater.from(context).inflate(R.layout.booking_management_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookingManagmentViewHolder holder, int position) {
        holder.bookingOrder.setText(String.valueOf(data.get(position).getBookingOrder()));
        holder.bookerName.setText(data.get(position).getBookerName());
        holder.elapsedTime.setText(data.get(position).getElapsedTime());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}


//booking management page activity의 리사이클러뷰에 사용되는 어댑터.