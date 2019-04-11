package com.example.administrator.gametrading.Bean;

import java.io.Serializable;

public class OrderBean implements Serializable{
    private int orderID;//订单号
    private String username;//用户名
    private String comName;//商品名
    private String price;//单价
    private String phone;//电话
    private String date;
    private String orderImage;//商品图片
    private String status;//交易状态
    private String email;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getOrderImage() {
        return orderImage;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderID=" + orderID +
                ", username='" + username + '\'' +
                ", comName='" + comName + '\'' +
                ", price='" + price + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", orderImage='" + orderImage + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }
}
