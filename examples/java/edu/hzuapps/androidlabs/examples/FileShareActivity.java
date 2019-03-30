package edu.hzuapps.androidlabs.examples;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

import edu.hzuapps.androidlabs.R;

public class FileShareActivity extends AppCompatActivity {

    public static final String TAG = FileShareActivity.class.getSimpleName();

    private Intent mRequestFileIntent;
    private ParcelFileDescriptor mInputPFD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_share);

        mRequestFileIntent = new Intent(Intent.ACTION_PICK);
        mRequestFileIntent.setType("image/png");

        ((Button) findViewById(R.id.button_share)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(mRequestFileIntent, 0);
            }
        });

        ((Button) findViewById(R.id.button_download_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FileShareActivity.this, NetworkActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnIntent) {
        // 根据结果代码判断是否成功返回
        if (resultCode != RESULT_OK) {
            return; // 用户取消选择
        } else { // 从Intent中取出Uri
            Uri returnUri = returnIntent.getData();
            try { // 从Uri尝试打开文件 （读权限）
                mInputPFD = getContentResolver().openFileDescriptor(returnUri, "r");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, "文件不存在!");
                return;
            }
            // 取到该文件的描述
            FileDescriptor fd = mInputPFD.getFileDescriptor();

            // 获取MIME类型
            String mimeType = getContentResolver().getType(returnUri);

            // 通过FileProvider的游标获取文件大小等信息
            Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
            // 游标中各个列即对应文件的各种信息
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            returnCursor.moveToFirst();
            TextView nameView = (TextView) findViewById(R.id.text_filename);
            TextView sizeView = (TextView) findViewById(R.id.text_file_size);
            nameView.setText("选择的文件为: "+returnCursor.getString(nameIndex));
            sizeView.setText("大小为: "+Long.toString(returnCursor.getLong(sizeIndex)/1024) +"kb");

        }
    }
}
