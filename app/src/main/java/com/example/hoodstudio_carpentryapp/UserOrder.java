package com.example.hoodstudio_carpentryapp;

import com.google.firebase.database.Exclude;

public class UserOrder {
    public String orderCustomerName;
    public String orderCustomerPhone;
    public String orderName;
    public String orderPrice;
    public String orderDetail;
    public String orderSize;
    public String orderUrl;
    public String orderStatus;
    public String orderAddress;

    public String mKey;

    public UserOrder() {
    }

    public UserOrder(String orderCustomerName, String orderCustomerPhone, String orderName, String orderPrice, String orderDetail, String orderSize, String orderUrl, String orderStatus, String orderAddress) {
        this.orderCustomerName = orderCustomerName;
        this.orderCustomerPhone = orderCustomerPhone;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderDetail = orderDetail;
        this.orderSize = orderSize;
        this.orderUrl = orderUrl;
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
    }

    public String getOrderCustomerName() {
        return orderCustomerName;
    }

    public void setOrderCustomerName(String orderCustomerName) {
        this.orderCustomerName = orderCustomerName;
    }

    public String getOrderCustomerPhone() {
        return orderCustomerPhone;
    }

    public void setOrderCustomerPhone(String orderCustomerPhone) {
        this.orderCustomerPhone = orderCustomerPhone;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(String orderSize) {
        this.orderSize = orderSize;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
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
