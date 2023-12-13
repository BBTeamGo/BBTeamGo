package com.example.bbteamgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbteamgo.MyShoppingCartData;

import java.util.List;

public class MyShoppingCartAdapter extends RecyclerView.Adapter<MyShoppingCartAdapter.ViewHolder> {

    private List<MyShoppingCartData> shoppingCartItems;
    private Context context;

    public MyShoppingCartAdapter(Context context, List<MyShoppingCartData> shoppingCartItems) {
        this.context = context;
        this.shoppingCartItems = shoppingCartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_shopping_cart_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyShoppingCartData item = shoppingCartItems.get(position);

        holder.menuNameTextView.setText(item.getMenuName());
        holder.menuNumberTextView.setText(String.valueOf(item.getNumOfMenu()));

        holder.menuMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Handle minus button click
            }
        });

        holder.menuPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Handle plus button click
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingCartItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuNameTextView;
        TextView menuNumberTextView;
        ImageButton menuMinusButton;
        ImageButton menuPlusButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuNameTextView = itemView.findViewById(R.id.shopping_cart_menu_list);
            menuNumberTextView = itemView.findViewById(R.id.menu_number);
            menuMinusButton = itemView.findViewById(R.id.menu_minus_btn);
            menuPlusButton = itemView.findViewById(R.id.menu_plus_btn);
        }
    }
}