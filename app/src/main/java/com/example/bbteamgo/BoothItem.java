package com.example.bbteamgo;

public class BoothItem {
    String pictureURL;
    String title;
    String explainText;
    int maxCustomerNum, currentCustomerNum;

    public BoothItem(String pictureURL, String title, String explainText, int maxCustomerNum, int currentCustomerNum) {
        this.pictureURL = pictureURL;
        this.title = title;
        this.explainText = explainText;
        this.maxCustomerNum = maxCustomerNum;
        this.currentCustomerNum = currentCustomerNum;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplainText() {
        return explainText;
    }

    public void setExplainText(String explainText) {
        this.explainText = explainText;
    }

    public int getMaxCustomerNum() {
        return maxCustomerNum;
    }

    public void setMaxCustomerNum(int maxCustomerNum) {
        this.maxCustomerNum = maxCustomerNum;
    }

    public int getCurrentCustomerNum() {
        return currentCustomerNum;
    }

    public void setCurrentCustomerNum(int currentCustomerNum) {
        this.currentCustomerNum = currentCustomerNum;
    }
}
