package edu.hzuapps.androidlabs.soft1714080902125;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.helloworldactivity.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Analysis extends AppCompatActivity implements View.OnClickListener{

    TextView responsetText;
    public ImageView imageView;
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        Button sendRequest = (Button) findViewById(R.id.b1);
        responsetText = (TextView) findViewById(R.id.b1);
        sendRequest.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.iv);
        // 此方法在主线程中调用，可以用来刷新ui
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        imageView.setImageBitmap((Bitmap) msg.obj);
                        break;
                    case 0:
                        Toast.makeText(Analysis.this, "请求失败",
                                Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }
        };
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.b1){
            sendRequestWithOkHttp();
            Toast.makeText(Analysis.this,"影名解析成功",Toast.LENGTH_SHORT).show();

        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("https://raw.githubusercontent.com/CAIHAOXIN/android-labs-2019/master/students/soft1714080902125/movies.json")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData =response.body().string();
                    parseJSONWithGSON(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendRequesWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    {
                        if(reader != null){
                            try{
                                reader.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        if(connection != null){
                            connection.disconnect();
                        }
                    }
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:appList){
            Log.d("Analysis","id: "+app.getId());
            Log.d("Analysis","id: "+app.getName());
        }
    }


    private void showResponse(final String response){
        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                responsetText.setText(response);
            }
        });
    }
    public void click(View view) {
        final String path = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=1e5633469a8fa0ec6bca6c5f47fe328b/6a600c338744ebf866ca954ad2f9d72a6059a708.jpg";
        final File file = new File(getCacheDir(), getFileName(path));
        if (file.exists()) {
            Toast.makeText(Analysis.this, "从缓存中读取图片", Toast.LENGTH_SHORT)
                    .show();
            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bm);
        } else {
            Toast.makeText(Analysis.this, "从网上下载图片", Toast.LENGTH_SHORT)
                    .show();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        URL url = new URL(path);
                        HttpURLConnection connection = (HttpURLConnection) url
                                .openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);
                        connection.connect();
                        if (connection.getResponseCode() == 200) {
                            InputStream is = connection.getInputStream();
                            FileOutputStream fos = new FileOutputStream(file);

                            byte[] b = new byte[1024];
                            int len = 0;
                            while ((len = is.read(b)) != -1) {
                                fos.write(b, 0, len);
                            }
                            fos.close();
                            Bitmap bm = BitmapFactory.decodeFile(file
                                    .getAbsolutePath());
                            Message msg = new Message();
                            msg.obj = bm;
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } else {
                            Message msg = handler.obtainMessage();
                            msg.what = 0;
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            };
            thread.start();
        }
    }

    public String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);

    }
}

