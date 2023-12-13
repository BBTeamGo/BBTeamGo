package com.example.bbteamgo;

import android.view.LayoutInflater;
import android.view.View;
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
        OrderScreenData item = data.get(position);
        holder.binding.menuName.setText(data.get(position).getMenu_name());
        holder.binding.menuPrice.setText(String.valueOf(data.get(position).getMenu_price()));


        Glide.with(holder.binding.getRoot())
                .load(data.get(position).getImage_url())
                .into(holder.binding.menuImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ShoppingCart에 아이템 추가
                ShoppingCart.getInstance().addItem(new MyShoppingCartData(
                        item.getMenu_name(),
                        1,  // 임시로 수량 1로 설정
                        item.getMenu_price()
                ));
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
