package com.example.bbteamgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoothAdapter extends RecyclerView.Adapter<BoothViewHolder> {

    Context context;
    List<BoothItem> booths;

    public BoothAdapter(Context context, List<BoothItem> booths) {
        this.context = context;
        this.booths = booths;
    }



    @NonNull
    @Override
    public BoothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoothViewHolder(LayoutInflater.from(context).inflate(R.layout.item_booth, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoothViewHolder holder, int position) {
//        holder.picture.setImageResource(booths.get(position).getPicture());
        holder.title.setText(booths.get(position).getTitle());
        holder.explainText.setText(booths.get(position).getExplainText());
    }

    @Override
    public int getItemCount() {
        return booths.size();
    }
}
