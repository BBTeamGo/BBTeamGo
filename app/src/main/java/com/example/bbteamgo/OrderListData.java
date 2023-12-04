package com.example.bbteamgo;

public class OrderListData {
    private String tableNumber;

    private String tableName;

    private String orderElapsedTime;

    private String firstMenu, secondMenu, thirdMenu;

    private int priceOfFirstMenu, priceOfSecondMenu, priceOfThirdMenu, sumOfAllMenu;


    boolean isVisible;

    public OrderListData(String tableNumber, String tableName, String orderElapsedTime, String firstMenu, String secondMenu, String thirdMenu, int priceOfFirstMenu, int priceOfSecondMenu, int priceOfThirdMenu, int sumOfAllMenu, boolean isVisible) {
        this.tableNumber = tableNumber;
        this.tableName = tableName;
        this.orderElapsedTime = orderElapsedTime;
        this.firstMenu = firstMenu;
        this.secondMenu = secondMenu;
        this.thirdMenu = thirdMenu;
        this.priceOfFirstMenu = priceOfFirstMenu;
        this.priceOfSecondMenu = priceOfSecondMenu;
        this.priceOfThirdMenu = priceOfThirdMenu;
        this.sumOfAllMenu = sumOfAllMenu;
        this.isVisible = isVisible;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public String getTableName() {
        return tableName;
    }

    public String getOrderElapsedTime() {
        return orderElapsedTime;
    }

    public String getFirstMenu() {
        return firstMenu;
    }

    public String getSecondMenu() {
        return secondMenu;
    }

    public String getThirdMenu() {
        return thirdMenu;
    }

    public int getPriceOfFirstMenu() {
        return priceOfFirstMenu;
    }

    public int getPriceOfSecondMenu() {
        return priceOfSecondMenu;
    }

    public int getPriceOfThirdMenu() {
        return priceOfThirdMenu;
    }

    public int getSumOfAllMenu() {
        return sumOfAllMenu;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setOrderElapsedTime(String orderElapsedTime) {
        this.orderElapsedTime = orderElapsedTime;
    }

    public void setFirstMenu(String firstMenu) {
        this.firstMenu = firstMenu;
    }

    public void setSecondMenu(String secondMenu) {
        this.secondMenu = secondMenu;
    }

    public void setThirdMenu(String thirdMenu) {
        this.thirdMenu = thirdMenu;
    }

    public void setPriceOfFirstMenu(int priceOfFirstMenu) {
        this.priceOfFirstMenu = priceOfFirstMenu;
    }

    public void setPriceOfSecondMenu(int priceOfSecondMenu) {
        this.priceOfSecondMenu = priceOfSecondMenu;
    }

    public void setPriceOfThirdMenu(int priceOfThirdMenu) {
        this.priceOfThirdMenu = priceOfThirdMenu;
    }

    public void setSumOfAllMenu(int sumOfAllMenu) {
        this.sumOfAllMenu = sumOfAllMenu;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

}
