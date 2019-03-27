package com.example.administrator.gametrading.MainPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.administrator.gametrading.Adapter.TrandingAdapter;
import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.TransactionPage.ComDetailsActivity;
import com.example.administrator.gametrading.util.XListView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends LazyLoadBaseFragment implements XListView.IXListViewListener{
    private XListView xListView;
    private List<Commodity> commodityList = new ArrayList<>();
    private FloatingActionButton fb_button;
    private Handler reFreshHandler,initHandler;
    private static final int CHANGE_UI = 1;
    private TrandingAdapter adapter;
    private SearchView search_searchView;
    private ImageView back_image;
    private ImageView scan_image;
    private View search_view;
    private View view;
    //public static ComListActivity instance2;
    public ShopFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.shop_fragment,container,false);
        Log.e("ShopFragment", "ShopFragment");
        initRefresh();
        init();
        initData();
        return view;
    }

    private void initData() {

    }

    private void init() {
        fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //无滑动动画
                xListView.setSelection(0);
                //有滑动动画
                //xListView.smoothScrollToPosition(0);
            }
        });
        //点击item 跳转 商品详情页
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Commodity commodity = commodityList.get(position - 1);
                Intent intent = new Intent(getActivity(), ComDetailsActivity.class);
                //intent.putExtra("id", String.valueOf(commodity.getComId()));

                //Bundle bundle = new Bundle();
                //bundle.putSerializable("commodity",commodity);
                //intent.putExtras(bundle);

                intent.putExtra("commodity",commodity);
                Log.e("before intent",commodity.toString());
                startActivity(intent);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private void initRefresh() {
        //获取数据刷新
        initHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        adapter = new TrandingAdapter(getActivity(), R.layout.item_tranding, commodityList);
                        xListView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        Log.e("after initData", commodityList.toString());
                        break;
                }
            }
        };

    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {

        adapter = new TrandingAdapter(getActivity(), R.layout.item_tranding, commodityList);
        fb_button = (FloatingActionButton) view.findViewById(R.id.fb_button);
        xListView = (XListView) view.findViewById(R.id.listView);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        xListView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new Thread() {

            public void run() {

                try {
                    sleep(1000);
                    reFreshHandler.sendMessage(new Message());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }.start();
    }

    @Override
    public void onLoadMore() {
        new Thread() {

            public void run() {
                try {
                    sleep(1000);
                    reFreshHandler.sendMessage(new Message());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }.start();
    }
}
