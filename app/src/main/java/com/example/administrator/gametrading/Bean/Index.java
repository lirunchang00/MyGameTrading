package com.example.administrator.gametrading.Bean;

import android.content.Intent;

public class Index {
    private String name;
    private int imageId;
    private Intent intent;


    public Index(String name, int imageId,Intent intent){
        this.name = name;
        this.imageId = imageId;
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

}
