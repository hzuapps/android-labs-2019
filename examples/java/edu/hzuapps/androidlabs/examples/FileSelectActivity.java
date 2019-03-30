package edu.hzuapps.androidlabs.examples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class FileSelectActivity extends AppCompatActivity {

    public static final String TAG = FileSelectActivity.class.getSimpleName();

    public static final String CONTENT_AUTH = "edu.hzuapps.androidworks.FILE_PROVIDER";

    private Intent mResultIntent;
    // App的内部存储目录
    private File mPrivateRootDir;
    // “images”子目录
    private File mImagesDir;
    // “images”目录下的文件
    File[] mImageFiles;

    // 对应的文件名
    String[] mImageFilenames;
    GridView mFileGridView;

    ImageView mLastSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_select);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
        // 初始化图片数据 files/images
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

        // 创建Intent,返回请求文件的App
        mResultIntent = new Intent("com.example.myapp.ACTION_RETURN_FILE");
        // 获取内部存储目录: files
        mPrivateRootDir = getFilesDir();
        // 获取内部存储子目录: files/images
        mImagesDir = new File(mPrivateRootDir, "images");
        // 列出目录下的文件 - 列举目录的方法
        mImageFiles = mImagesDir.listFiles();
        // TODO 从网上下载一些图片保存到目录中
        if (mImageFiles == null) {
            //
        }

        // 活动的结果暂时设置为null——即不带数据返回
        // 参见 startActivityForResult()
        setResult(Activity.RESULT_CANCELED, null);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
        // 初始化选择界面 GridView
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

        // 使用ListView显示可分享图片.
        mFileGridView = (GridView) findViewById(R.id.file_list);
        // 准备列表数据
        mImageFilenames = new String[mImageFiles.length];
        //for(File imageFile : mImageFiles) {
        for (int i = 0; i < mImageFilenames.length; i++) {
            mImageFilenames[i] = mImageFiles[i].getAbsolutePath();
        }
        // 准备图片适配器
        FileItemAdapter adapter = new FileItemAdapter(this, mImageFiles);
        mFileGridView.setAdapter(adapter);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
        // 响应文件选择 - 选择后准备好要分享的文件信息
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
        mFileGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long rowId) {
                // 先修改样式
                if (mLastSelected != null) {
                    mLastSelected.setBackgroundColor(Color.WHITE);
                }
                mLastSelected = (ImageView) view;
                mLastSelected.setBackgroundColor(Color.BLUE);
                // 取到文件
                File file = new File(mImageFilenames[index]);
                // 将文件分享给另一个应用 | Activity
                FileSelectActivity.this.shareToAnotherApp(file);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_image, menu);
        return true;
    }

    // 点击分享按钮后将文件分享给其他App
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 将文件分享给另一个应用 | Activity
    private void shareToAnotherApp(File file) {
        // 1. 使用FileProvider构造内容（文件）Uri
        Uri fileUri = null;
        try {
            fileUri = FileProvider.getUriForFile( //
                    FileSelectActivity.this, CONTENT_AUTH, file);
        } catch (IllegalArgumentException e) {
            // 避免分享文件时出错 - RuntimeException
            Log.e(TAG, "文件无法分享: " + file.getAbsolutePath());
            // 我们能生成的那些Content URI所对应的文件，
            // 必须是那些在meta-data文件中包含<paths>标签的目录内的文件。
            // 如果调用getUriForFile()方法所要获取的文件不在我们指定的目录中，
            // 会收到一个IllegalArgumentException。

            // 问题: 必须定义才能分享!如何解决?
        }

        if (fileUri != null) { // 有文件分享
            // 2. 分配临时读权限到指定的Uri
            mResultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 3. 将文件Uri放到结果中（此时未触发分享）
            mResultIntent.setDataAndType(fileUri, getContentResolver().getType(fileUri));
            this.setResult(Activity.RESULT_OK, mResultIntent);
        } else { // 没有文件分享
            mResultIntent.setDataAndType(null, "");
            this.setResult(RESULT_CANCELED, mResultIntent);
        }
    }

}

class FileItemAdapter extends BaseAdapter {

    Context mContext;
    File[] mImageFiles;

    public FileItemAdapter(Context context, File[] imageFiles) {
        this.mContext = context;
        this.mImageFiles = imageFiles;
    }

    @Override
    public int getCount() {
        return mImageFiles.length;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup viewGroup) {
        // 创建图片显示项
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(128, 128));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Log.i("图片", mImageFiles[index].getAbsolutePath());
        imageView.setImageURI(Uri.parse(mImageFiles[index].getAbsolutePath()));
        //imageView.setImageBitmap(BitmapFactory.decodeFile(mImageFiles[index].getAbsolutePath()));
        return imageView;
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public Object getItem(int index) {
        return null;
    }
}
