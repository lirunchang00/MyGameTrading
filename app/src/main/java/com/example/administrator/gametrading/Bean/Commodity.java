package com.example.administrator.gametrading.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Commodity implements Serializable {

    private int comId;
    private String comName;
    private String comContent;
    private String comNum;
    private String comPrice;
    private String comImage;
    private String comSpecial;
    private String operating;
    private String comServer;
    private String comMethod;
    private String solder;
    private int active;
    private String type;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getComId() {
        return this.comId;
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

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public String getComNum() {
        return comNum;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public String getComPrice() {
        return comPrice;
    }

    public void setComPrice(String comPrice) {
        this.comPrice = comPrice;
    }

    public String getComImage() {
        return comImage;
    }

    public void setComImage(String comImage) {
        this.comImage = comImage;
    }

    public String getComSpecial() {
        return comSpecial;
    }

    public void setComSpecial(String comSpecial) {
        this.comSpecial = comSpecial;
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }

    public String getComServer() {
        return comServer;
    }

    public void setComServer(String comServer) {
        this.comServer = comServer;
    }

    public String getComMethod() {
        return comMethod;
    }

    public void setComMethod(String comMethod) {
        this.comMethod = comMethod;
    }

    public String getSolder() {
        return solder;
    }

    public void setSolder(String solder) {
        this.solder = solder;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(comId);
        dest.writeString(comName);
        dest.writeString(comContent);
        dest.writeString(comMethod);
        dest.writeString(comNum);
        dest.writeString(comPrice);
        dest.writeString(comImage);
        dest.writeString(comSpecial);
        dest.writeString(operating);
        dest.writeString(comServer);
        dest.writeString(comMethod);
        dest.writeString(solder);
        dest.writeInt(active);
        dest.writeString(type);
    }

    public static final Creator<Commodity> CREATOR = new Creator<Commodity>() {
        @Override
        public Commodity createFromParcel(Parcel in) {
            Commodity commodity = new Commodity();
            commodity.comId = in.readInt();
            commodity.comName = in.readString();
            commodity.comContent = in.readString();
            commodity.comNum = in.readString();
            commodity.comPrice = in.readString();
            commodity.comImage = in.readString();
            commodity.comSpecial = in.readString();
            commodity.operating = in.readString();
            commodity.comServer = in.readString();
            commodity.comMethod = in.readString();
            commodity.solder = in.readString();
            commodity.active = in.readInt();
            commodity.type  = in.readString();
            commodity.date = in.readString();
            return commodity;
        }

        @Override
        public Commodity[] newArray(int size) {
            return new Commodity[size];
        }
    };*/
}