package com.example.bbteamgo;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbteamgo.databinding.OrderListUnfoldedItemViewBinding;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListViewHolder> {

    List<OrderListData> data;


    public OrderListAdapter(List<OrderListData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderListUnfoldedItemViewBinding binding = OrderListUnfoldedItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
            holder.binding.tableNumber.setText(data.get(position).getTableNumber());
            holder.binding.tableName.setText(data.get(position).getTableName());
            holder.binding.orderElapsedTime.setText(data.get(position).getOrderElapsedTime());
            holder.binding.firstMenu.setText(data.get(position).getFirstMenu());
            holder.binding.secondMenu.setText(data.get(position).getSecondMenu());
            holder.binding.thirdMenu.setText(data.get(position).getThirdMenu());
            holder.binding.priceOfFirstMenu.setText(String.valueOf(data.get(position).getPriceOfFirstMenu()));
            holder.binding.priceOfSecondMenu.setText(String.valueOf(data.get(position).getPriceOfSecondMenu()));
            holder.binding.priceOfThirdMenu.setText(String.valueOf(data.get(position).getPriceOfThirdMenu()));
            holder.binding.sumOfAllMenu.setText(String.valueOf(data.get(position).getSumOfAllMenu()));

            holder.binding.tableName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data.get(position).isVisible)
                    {
                        holder.binding.firstMenu.setVisibility(View.GONE);
                        holder.binding.secondMenu.setVisibility(View.GONE);
                        holder.binding.thirdMenu.setVisibility(View.GONE);
                        holder.binding.priceOfFirstMenu.setVisibility(View.GONE);
                        holder.binding.priceOfSecondMenu.setVisibility(View.GONE);
                        holder.binding.priceOfThirdMenu.setVisibility(View.GONE);
                        holder.binding.sumOfAllMenu.setVisibility(View.GONE);
                        holder.binding.cancelOrder.setVisibility(View.GONE);
                        holder.binding.totalPrice.setVisibility(View.GONE);
                        data.get(position).isVisible= false;

                    }
                    else
                    {
                        holder.binding.firstMenu.setVisibility(View.VISIBLE);
                        holder.binding.secondMenu.setVisibility(View.VISIBLE);
                        holder.binding.thirdMenu.setVisibility(View.VISIBLE);
                        holder.binding.priceOfFirstMenu.setVisibility(View.VISIBLE);
                        holder.binding.priceOfSecondMenu.setVisibility(View.VISIBLE);
                        holder.binding.priceOfThirdMenu.setVisibility(View.VISIBLE);
                        holder.binding.sumOfAllMenu.setVisibility(View.VISIBLE);
                        holder.binding.cancelOrder.setVisibility(View.VISIBLE);
                        holder.binding.totalPrice.setVisibility(View.VISIBLE);
                        data.get(position).isVisible= true;
                    }
                }
            });


    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
