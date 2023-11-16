package com.example.bbteamgo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BookingManagmentViewHolder extends RecyclerView.ViewHolder {

    TextView bookingOrder, bookerName, elapsedTime;

    public BookingManagmentViewHolder(View itemView){
        super(itemView);
        bookingOrder = itemView.findViewById(R.id.booking_order);
        bookerName = itemView.findViewById(R.id.booker_name);
        elapsedTime = itemView.findViewById(R.id.elapsed_time);

    }
}


/*booking management page 액티비티의 리사이클러 뷰에 사용되는 viewholder*/