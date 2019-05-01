package com.example.administrator.gametrading.AdminPage;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gametrading.Bean.Girls;
import com.example.administrator.gametrading.MainActivity;
import com.example.administrator.gametrading.R;
import com.example.administrator.gametrading.ShoppingCarPage.AddComActivity;
import com.example.administrator.gametrading.Tools;
import com.example.administrator.gametrading.util.SessionUtil;
import com.example.administrator.gametrading.util.UploadUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddIndexActivity extends AppCompatActivity {
    private EditText add_girls_name,add_girls_type,add_girls_start,add_girls_damage,add_girls_hit,add_girls_avoid,add_girls_shooting,add_girls_hp,add_girls_piercing;
    private EditText add_girls_cirt_rate,add_girls_cirt_damage,add_girls_chain,add_girls_armor,add_girls_skill_name,add_girls_skill_cd,add_girls_skill_introduction;
    private EditText add_girls_aura_introduction,add_girls_dubbing,add_girls_painter,add_girls_method,add_girls_introduction,add_girls_no;
    private ImageView add_girls_portrait,add_girls_break_portrait,add_girls_skill_pic,add_girls_aura,add_girls_enter,add_girls_head;
    private Button back;
    private Girls girls;
    private String name,type,start,damage,hit,avoid,shooting,hp,piercing,cirt_rate,cirt_damage,chain,armor,skill_name,skill_cd,skill_introduction;
    private String aura_introduction,dubbing,painter,method,introduction,skill_picS,portraitS,break_portrainS,auraS,no,headS,TypePic;
    public static final int PORTRAIT = 1;
    public static final int BREAK_PORTRAIT = 2;
    public static final int SKILL_PIC = 3;
    public static final int AURA = 4;
    public static final int HEAD = 5;
    public static final int CROP_SMALL_PICTURE_PORTRAIT=6;
    public static final int CROP_SMALL_PICTURE_BREAK_PORTRAIT=7;
    public static final int CROP_SMALL_PICTURE_SKILL_PIC=8;
    public static final int CROP_SMALL_PICTURE_AURA=9;
    public static final int CROP_SMALL_PICTURE_HEAD=10;
    public static final String KEY_PHOTO_PATH = "photo_path";
    private static final String TAG = "SelectPicActivity";
    private Uri portrait;
    private Uri break_portrain;
    private Uri skillpic;
    private Uri aura;
    private Uri head;
    private Intent lastintent;

    private Uri tempUri;
    private String portraitPath = null;
    private String break_portrainPath = null;
    private String skillpicPath = null;
    private String auraPath = null;
    private String headPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_index);
        initView();
        initData();

    }

    private void initData() {
        add_girls_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (portrait!=null){
                    touploadFile_portrait(AddIndexActivity.this);
                }else {
                    Log.e("error","error");
                }
                if (break_portrain!=null){
                    touploadFile_break_portrain(AddIndexActivity.this);
                }else {
                    Log.e("error","error");
                }
                if (skillpic!=null){
                    touploadFile_skillpic(AddIndexActivity.this);
                }else {
                    Log.e("error","error");
                }
                if (aura!=null){
                    touploadFile_aura(AddIndexActivity.this);
                }else {
                    Log.e("error","error");
                }
                if (head!=null){
                    touploadFile_head(AddIndexActivity.this);
                }else {
                    Log.e("error","error");
                }
                next();

            }
        });
        add_girls_portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_girls_no.getText().toString().equals("")){
                    Toast.makeText(AddIndexActivity.this,"先输入编号再选择",Toast.LENGTH_SHORT).show();
                }else {
                    if (ContextCompat.checkSelfPermission(AddIndexActivity.this
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddIndexActivity.this
                                ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    portrait();
                }
            }
        });
        add_girls_break_portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_girls_no.getText().toString().equals("")){
                    Toast.makeText(AddIndexActivity.this,"先输入编号再选择",Toast.LENGTH_SHORT).show();
                }else {
                    if (ContextCompat.checkSelfPermission(AddIndexActivity.this
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddIndexActivity.this
                                ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    break_portrait();
                }

            }
        });
        add_girls_skill_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_girls_no.getText().toString().equals("")){
                    Toast.makeText(AddIndexActivity.this,"先输入编号再选择",Toast.LENGTH_SHORT).show();
                }else {
                    if (ContextCompat.checkSelfPermission(AddIndexActivity.this
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddIndexActivity.this
                                ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    skill_pic();
                }
            }
        });
        add_girls_aura.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (add_girls_no.getText().toString().equals("")){
                    Toast.makeText(AddIndexActivity.this,"先输入编号再选择",Toast.LENGTH_SHORT).show();
                }else {
                    if (ContextCompat.checkSelfPermission(AddIndexActivity.this
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddIndexActivity.this
                                ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    aura();
                }
            }
        });
        add_girls_head.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (add_girls_no.getText().toString().equals("")){
                    Toast.makeText(AddIndexActivity.this,"先输入编号再选择",Toast.LENGTH_SHORT).show();
                }else {
                    if (ContextCompat.checkSelfPermission(AddIndexActivity.this
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddIndexActivity.this
                                ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    head();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void next() {
        no = add_girls_no.getText().toString();
        name = add_girls_name.getText().toString();
        type = add_girls_type.getText().toString();
        start = add_girls_start.getText().toString();
        damage = add_girls_damage.getText().toString();
        hit = add_girls_hit.getText().toString();
        avoid = add_girls_avoid.getText().toString();
        shooting = add_girls_shooting.getText().toString();
        hp = add_girls_hp.getText().toString();
        piercing = add_girls_piercing.getText().toString();
        cirt_rate = add_girls_cirt_rate.getText().toString();
        cirt_damage = add_girls_cirt_damage.getText().toString();
        chain = add_girls_chain.getText().toString();
        armor = add_girls_armor.getText().toString();
        skill_name = add_girls_skill_name.getText().toString();
        skill_cd = add_girls_skill_cd.getText().toString();
        skill_introduction = add_girls_skill_introduction.getText().toString();
        aura_introduction = add_girls_aura_introduction.getText().toString();
        dubbing = add_girls_dubbing.getText().toString();
        painter = add_girls_painter.getText().toString();
        method = add_girls_method.getText().toString();
        introduction = add_girls_introduction.getText().toString();
        portraitS = add_girls_no.getText().toString()+"portrait.png";
        break_portrainS = add_girls_no.getText().toString()+"portrait_d.png";
        skill_picS = add_girls_no.getText().toString()+"skill.png";
        auraS =add_girls_no.getText().toString()+"aura.png";
        headS = add_girls_no.getText().toString()+".jpg";
        TypePic =   type = add_girls_type.getText().toString()+".png";


        RequestQueue requestQueue = Volley.newRequestQueue(AddIndexActivity.this);
        String url = Tools.addindex;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddIndexActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("No",no);
                map.put("Name",name);
                map.put("Type",type);
                map.put("Start",start);
                map.put("Damage",damage);
                map.put("Hit",hit);
                map.put("Avoid",avoid);
                map.put("Shooting",shooting);
                map.put("Hp",hp);
                map.put("Piercing",piercing);
                map.put("CirtRate",cirt_rate);
                map.put("CirtDamage",cirt_damage);
                map.put("Chain",chain);
                map.put("Armor",armor);
                map.put("SkillName",skill_name);
                map.put("SkillCD",skill_cd);
                map.put("SkillIntroduction",skill_introduction);
                map.put("AuraIntroduction",aura_introduction);
                map.put("Dubbing",dubbing);
                map.put("Painter",painter);
                map.put("Method",method);
                map.put("Introduction",introduction);
                map.put("Portrait",portraitS);
                map.put("BreakPortrait",break_portrainS);
                map.put("SkillPic",skill_picS);
                map.put("Aura",auraS);
                map.put("Head",headS);
                map.put("TypePic",TypePic);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void portrait(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,PORTRAIT);
    }
    public void break_portrait(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,BREAK_PORTRAIT);
    }
    public void skill_pic(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,SKILL_PIC);
    }
    public void aura(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,AURA);
    }
    public void head(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,HEAD);
    }
    public void  onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode== Activity.RESULT_OK){
            Log.e("data",data.toString());
            switch (requestCode){
                case PORTRAIT:
                    startActivityForResult(portraitImage(data.getData()),CROP_SMALL_PICTURE_PORTRAIT);
                    break;
                case BREAK_PORTRAIT:
                    startActivityForResult(break_portraitImage(data.getData()),CROP_SMALL_PICTURE_BREAK_PORTRAIT);
                    break;
                case SKILL_PIC:
                    startActivityForResult(skill_picImage(data.getData()),CROP_SMALL_PICTURE_SKILL_PIC);
                    break;
                case AURA:
                    startActivityForResult(auraImage(data.getData()),CROP_SMALL_PICTURE_AURA);
                    break;
                case HEAD:
                    startActivityForResult(headImage(data.getData()),CROP_SMALL_PICTURE_HEAD);
                    break;
                case CROP_SMALL_PICTURE_PORTRAIT:
                    if (data!=null){
                        String apicPath=portrait.toString();
                        portraitPath = apicPath.substring(5);
                        setImageViewPortrait(data);
                    }
                        break;
                case CROP_SMALL_PICTURE_BREAK_PORTRAIT:
                    if (data!=null) {
                        String apicPath = break_portrain.toString();
                        break_portrainPath = apicPath.substring(5);
                        setImageViewBreakPortrait(data);
                    }
                        break;
                case CROP_SMALL_PICTURE_SKILL_PIC:
                    if (data!=null) {
                        String apicPath = skillpic.toString();
                        skillpicPath = apicPath.substring(5);
                        setImageViewSkillPic(data);
                    }
                        break;
                case CROP_SMALL_PICTURE_AURA:
                    if (data!=null) {
                        String apicPath = aura.toString();
                        auraPath = apicPath.substring(5);
                        setImageViewAura(data);
                    }
                        break;
                case CROP_SMALL_PICTURE_HEAD:
                    if (data!=null){
                        String apicPath=head.toString();
                        headPath = apicPath.substring(5);
                        setImageViewHead(data);
                    }
                    break;
                        // dophoto(requestCode,data);
                   /* Bundle ex  = data.getExtras();
                    dophoto(requestCode,data);

                    Bitmap bm = BitmapFactory.decodeFile(picPath);
                    Bitmap bm = ex.getParcelable("data");
                    head.setImageBitmap(bm);*/

            }
        }

        super.onActivityResult(requestCode,resultCode,data);
    }
    protected Intent portraitImage(Uri uri){
        try {
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    add_girls_no.getText().toString()+"portrait.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            portrait = outputUri;
            Log.e(TAG, "1: "+portrait);
            Intent intent = new Intent("com.android.camera.action.CROP");

            intent.setDataAndType(uri, "image/*");
           intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 2);

           /* intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);*/
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }  intent.putExtra("noFaceDetection", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            Log.e(TAG, "2: "+portrait);
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected Intent break_portraitImage(Uri uri){
        try {
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    add_girls_no.getText().toString()+"portrait_d.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            Log.e(TAG, "CutForPhoto: "+cutfile);
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            break_portrain = outputUri;
            Log.e(TAG, "1: "+break_portrain);
            Intent intent = new Intent("com.android.camera.action.CROP");

            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 2);

             /*      intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);*/
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }  intent.putExtra("noFaceDetection", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            Log.e(TAG, "2: "+break_portrain);
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected Intent skill_picImage(Uri uri){
        try {
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    add_girls_no.getText().toString()+"skill.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            skillpic = outputUri;
            Log.e(TAG, "1: "+skillpic);
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
            Log.e(TAG, "2: "+skillpic);
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected Intent auraImage(Uri uri){
        try {
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    add_girls_no.getText().toString()+"aura.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            aura = outputUri;
            Log.e(TAG, "1: "+aura);
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
            Log.e(TAG, "2: "+aura);
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected Intent headImage(Uri uri){
        try {
            if (uri == null) {
                Log.e("图片不存在", "图片不存在");
            }
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    add_girls_no.getText().toString()+".jpg"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Log.e(TAG, "CutForPhoto: "+cutfile);
            outputUri = Uri.fromFile(cutfile);
            head = outputUri;
            Log.e(TAG, "1: "+head);
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
            Log.e(TAG, "2: "+head);
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void setImageViewPortrait(Intent data){
        /*Bundle ex  = data.getExtras();
        if (ex!=null){
            Bitmap bm = ex.getParcelable("data");
            head.setImageBitmap(bm);}*/
        Bitmap bitmap = null;
        try {
            Log.e(TAG, "1231: "+portrait);
            bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(portrait));
            add_girls_portrait.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void setImageViewBreakPortrait(Intent data){
        /*Bundle ex  = data.getExtras();
        if (ex!=null){
            Bitmap bm = ex.getParcelable("data");
            head.setImageBitmap(bm);}*/
        Bitmap bitmap = null;
        try {
            Log.e(TAG, "1231: "+break_portrain);
            bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(break_portrain));
            add_girls_break_portrait.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void setImageViewSkillPic(Intent data){
        /*Bundle ex  = data.getExtras();
        if (ex!=null){
            Bitmap bm = ex.getParcelable("data");
            head.setImageBitmap(bm);}*/
        Bitmap bitmap = null;
        try {
            Log.e(TAG, "1231: "+skillpic);
            bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(skillpic));
            add_girls_skill_pic.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void setImageViewHead(Intent data){
        /*Bundle ex  = data.getExtras();
        if (ex!=null){
            Bitmap bm = ex.getParcelable("data");
            head.setImageBitmap(bm);}*/
        Bitmap bitmap = null;
        try {
            Log.e(TAG, "1231: "+head);
            bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(head));
            add_girls_head.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void setImageViewAura(Intent data){
        /*Bundle ex  = data.getExtras();
        if (ex!=null){
            Bitmap bm = ex.getParcelable("data");
            head.setImageBitmap(bm);}*/
        Bitmap bitmap = null;
        try {
            Log.e(TAG, "1231: "+aura);
            bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(aura));
            add_girls_aura.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void touploadFile_portrait(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",portrait+"");
        Log.e("filepath",portraitPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( portraitPath,fileKey, Tools.imageurl,params,context);
    }
    private void touploadFile_break_portrain(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",break_portrain+"");
        Log.e("filepath",break_portrainPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( break_portrainPath,fileKey, Tools.imageurl,params,context);
    }
    private void touploadFile_skillpic(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",skillpic+"");
        Log.e("filepath",skillpicPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( skillpicPath,fileKey, Tools.imageurl,params,context);
    }
    private void touploadFile_aura(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",aura+"");
        Log.e("filepath",auraPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( auraPath,fileKey, Tools.imageurl,params,context);
    }
    private void touploadFile_head(final Context context) {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        Log.e("filepath",head+"");
        Log.e("filepath",headPath+"");
        params.put("orderId", "zhansgan");
        uploadUtil.uploadFile( headPath,fileKey, Tools.imageurl,params,context);
    }
    private void initView() {
        add_girls_name=(EditText)findViewById(R.id.add_girls_name);
        add_girls_no=(EditText)findViewById(R.id.add_girls_no);
        add_girls_type=(EditText)findViewById(R.id.add_girls_type);
        add_girls_start=(EditText)findViewById(R.id.add_girls_start);
        add_girls_damage=(EditText)findViewById(R.id.add_girls_damage);
        add_girls_hit=(EditText)findViewById(R.id.add_girls_hit);
        add_girls_avoid=(EditText)findViewById(R.id.add_girls_avoid);
        add_girls_shooting=(EditText)findViewById(R.id.add_girls_shooting);
        add_girls_hp=(EditText)findViewById(R.id.add_girls_hp);
        add_girls_piercing=(EditText)findViewById(R.id.add_girls_piercing);
        add_girls_cirt_rate=(EditText)findViewById(R.id.add_girls_cirt_rate);
        add_girls_cirt_damage=(EditText)findViewById(R.id.add_girls_cirt_damage);
        add_girls_chain=(EditText)findViewById(R.id.add_girls_chain);
        add_girls_armor=(EditText)findViewById(R.id.add_girls_armor);
        add_girls_skill_name=(EditText)findViewById(R.id.add_girls_skill_name);
        add_girls_skill_cd=(EditText)findViewById(R.id.add_girls_skill_cd);
        add_girls_skill_introduction=(EditText)findViewById(R.id.add_girls_skill_introduction);
        add_girls_aura_introduction=(EditText)findViewById(R.id.add_girls_aura_introduction);
        add_girls_dubbing=(EditText)findViewById(R.id.add_girls_dubbing);
        add_girls_painter=(EditText)findViewById(R.id.add_girls_painter);
        add_girls_method=(EditText)findViewById(R.id.add_girls_method);
        add_girls_introduction=(EditText)findViewById(R.id.add_girls_introduction);

        back = (Button)findViewById(R.id.add_girls_back);
        add_girls_portrait=(ImageView)findViewById(R.id.add_girls_portrait);
        add_girls_break_portrait=(ImageView)findViewById(R.id.add_girls_break_portrait);
        add_girls_skill_pic=(ImageView)findViewById(R.id.add_girls_skill_pic);
        add_girls_aura=(ImageView)findViewById(R.id.add_girls_aura);
        add_girls_enter=(ImageView)findViewById(R.id.add_girls_enter);
        add_girls_head = (ImageView)findViewById(R.id.add_girls_head);
    }
}
