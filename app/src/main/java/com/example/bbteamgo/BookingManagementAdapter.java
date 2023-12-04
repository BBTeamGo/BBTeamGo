package com.example.bbteamgo;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbteamgo.databinding.BookingManagementItemViewBinding;

import java.util.List;


public class BookingManagementAdapter extends RecyclerView.Adapter<BookingManagmentViewHolder>{


    List<BookingManagementData> data;

    public BookingManagementAdapter( List<BookingManagementData> data) {

        this.data = data;
    }

    @NonNull
    @Override
    public BookingManagmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookingManagementItemViewBinding binding = BookingManagementItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookingManagmentViewHolder(binding);
        // return new BookingManagmentViewHolder(LayoutInflater.from(context).inflate(R.layout.booking_management_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookingManagmentViewHolder holder, int position) {
        holder.binding.bookingOrder.setText(String.valueOf(data.get(position).getBookingOrder()));
        holder.binding.bookerName.setText(data.get(position).getBookerName());
        holder.binding.elapsedTime.setText(data.get(position).getElapsedTime());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}


//booking management page activity의 리사이클러뷰에 사용되는 어댑터.