package com.example.bbteamgo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class BoothViewHolder extends RecyclerView.ViewHolder {

    ImageView picture;
    TextView title, explainText, customerNumText;

    public BoothViewHolder(@NonNull @org.jetbrains.annotations.NotNull View boothView) {
        super(boothView);
        picture = boothView.findViewById(R.id.picture);
        title = boothView.findViewById(R.id.title);
        explainText = boothView.findViewById(R.id.explain_text);
        customerNumText = boothView.findViewById(R.id.customer_num_text);
    }
}
