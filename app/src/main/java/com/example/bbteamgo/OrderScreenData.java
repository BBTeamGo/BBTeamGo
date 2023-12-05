package com.example.bbteamgo;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.RequestBuilder;

public class OrderScreenData {
    private int menu_price;
    private String menu_name;


    private String image_url;

    public OrderScreenData(int menu_price, String menu_name, String image_url) {
        this.menu_price = menu_price;
        this.menu_name = menu_name;
        this.image_url = image_url;
    }




    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


}
