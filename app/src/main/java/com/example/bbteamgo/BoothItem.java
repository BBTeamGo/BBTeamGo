package com.example.bbteamgo;

public class BoothItem {
    String pictureURL;
    String title;
    String explainText;

    public BoothItem(String pictureURL, String title, String explainText) {
        this.pictureURL = pictureURL;
        this.title = title;
        this.explainText = explainText;
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
}
