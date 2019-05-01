package com.example.administrator.gametrading.MyCenterPage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gametrading.Bean.User;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.PhotoPopupWindow;
import com.example.administrator.gametrading.util.SessionUtil;
import com.example.administrator.gametrading.util.UploadUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyMessageActivity extends AppCompatActivity {

    private ImageView head;
    private Button upload_head;
    private String picPath = null;
    private Button Mymessage_back;
    private TextView name;
    private Activity mActivity;
    private  PhotoPopupWindow mPhotoPopupWindow;
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    public static final String KEY_PHOTO_PATH = "photo_path";
    public static final int CROP_SMALL_PICTURE=3;
    private static final String TAG = "SelectPicActivity";
    private Uri photoUri;
    private Intent lastintent;
    private Uri tempUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        head = (ImageView) this.findViewById(R.id.selecthead);
        String cookie = new SessionUtil(MyMessageActivity.this).GetSession();
        if (cookie==""){
            Intent intent= new Intent();
            intent.setClass(MyMessageActivity.this,LoginActivity.class);
            Toast.makeText(MyMessageActivity.this,"请登陆再更换头像",Toast.LENGTH_LONG).show();
            startActivity(intent);

        }else if (cookie!=""){
            name.setText(cookie.substring(5));
        }
        User userBean=new User();
        lastintent = getIntent();
        /**
         * 点击进入头像选择页面
         */
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoPopupWindow = new PhotoPopupWindow(MyMessageActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(MyMessageActivity.this
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MyMessageActivity.this
                                    ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                        pick_photo();
                        mPhotoPopupWindow.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        take_photo();
                        mPhotoPopupWindow.dismiss();
                    }
                });
                View rootView = LayoutInflater.from(MyMessageActivity.this).inflate(R.layout.activity_my_message, null);
                mPhotoPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });





        /**
         * 确认要上传的头像
         */
        upload_head = (Button)this.findViewById(R.id.upload_head);
        upload_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picPath!=null){
                    touploadFile(MyMessageActivity.this);
                }else {
                    Log.e("error","error");
                }
            }
        });
        Mymessage_back = (Button)this.findViewById(R.id.Mymessage_back);
        Mymessage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMessageActivity.this.finish();
            }
        });
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data 相片数据
     */
/*    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode== Activity.RESULT_OK){
            picPath =data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
            Log.e("最终图片","最终图片"+picPath);
            Bitmap bm = BitmapFactory.decodeFile(picPath);
            head.setImageBitmap(bm);
        }
        super.onActivityResult(resultCode,resultCode,data);
    }*/

    /**
     * 调用工具类的上传图片功能
     * @param context
     */
    private void touploadFile(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",photoUri+"");
        Log.e("filepath",picPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( picPath,fileKey, Tools.imageurl,params,context);
    }

    //获取相机图片
    public void take_photo(){
        //获取SD卡
       /* String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)){
            //获取权限，创建照相机拍摄，并把之存进photoUri
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
            startActivityForResult(intent,SELECT_PIC_BY_TACK_PHOTO);
        }else{
            Toast.makeText(this,"内存卡不存在",Toast.LENGTH_LONG).show();
        }*/
        //创建一个file，用来存储拍照后的照片
        File outputfile = new File(this.getExternalCacheDir(),"output.png");
        try {
            if (outputfile.exists()){
                outputfile.delete();//删除
            }
            outputfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageuri ;
        if (Build.VERSION.SDK_INT >= 24){
            imageuri = FileProvider.getUriForFile(this,
                    "com.mycompany.wanjiale_supermarket_system.fileprovider", //可以是任意字符串
                    outputfile);
        }else{
            imageuri = Uri.fromFile(outputfile);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(intent,SELECT_PIC_BY_TACK_PHOTO);
    }
    public void pick_photo(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,SELECT_PIC_BY_PICK_PHOTO);
    }
    public void  onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case SELECT_PIC_BY_TACK_PHOTO:
                    String path = this.getExternalCacheDir().getPath();
                    String name = "output.png";
                    Log.e("path",path);
                    startActivityForResult(cuttakeImage(path,name),CROP_SMALL_PICTURE);
                    break;
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

    /**
     * 图片数据处理，回调参数到MymessageActitivy
     * @param requestCode
     * @param data
     */
    private void dophoto(int requestCode, Intent data) {

        if (requestCode==SELECT_PIC_BY_PICK_PHOTO){
            if (data==null){
                Toast.makeText(this,"选择图片文件出错",Toast.LENGTH_LONG).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri==null){
                Toast.makeText(this,"选择图片文件出错",Toast.LENGTH_LONG).show();
                return;
            }
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(photoUri,pojo,null,null,null);
        if (cursor!=null){
            int columnIndex = cursor.getColumnIndex(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            cursor.close();
        }
        Log.e(TAG,"photoUri = "+photoUri);
        Log.e(TAG,"imagepath = "+picPath);
        if (picPath!=null){
            lastintent.putExtra(KEY_PHOTO_PATH,picPath);
            setResult(Activity.RESULT_OK,lastintent);
        }else {
            Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }
    }

    protected Intent cutpcikImage(Uri uri){
        try {
            String a = new SessionUtil(MyMessageActivity.this).GetSession();
            String cookie = a.substring(5);
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    cookie+".png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            photoUri = outputUri;
            Log.e(TAG, "1: "+photoUri);
            Intent intent = new Intent("com.android.camera.action.CROP");

            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);

            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
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
    protected  Intent cuttakeImage(String camerapath,String imgname){
        try {
            String a = new SessionUtil(MyMessageActivity.this).GetSession();
            String cookie = a.substring(5);
            //设置裁剪之后的图片路径文件
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    cookie+".png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = null; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Intent intent = new Intent("com.android.camera.action.CROP");
            //拍照留下的图片
            File camerafile = new File(camerapath,imgname);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(this,
                        "com.mycompany.wanjiale_supermarket_system.fileprovider",
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            outputUri = Uri.fromFile(cutfile);
            //把这个 uri 提供出去，就可以解析成 bitmap了
            photoUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY",150);
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
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
            head.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
