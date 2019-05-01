package com.example.administrator.gametrading.MainPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.gametrading.AdminPage.AdminActivity;
import com.example.administrator.gametrading.Bean.User;
import com.example.administrator.gametrading.Handler.LoginHandler;
import com.example.administrator.gametrading.MainActivity;
import com.example.administrator.gametrading.MyCenterPage.ComCollectionActivity;
import com.example.administrator.gametrading.MyCenterPage.LoginActivity;
import com.example.administrator.gametrading.MyCenterPage.MyOrderActivity;
import com.example.administrator.gametrading.MyCenterPage.MyPostActivity;
import com.example.administrator.gametrading.MyCenterPage.MyRepeatActivity;
import com.example.administrator.gametrading.MyCenterPage.MySolderActivity;
import com.example.administrator.gametrading.MyCenterPage.PostCollectionActivity;
import com.example.administrator.gametrading.MyCenterPage.UpdateMessageActivity;
import com.example.administrator.gametrading.MyCenterPage.UpdatePasswordActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Service.LoginService;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.PhotoPopupWindow;
import com.example.administrator.gametrading.util.SessionUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyFragment extends LazyLoadBaseFragment {
    public MyFragment(){}
    private ImageView head;
    private RelativeLayout update_msg,update_password,my_post,my_repeat,my_post_collection,my_solder,my_com_collection,my_order,admin;
    private TextView login;
    private TextView my_name;
    private String Cookie="";
    private View view;
    private PhotoPopupWindow mPhotoPopupWindow;
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    public static final String KEY_PHOTO_PATH = "photo_path";
    public static final int CROP_SMALL_PICTURE=3;
    private static final String TAG = "SelectPicActivity";
    private String cookie="";
    private User user = new User();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment,container,false);
        Cookie = new SessionUtil(getContext()).GetSession();
        initView(view);

        if (!Cookie.equals("")){
            my_name.setText(Cookie.substring(5));
            cookie = Cookie.substring(5);
        }
        if (!cookie.equals("")) {
            getPms(cookie);
        }

        initAction();
        Log.e("MyFragment", "MyFragment");
        return view;
    }

    private void initAction() {
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {

                }
            }
        });

        update_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), UpdateMessageActivity.class);
                    startActivity(intent);
                }
            }
        });
        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
                    startActivity(intent);
                }
            }
        });
        my_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), MyPostActivity.class);
                    startActivity(intent);
                }
            }
        });
        my_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), MyRepeatActivity.class);
                    startActivity(intent);
                }
            }
        });
        my_post_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), PostCollectionActivity.class);
                    startActivity(intent);
                }
            }
        });
        my_com_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), ComCollectionActivity.class);
                    startActivity(intent);
                }
            }
        });
        my_solder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), MySolderActivity.class);
                    startActivity(intent);
                }
            }
        });
        my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie==""){
                    login();
                }else {
                    Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                    startActivity(intent);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cookie.equals("")){
                login();
                }else if (!Cookie.equals("")){
                    logout();
                }
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });



    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置参数
        builder.setMessage("确定退出登录？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new SessionUtil(getContext()).Clear();
                        Intent intent = new Intent();
                        intent.putExtra("refresh",4);
                        intent.setClass(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    private void getPms(final String name) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());


        String url = Tools.getPmg;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("myFragment",response);
                doPmg(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String > map = new HashMap<String ,String>();
                map.put("name",name);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void doPmg(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String pmg = jsonObject.getString("pmg");
            Log.e("pmg",pmg);
            user.setPmg(pmg);
            user.setHead(jsonObject.getString("head"));
            if (user.getPmg()!="0") {
                admin.setVisibility(View.VISIBLE);
            }
            String myHead = Tools.headUrl+"a/"+user.getHead();
            Log.e("myHead",myHead);
            Glide.with(getContext()).load(myHead).placeholder(R.drawable.main_my).into(head);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void login() {
        Toast.makeText(getActivity(),"登录",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        head = (ImageView)view.findViewById(R.id.my_head);
        update_msg = (RelativeLayout)view.findViewById(R.id.update_message);
        update_password = (RelativeLayout)view.findViewById(R.id.update_password);
        my_post= (RelativeLayout)view.findViewById(R.id.my_post);
        my_repeat= (RelativeLayout)view.findViewById(R.id.my_repeat);
        my_post_collection = (RelativeLayout)view.findViewById(R.id.my_post_collection);
        my_com_collection = (RelativeLayout)view.findViewById(R.id.my_com_collection);
        my_solder = (RelativeLayout)view.findViewById(R.id.my_solder);
        my_order= (RelativeLayout)view.findViewById(R.id.my_order);
        admin= (RelativeLayout)view.findViewById(R.id.admin);

        login = (TextView)view.findViewById(R.id.login_register);
        my_name = (TextView)view.findViewById(R.id.my_name);

        if (Cookie!=""){
            login.setText("注销");
        }
    }
}
