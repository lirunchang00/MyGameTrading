package com.example.administrator.gametrading.Bean;

import java.io.Serializable;

public class CollectionBean  implements Serializable {
    private int id;
    private int comId;
    private String comName;
    private String image;
    private String solder;
    private String price;
    private String head;

    @Override
    public String toString() {
        return "CollectionBean{" +
                "id=" + id +
                ", comId=" + comId +
                ", comName='" + comName + '\'' +
                ", image='" + image + '\'' +
                ", solder='" + solder + '\'' +
                ", price='" + price + '\'' +
                ", head='" + head + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSolder() {
        return solder;
    }

    public void setSolder(String solder) {
        this.solder = solder;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
