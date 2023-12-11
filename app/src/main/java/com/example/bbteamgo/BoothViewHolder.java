package com.example.bbteamgo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class BoothViewHolder extends RecyclerView.ViewHolder {

    ImageView picture;
    TextView title, explainText;

    public BoothViewHolder(@NonNull @org.jetbrains.annotations.NotNull View boothView,
                           final BoothAdapter.OnBoothClickListener listener) {
        super(boothView);
        picture = boothView.findViewById(R.id.picture);
        title = boothView.findViewById(R.id.title);
        explainText = boothView.findViewById(R.id.explain_text);

        boothView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onBoothClick(position);
                    }
                }
            }
        });
    }
}
