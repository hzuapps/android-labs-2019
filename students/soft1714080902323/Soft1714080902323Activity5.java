package androidlabs2019.students.soft1714080902323;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.LogRecord;

public class Soft1714080902323Activity5  extends Activity {
    protected static final int REQUEST_SUCCESS = 1;
    protected static final int REQUEST_ERROR = 0;
    EditText editText;
    ImageView imageView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902323_activity5);
        editText = (EditText) findViewById(R.id.editId);
        imageView = (ImageView) findViewById(R.id.imgId);
        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        imageView.setImageBitmap(bitmap);
                        break;

                    case REQUEST_ERROR:
                        Toast.makeText(getApplicationContext(), "服务器繁忙...", 1).show();
                        break;
                    default:
                        break;
                }

            }
        };
    }

    public void click(View v) {
        new Thread(){
            @Override
            public void run() {

                String path = editText.getText().toString().trim();

                try {
                    //如果总是取相同的图片，可以直接从缓存中取图片

                    File file = new File(getCacheDir(),"picture");
                    //Base64是谷歌提供的一种加密算法。
                    //File file = new File(getCacheDir(),new String(Base64.encode(path.getBytes(), Base64.DEFAULT)));
                    if(file.exists())
                    {
                        //文件存在，直接从文件中取数据
                        Message msg = Message.obtain();
                        msg.what = REQUEST_SUCCESS;
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                        System.out.println("从文件中获取...");
                    }
                    //如果是第一次访问，就直接从网络上获取
                    else{

                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //设置提交方式
                        conn.setRequestMethod("GET");
                        //设置最大读取时间
                        conn.setConnectTimeout(5000);
                        //得到状态码
                        if(conn.getResponseCode()==200)
                        {
                            //拿到输入流
                            InputStream in = conn.getInputStream();

                            //缓存到本地Cache文件夹中
                            File newFile = new File(getCacheDir(),"picture");
                            FileOutputStream fileOuputStream = new FileOutputStream(newFile);
                            //把图片写入文件中
                            int len = 0;
                            byte[] buffer = new byte[512];
                            while((len = in.read(buffer) ) >0 )
                            {
                                fileOuputStream.write(buffer,0,len);
                            }

                            //利用位图工厂得到位图（从流中解码）
                            //Bitmap bitmap = BitmapFactory.decodeStream(in);
                            //利用位图工厂得到位图（从文件中解码）
                            Bitmap bitmap = BitmapFactory.decodeFile(newFile.getAbsolutePath());

                            //利用handler给主线程发送消息，更新imageView
                            Message msg = Message.obtain();
                            msg.what = REQUEST_SUCCESS;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);

                            System.out.println("从网络中获取...");
                        }
                        else{
                            Message msg = Message.obtain();
                            msg.what = REQUEST_ERROR;
                            handler.sendMessage(msg);
                        }
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    Message msg = Message.obtain();
                    msg.what = REQUEST_ERROR;
                    handler.sendMessage(msg);
                }
            }

        }.start();
    }

}
