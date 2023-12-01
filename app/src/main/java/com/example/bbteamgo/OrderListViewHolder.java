package com.example.bbteamgo;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bbteamgo.databinding.OrderListUnfoldedItemViewBinding;

public class OrderListViewHolder extends RecyclerView.ViewHolder {
    public OrderListUnfoldedItemViewBinding binding;

    public OrderListViewHolder(OrderListUnfoldedItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
