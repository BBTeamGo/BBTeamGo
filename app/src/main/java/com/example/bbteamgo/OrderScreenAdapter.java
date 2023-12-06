package com.example.bbteamgo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bbteamgo.databinding.OrderScreenItemViewBinding;

import java.util.List;

public class OrderScreenAdapter extends RecyclerView.Adapter<OrderScreenViewHolder> {

    List<OrderScreenData> data;

    public OrderScreenAdapter(List<OrderScreenData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public OrderScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderScreenItemViewBinding binding = OrderScreenItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderScreenViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderScreenViewHolder holder, int position) {
        holder.binding.menuName.setText(data.get(position).getMenu_name());
        holder.binding.menuPrice.setText(String.valueOf(data.get(position).getMenu_price()));


        Glide.with(holder.binding.getRoot())
                .load(data.get(position).getImage_url())
                .into(holder.binding.menuImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
