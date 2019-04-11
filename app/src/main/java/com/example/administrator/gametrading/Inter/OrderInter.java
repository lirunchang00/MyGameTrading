package com.example.administrator.gametrading.Inter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.OrderBean;

import java.util.ArrayList;


public interface OrderInter {
    void addCom(Commodity commodity, Context context);

    void showMyOrder(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);

    void mySolder(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);

    void comDetail(Context context,Commodity commodity);
}
