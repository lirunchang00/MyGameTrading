package com.example.administrator.gametrading.Inter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.gametrading.Bean.CollectionBean;

import java.util.ArrayList;

public interface CollectionInter {
    void comCollection(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);

    void postCollection(Context context, ArrayList arrayList, RecyclerView.Adapter arrayAdapter);

    void deleteComCollection(Context context,String comId);

    void addComCollection(Context context,String comId);

    void deletePostCollection(Context context,String postId);

    void addPostCollection(Context contextm,String postId);
}
