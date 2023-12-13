package com.example.bbteamgo;

import com.example.bbteamgo.MyShoppingCartData;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<MyShoppingCartData> items;

    private ShoppingCart() {
        items = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(MyShoppingCartData item) {
        items.add(item);
    }

    public List<MyShoppingCartData> getItems() {
        return items;
    }
}
