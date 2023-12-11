package com.example.bbteamgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class BoothAdapter extends RecyclerView.Adapter<BoothViewHolder> {
    public interface OnBoothClickListener {
        void onBoothClick(int position);
    }
    OnBoothClickListener boothClickListener = null;

    Context context;
    List<BoothItem> booths;

    public BoothAdapter(Context context, List<BoothItem> booths, OnBoothClickListener listener) {
        this.context = context;
        this.booths = booths;
        boothClickListener = listener;
    }

    @NonNull
    @Override
    public BoothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoothViewHolder(LayoutInflater.from(context).inflate(R.layout.item_booth, parent, false),
                boothClickListener);
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
