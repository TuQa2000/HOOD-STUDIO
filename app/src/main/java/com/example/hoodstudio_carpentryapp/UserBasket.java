package com.example.hoodstudio_carpentryapp;

import com.google.firebase.database.Exclude;

public class UserBasket {
    public String basName;
    public String basPrice;
    public String basDetail;
    public String basSize;
    public String basUrl;
    public String mKey;

    public UserBasket() {
    }

    public UserBasket(String basName, String basPrice, String basDetail, String basSize, String basUrl) {
        this.basName = basName;
        this.basPrice = basPrice;
        this.basDetail = basDetail;
        this.basSize = basSize;
        this.basUrl = basUrl;
    }

    public String getBasName() {
        return basName;
    }

    public void setBasName(String basName) {
        this.basName = basName;
    }

    public String getBasPrice() {
        return basPrice;
    }

    public void setBasPrice(String basPrice) {
        this.basPrice = basPrice;
    }

    public String getBasDetail() {
        return basDetail;
    }

    public void setBasDetail(String basDetail) {
        this.basDetail = basDetail;
    }

    public String getBasSize() {
        return basSize;
    }

    public void setBasSize(String basSize) {
        this.basSize = basSize;
    }

    public String getBasUrl() {
        return basUrl;
    }

    public void setBasUrl(String basUrl) {
        this.basUrl = basUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
