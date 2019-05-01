package com.example.administrator.gametrading.Bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends DataSupport {
    private int id;
    private String Name;
    private String Pass;
    private String Head;
    private String active;
    private String pmg;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Pass='" + Pass + '\'' +
                ", Head='" + Head + '\'' +
                ", active='" + active + '\'' +
                ", pmg='" + pmg + '\'' +
                '}';
    }

    public String getPmg() {
        return pmg;
    }

    public void setPmg(String pmg) {
        this.pmg = pmg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
