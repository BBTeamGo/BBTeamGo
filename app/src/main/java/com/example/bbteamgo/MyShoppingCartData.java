package com.example.bbteamgo;

public class MyShoppingCartData {
    private String menuName;

    private int numOfMenu;

    private int menuPrice;



    public MyShoppingCartData(String menuName, int numOfMenu, int menuPrice) {
        this.menuName = menuName;
        this.numOfMenu = numOfMenu;
        this.menuPrice = menuPrice;

    }


    public String getMenuName() {
        return menuName;
    }

    public int getNumOfMenu() {
        return numOfMenu;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setNumOfMenu(int numOfMenu) {
        this.numOfMenu = numOfMenu;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }
}
