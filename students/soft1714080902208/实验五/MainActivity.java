package edu.hzuapps.soft1714080902208;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "data.txt";
    private static final String TAG = "MainActivity";
    private TextView dataView;
    private Button saveButton;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataView = (TextView) findViewById(R.id.data_view);
        saveButton = (Button) findViewById(R.id.save_button);
        loadButton = (Button) findViewById(R.id.load_button);
        setListener();
    }

    private void setListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    saveData();
                } catch (IOException e) {
                }
                Toast.makeText(MainActivity.this, "保存成功",
                        Toast.LENGTH_SHORT).show();
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    loadData();
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }
            }
        });
    }

    public void saveData() throws IOException {
        OutputStream out = this.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        /* 参数一: 文件名。
         * 如果文件不存在，Android会自动创建它。创建的文件保存在/data/data/<package name>/files目录下
         * 参数二: 文件操作模式参数。代表该文件是私有数据，只能被应用本身访问。
         * */
        Writer writer = new OutputStreamWriter(out);
        try {
            String str = "来自保存在内部存储设备的数据";
            writer.write(str);
        } finally {
            writer.close();
        }
    }

    public void loadData() throws FileNotFoundException, IOException {
        BufferedReader reader = null;
        StringBuilder data = new StringBuilder();
        try {
            InputStream in = this.openFileInput(FILENAME);
            Log.i(TAG, in.toString());
            reader = new BufferedReader(new InputStreamReader(in));
            String line = new String();
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            dataView.setText(data);
        } catch (FileNotFoundException e) {
            dataView.setText("没有发现保存的数据");
        } finally {
            reader.close();
        }
    }
}
