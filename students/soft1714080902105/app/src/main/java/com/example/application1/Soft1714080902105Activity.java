package com.example.application1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.application1.student.Student;
import com.example.application1.student.StudentAddActivity;
import com.example.application1.student.StudentManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Soft1714080902105Activity extends AppCompatActivity {

    StudentManager studentManager;
    MyListAdapter listAdapter;
    final int REQ_ADD_STUDENT = 101;
    public static final int TAKE_PHOTO=1;
    private ImageView picture;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button takePhoto  =(Button)findViewById(R.id.take_photo);
        picture=(ImageView)findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage =new File(getExternalCacheDir(),"output_image.jpg");
                try {
                    if(outputImage.exists())
                    {
                        outputImage.delete();
                    }
                         outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24)
                {
                    imageUri= FileProvider.getUriForFile(Soft1714080902105Activity.this,
                            "com.example.application1.fileprovider",outputImage);
                }
                else {
                    imageUri=Uri.fromFile(outputImage);

                }
                Intent intent =new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                 startActivityForResult(intent,TAKE_PHOTO);
            }
        });

        // 加载XML
        studentManager = new StudentManager(this);
        try{
            studentManager.load();
        }catch (FileNotFoundException e)
        {
            // 初次加载时XML文件不存在, 是正常的
        }
        catch (Exception e)
        {
        }

        // 初始化ListView
        listAdapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.id_listview);
        listView.setAdapter(listAdapter); // 设置适配器
        listAdapter.setData( studentManager.getAll());
    }

    // 点击右上角的 '添加'
    public void onOption(View view)
    {
        Intent intent = new Intent(this, StudentAddActivity.class);
        startActivityForResult(intent, REQ_ADD_STUDENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

       if(requestCode==TAKE_PHOTO)
       {
           if(resultCode==RESULT_OK)
           {
               Bitmap bitmap= null;
               try {
                   bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                   picture.setImageBitmap(bitmap);
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               }
           }
       }
        if(requestCode == REQ_ADD_STUDENT)
        {
            if(resultCode == RESULT_OK)
            {
                String id = data.getStringExtra("id");
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                studentManager.add( new Student(id, name, phone));
                listAdapter.notifyDataSetChanged();
            }
        }
    }


    private class MyListAdapter extends BaseAdapter
    {
        List<Student> dataCopy = new ArrayList<>();

        public MyListAdapter()
        {
        }
        public void setData(List<Student> data)
        {
            this.dataCopy = data;
        }

        @Override
        public int getCount()
        {
            return dataCopy.size();
        }

        @Override
        public Object getItem(int position)
        {
            return dataCopy.get( position);
        }

        @Override
        public long getItemId(int position)
        {
            return position; // 如果不懂，先跳过
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // 创建控件
            if (convertView == null)
            {
                convertView = getLayoutInflater()
                        .inflate(R.layout.list_item_student, parent, false);
            }
            // 获取数据
            Student it = (Student) getItem(position);
            ((TextView)convertView.findViewById(R.id.id_item_id)).setText(it.id);
            ((TextView)convertView.findViewById(R.id.id_item_name)).setText(it.name);
            ((TextView)convertView.findViewById(R.id.id_item_phone)).setText(it.phone);
            return convertView;
        }
    }
}
