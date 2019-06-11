package edu.hzuapps.androidlabs.soft1714080902218;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class TrdActivity extends Activity {
    private EditText meEditTextContent;
    private TextView tvContent;
    private TextView mTextViewSdcard;

    private Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.blue);
        setContentView(R.layout.trd_activity);

        button = (Button)findViewById(R.id.btn) ;


        //绑定监听器
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(TrdActivity.this, DownloadFile.class) ;
                startActivity(intent) ;
            }

        });
        // 获取到相应的控件
        meEditTextContent = (EditText) findViewById(R.id.etContent);
        tvContent = (TextView) findViewById(R.id.tvContent);
        mTextViewSdcard = (TextView) findViewById(R.id.tvsdcard);
    }

    /**
     * 保存文件到手机内存中
     *
     * @param v
     */
    public void save(View v) {
        // 文件输出流
        FileOutputStream out = null;
        // 缓冲写入器
        BufferedWriter writer = null;

        // 要写入的数据
        String str = meEditTextContent.getText().toString();

        try {
            // 用于将数据保存到指定的文件中，
            // 第一个参数是文件名，第二个参数是文件的操作模式
            // Context.MODE_PRIVATE写入的内容会覆盖原文件中的内容
            // Context.MODE_APPEND如果文件已经存在，就往文件中追加内容，不存在就创建文件
            out = openFileOutput("data.txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));

            // 往外写数据
            writer.write(str);
            Toast.makeText(TrdActivity.this, "保存成功！", Toast.LENGTH_SHORT)
                    .show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 载入文件信息
     *
     * @param v
     */
    public void load(View v) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            // 读取指定文件的信息
            in = openFileInput("data.txt");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            // 将读到的数据赋值给line，当line不为空的时候进行追加
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            // 将读取到的信息在文本中进行输出
            tvContent.setText(builder.toString());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    // 关闭输入流
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 将信息写入到sd卡中
     * 读写sd卡需要权限，在AndroidManifest.xml中设置android.permission.WRITE_EXTERNAL_STORAGE
     *
     * @param v
     */
    public void saveTosdcard(View v) {
        //文件输出流
        FileOutputStream fos = null;
        //要写入的数据
        String str = meEditTextContent.getText().toString();

        //设置文件路径 ，第一个参数是文件保存的路径，null放在根目录下，第二个参数是文件名
        File file = new File(getExternalFilesDir(null), "/data.txt");

        try {
            fos = new FileOutputStream(file);
            fos.write(str.getBytes());
            Toast.makeText(TrdActivity.this, "保存到SD卡成功！", Toast.LENGTH_SHORT)
                    .show();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    // 关闭输入流
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从sd卡中读取信息进行显示
     * 也需要权限，但在写入的时候已经给了权限，读取的时不需要分配权限
     * 需要设置时在AndroidManifest.xml中设置android.permission.READ_EXTERNAL_STORAGE
     * @param v
     */
    public void loadFromSdcard(View v) {
        //设置文件路径，第一个参数是文件保存的路径，null放在根目录下，第二个参数是文件名
        File file = new File(getExternalFilesDir(null), "/data.txt");
        //文件输入流
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            //使用缓冲来读
            byte[] buf = new byte[1024];// 每次读取1K的数据
            StringBuilder sb = new StringBuilder();
            // 当还存在数据的时候就一直读取
            while (is.read(buf) != -1) {
                sb.append(new String(buf).trim());
            }

            //将数据输入到文本中显示
            mTextViewSdcard.setText(sb.toString());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    // 关闭输入流
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
