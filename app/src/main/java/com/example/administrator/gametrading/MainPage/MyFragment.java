package com.example.administrator.gametrading.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.administrator.gametrading.util.PhotoPopupWindow;
import com.example.administrator.gametrading.util.SessionUtil;

public class MyFragment extends LazyLoadBaseFragment {
    public MyFragment(){}
    private ImageView head;
    private RelativeLayout update_msg,update_password,my_post,my_repeat,my_post_collection,my_solder,my_com_collection,my_order;
    private Button login;
    private TextView my_name;
    private String Cookie="";
    private View view;
    private PhotoPopupWindow mPhotoPopupWindow;
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    public static final String KEY_PHOTO_PATH = "photo_path";
    public static final int CROP_SMALL_PICTURE=3;
    private static final String TAG = "SelectPicActivity";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment,container,false);
        Cookie = new SessionUtil(getContext()).GetSession();
        initView(view);

        if (Cookie!=""){
            my_name.setText(Cookie.substring(5));
        }
        initAction();
        Log.e("MyFragment", "MyFragment");
        return view;
    }

    private void initAction() {
        if (Cookie!=""){
        String cookie = Cookie.substring(5);
        }
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
                login();
            }
        });
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
        login = (Button)view.findViewById(R.id.login_register);
        my_name = (TextView)view.findViewById(R.id.my_name);
    }
}
