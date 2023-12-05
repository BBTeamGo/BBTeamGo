package com.example.bbteamgo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbteamgo.databinding.OrderScreenItemViewBinding;

public class OrderScreenViewHolder extends RecyclerView.ViewHolder {
    public OrderScreenItemViewBinding binding;

    public OrderScreenViewHolder( OrderScreenItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
