package com.example.menu;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.provider.Telephony.Mms.Part.FILENAME;


public class CPSelect extends AppCompatActivity {

    private List<CaiPu>caiPuList=new ArrayList<>();
    private StringBuilder stringBuilder=new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpselect);
        getDatasync();
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

    public void getDatasync(){
        OkHttpUtils
                .post()
                .url("http://apis.juhe.cn/cook/index")
                .addParams("key","ec094bb8c7cd8ad35114c5fa0c81d678")
                .addParams("cid","10")
                .addParams("rn","30")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject jsonObject1=jsonObject.getJSONObject("result");
                            JSONArray jsonArray=jsonObject1.getJSONArray("data");

                            for(int i=0;i<30;i++){
                                CaiPu caiPu=new CaiPu();
                                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                                caiPu.setTitle(jsonObject2.getString("title"));
                                caiPu.setImtro(jsonObject2.getString("imtro"));
                                caiPu.setIngredients(jsonObject2.getString("ingredients"));
                                caiPu.setBurden(jsonObject2.getString("burden"));
                                //caiPu.setAlbum(jsonObject2.getString("albums"));
                                caiPuList.add(caiPu);
                                stringBuilder.append(caiPu.ToString());
                            }
                            saveTextIntoInternalStorage(stringBuilder.toString());
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
