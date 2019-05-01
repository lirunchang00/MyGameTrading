package com.example.administrator.gametrading.Inter;

import android.content.Context;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.Bean.Forum;
import com.example.administrator.gametrading.Bean.Girls;

public interface AdminInter {
    void deletePost(Forum forum, Context context);
    void shenhe(Commodity commodity, Context context);
    void deleteIndex(Girls girls,Context context);
}
