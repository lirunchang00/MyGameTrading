package com.example.administrator.gametrading.Inter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.gametrading.Bean.Commodity;

import java.util.ArrayList;
import java.util.Map;

public interface ShopInter {
    void allCom(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void showDetail(final Context context, String id, ArrayList<Commodity> list);
    void searchCom(String com,final Context context,ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
}

