package com.example.administrator.gametrading.Inter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.example.administrator.gametrading.Bean.Girls;

import java.util.ArrayList;

public interface GirlsInter {
    void add(Girls girls, Context context);
    void show(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void searchGirls(final String s,  Context context,ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
}
