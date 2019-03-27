package com.example.administrator.gametrading.MainPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class LazyFragment extends Fragment{
    private static  final  String TAG = LazyFragment.class.getSimpleName();
    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView==null){
            return;
        }
        if (isFirstVisible && isVisibleToUser){
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser){
            onFragementVisibleChange(true);
            isFirstVisible = true;
            return;
        }
        if (isFirstVisible){
            isFirstVisible = false;
            onFragementVisibleChange(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (rootView==null){
            rootView = view;
            if (getUserVisibleHint()){
                if (isFirstVisible){
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragementVisibleChange(true);
                isFirstVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initVariable(){
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView =true;
    }

    protected  void reuseView (boolean isReuse){
        isReuseView = isReuse;
    }
    protected  void  onFragementVisibleChange(boolean isVisible){

    }

    protected  void onFragmentFirstVisible(){

    }
    protected boolean isFragmentVisible(){
        return isFragmentVisible;
    }
}
