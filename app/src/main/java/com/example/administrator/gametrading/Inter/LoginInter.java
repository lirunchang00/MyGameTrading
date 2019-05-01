package com.example.administrator.gametrading.Inter;

import android.content.Context;

import com.example.administrator.gametrading.Bean.User;

import java.util.ArrayList;

public interface LoginInter {
    public void login(User user, Context context);
    public void getPmg(String user,Context context);
    public void head(final Context context, ArrayList arrayList);
    public void editpass(User user,Context context);
}
