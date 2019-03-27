package com.example.administrator.gametrading.Bean;

import java.io.Serializable;

public class Commodity implements Serializable {

    private int comId;               //商品id
    private String comName;          //商品名称
    private String comSynopsis;     //商品简介
    private int comSoldNum;         //已售数量
    private double comPrice;        //原价
    private int comStock;           //库存
    private String comImage;         //商品图片
    private int comFlag;             //上下架
    private String comClass;         //商品类别
    private String seller;           //卖家
    private int active;             //活跃度

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Commodity(int comId, String comName, double comPrice, String comImage) {
        this.comId = comId;
        this.comName = comName;
        this.comPrice = comPrice;
        this.comImage = comImage;
    }

    public Commodity(int comId ,String comName, String comSynopsis, int comSoldNum, double comPrice, int comStock, String comImage,int comFlag,String comClass) {
        this.comId=comId;
        this.comName = comName;
        this.comSynopsis = comSynopsis;
        this.comSoldNum = comSoldNum;
        this.comPrice = comPrice;
        this.comStock = comStock;
        this.comImage = comImage;
        this.comFlag=comFlag;
        this.comClass=comClass;

    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComSynopsis() {
        return comSynopsis;
    }

    public void setComSynopsis(String comSynopsis) {
        this.comSynopsis = comSynopsis;
    }

    public int getComSoldNum() {
        return comSoldNum;
    }

    public void setComSoldNum(int comSoldNum) {
        this.comSoldNum = comSoldNum;
    }

    public double getComPrice() {
        return comPrice;
    }

    public void setComPrice(double comPrice) {
        this.comPrice = comPrice;
    }

    public int getComStock() {
        return comStock;
    }

    public void setComStock(int comStock) {
        this.comStock = comStock;
    }

    public String getComImage() {
        return comImage;
    }

    public void setComImage(String comImage) {
        this.comImage = comImage;
    }

    public int getComFlag() {
        return comFlag;
    }

    public void setComFlag(int comFlag) {
        this.comFlag = comFlag;
    }

    public String getComClass() {
        return comClass;
    }

    public void setComClass(String comClass) {
        this.comClass = comClass;
    }
}
