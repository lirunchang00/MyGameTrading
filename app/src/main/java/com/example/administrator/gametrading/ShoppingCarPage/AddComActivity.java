package com.example.administrator.gametrading.ShoppingCarPage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.Commodity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;
import com.example.administrator.gametrading.util.UploadUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddComActivity extends AppCompatActivity{
    private String Cookie = "lrc";
    private Context context;
    private ImageView add_com_image;
    private EditText add_com_name,add_com_type,add_com_content,add_com_method,add_com_special,add_com_price;
    private Spinner add_com_server,add_com_operating;
    private Button add_com_next,add_com_back;
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    public static final int CROP_SMALL_PICTURE=3;
    private String picPath = null;
    private Uri photoUri;
    private String TAG = "AddComActitivy";
    private List<String> operatingList = new ArrayList<>();
    private List<String> serverList =new ArrayList<>();

    String path;
    String ComName ;
    String Type ;
    String ComPrice;
    String[] Operating ;
    String ComSpecial ;
    String Solder ;
    String ComContent ;
    String ComMethod ;
    String[] ComServer ;
    String ComImage ;
    String o1;
    String s1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_com);
       // Cookie = new SessionUtil(context).GetSession();
        initView();
        initData();


        add_com_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_com_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next();
            }
        });


    }

    private void initData() {
        operatingList.add("安卓系统");
        operatingList.add("IOS系统");
        serverList.add("格里芬");
        serverList.add("Kar98");
        serverList.add("G11");
        serverList.add("HK416");
        /**
         * 操作系统选择
         * **/
        Operating=getResources().getStringArray(R.array.operating);
        ArrayAdapter<String> operatingAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, operatingList);
        add_com_operating.setAdapter(operatingAdapter);
        add_com_operating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                o1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**
         * 服务器选择
         * **/
        ComServer = getResources().getStringArray(R.array.server);
        ArrayAdapter<String> serverAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,serverList);
        add_com_server.setAdapter(serverAdapter);
        add_com_server.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        add_com_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddComActivity.this
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddComActivity.this
                            ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                pickPhoto();
            }
        });

    }

    private void pickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,SELECT_PIC_BY_PICK_PHOTO);
    }
    public void  onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode== Activity.RESULT_OK){
            switch (requestCode){

                case SELECT_PIC_BY_PICK_PHOTO:
                    startActivityForResult(cutpcikImage(data.getData()),CROP_SMALL_PICTURE);
                    break;
                case CROP_SMALL_PICTURE:
                    if (data!=null){
                        String apicPath=photoUri.toString();
                        picPath = apicPath.substring(5);
                        setImageView(data);
                        break;
                        // dophoto(requestCode,data);
                   /* Bundle ex  = data.getExtras();
                    dophoto(requestCode,data);

                    Bitmap bm = BitmapFactory.decodeFile(picPath);
                    Bitmap bm = ex.getParcelable("data");
                    head.setImageBitmap(bm);*/
                    }
            }
        }

        super.onActivityResult(requestCode,resultCode,data);
    }
    private void next() {
        ComName = add_com_name.getText().toString();
        Type = add_com_type.getText().toString();
        ComSpecial = add_com_special.getText().toString();
        Solder = Cookie;
        ComContent = add_com_content.getText().toString();
        ComMethod = add_com_method.getText().toString();
        ComPrice = add_com_price.getText().toString();
        //ComImage = add_com_image.toString();




        if (init()){
            Commodity commodity = new Commodity();
            commodity.setComName(ComName);
            commodity.setType(Type);
            commodity.setComPrice(ComPrice);
            commodity.setOperating(o1);
            commodity.setComSpecial(ComSpecial);
            commodity.setSolder(Solder);
            commodity.setComContent(ComContent);
            commodity.setComMethod(ComMethod);
            commodity.setComServer(s1);
            commodity.setComImage(picPath);
            Log.e("getComName",commodity.getComName()+"");
            Log.e("getType",commodity.getType()+"");
            Log.e("getOperating",commodity.getOperating()+"");
            Log.e("getComMethod",commodity.getComMethod()+"");
            Log.e("getComServer",commodity.getComServer()+"");
            Log.e("getComImage",commodity.getComImage()+"");
            Intent intent = new Intent(AddComActivity.this,AddComMsgActivity.class);
            intent.putExtra("commodity",commodity);
            startActivity(intent);
        }
    }

    private void initView() {
        add_com_image = (ImageView)this.findViewById(R.id.add_com_image);
        add_com_name = (EditText)this.findViewById(R.id.add_com_name);
        add_com_type = (EditText)this.findViewById(R.id.add_com_type);
        add_com_content = (EditText)this.findViewById(R.id.add_com_content);
        add_com_method = (EditText)this.findViewById(R.id.add_com_method);
        add_com_special = (EditText)this.findViewById(R.id.add_com_special);
        add_com_next = (Button) this.findViewById(R.id.add_com_next);
        add_com_back = (Button)this.findViewById(R.id.add_com_back);
        add_com_server = (Spinner) this.findViewById(R.id.add_com_server);
        add_com_operating = (Spinner) this.findViewById(R.id.add_com_operating);
        add_com_price = (EditText)this.findViewById(R.id.add_com_price);
    }

    private boolean init() {

        if (ComName.equals("")) {
            Toast.makeText(AddComActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Type.equals("")) {

            Toast.makeText(AddComActivity.this, "账号类型不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Operating.equals("")) {
            Toast.makeText(AddComActivity.this, "操作系统不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ComMethod.equals("")) {
            Toast.makeText(AddComActivity.this, "使用方法不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ComServer.equals("")) {

            Toast.makeText(AddComActivity.this, "服务器不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ComPrice.equals("")) {

            Toast.makeText(AddComActivity.this, "服务器不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    protected Intent cutpcikImage(Uri uri){
        try {
            String a = new SessionUtil(AddComActivity.this).GetSession();
            String cookie = a.substring(5);
            UUID uuid = UUID.randomUUID();
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    uuid+".png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            photoUri = outputUri;
            Log.e(TAG, "1: "+photoUri);
            Intent intent = new Intent("com.android.camera.action.CROP");

            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);

            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 200);
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }  intent.putExtra("noFaceDetection", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            Log.e(TAG, "2: "+photoUri);
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void setImageView(Intent data){
        /*Bundle ex  = data.getExtras();
        if (ex!=null){
            Bitmap bm = ex.getParcelable("data");
            head.setImageBitmap(bm);}*/
        Bitmap bitmap = null;
        try {
            Log.e(TAG, "1231: "+photoUri);
            bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(photoUri));
            add_com_image.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
