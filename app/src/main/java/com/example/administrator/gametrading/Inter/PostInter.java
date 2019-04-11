package com.example.administrator.gametrading.Inter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.gametrading.Bean.Forum;

import java.util.ArrayList;

public interface PostInter {
    void allPost(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void essencePost(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void sendPost(Forum forum,Context context);
    void showRepeatPost(Context context,ArrayList arrayList,RecyclerView.Adapter arrayAdapter,Forum forum);
    void showPost(Context context,ArrayList arrayList,RecyclerView.Adapter arrayAdapter,Forum forum);
    void repeatPost(Context context,Forum forum);
    void myPost(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void myRepeat(final Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);
    void searchPost(final String text,final Context context,ArrayList arrayList,RecyclerView.Adapter arrayAdapter);

}
