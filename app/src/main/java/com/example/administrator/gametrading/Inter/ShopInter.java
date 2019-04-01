package com.example.administrator.gametrading.Inter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public interface ShopInter {
    void allCom(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void showDetail(final Context context,String id,ArrayList arrayList);
}

