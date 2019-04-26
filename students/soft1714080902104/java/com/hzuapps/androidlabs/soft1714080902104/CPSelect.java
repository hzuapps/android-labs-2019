package com.example.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.menu.Model.CaiPu;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.provider.Telephony.Mms.Part.FILENAME;


public class CPSelect extends AppCompatActivity {

    private List<CaiPu>caiPuList=new ArrayList<>();

    private TextView cp_caiming_text;
    private ImageView cp_image;
    private TextView cp_jianjieneirong_text;
    private TextView cp_cailiao_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpselect);
        cp_image=(ImageView)findViewById(R.id.cp_tupian_image);
        cp_caiming_text=(TextView)findViewById(R.id.cp_caiming_text);
        cp_cailiao_text=(TextView)findViewById(R.id.cp_cailiao_text);
        cp_jianjieneirong_text=(TextView)findViewById(R.id.cp_jianjieneirong_text);
        caiPuList=((DataApplication)getApplication()).GetDataList();
        setDatasync();
    }

    private void saveTextIntoInternalStorage(String text) {
        // 获取内部存储目录
        File dir = this.getFilesDir();
        //File dir = getCacheDir();

        if (dir.isDirectory()) {
//            dir.mkdir();
//            dir.mkdirs();
        }

        if (dir.isFile()) {
            // D:/Abc.txt , -> D:/Abc1.txt ->D:/abc.txt
        }

        File file = new File(dir, FILENAME);

//        try {
//            File = File.createTempFile(FILENAME, null, dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (file.exists()) { // 判断文件是否存在
            file.canRead();
            file.canWrite();
            file.canExecute();

            file.getFreeSpace();
            file.getTotalSpace();
        }

        FileOutputStream fos = null;  // 字节流  | char | cn : gbk 2 bytes, utf8 3 bytes

        try { // 使用API打开输出流
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            //FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes()); // 写入内容
            fos.close(); // 关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileReader reader = null; // char

        try {
            reader = new FileReader(file.getAbsoluteFile());
            BufferedReader bReader = new BufferedReader(reader);
            String line = bReader.readLine();
            bReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),"存储数据成功",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"存储数据为：",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),text.toString(),Toast.LENGTH_SHORT).show();
    }

    public void setDatasync(){

        cp_caiming_text.setText(caiPuList.get(0).getTitle());
        cp_cailiao_text.setText(caiPuList.get(0).getBurden());
        cp_jianjieneirong_text.setText(caiPuList.get(0).getImtro());
        Glide.with(getApplicationContext()).load(caiPuList.get(0).getAlbum()).into(cp_image);
    }
}
