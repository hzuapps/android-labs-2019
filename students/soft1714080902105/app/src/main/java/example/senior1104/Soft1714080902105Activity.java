package example.senior1104;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import example.senior1104.student.Student;
import example.senior1104.student.StudentAddActivity;
import example.senior1104.student.StudentManager;

public class Soft1714080902105Activity extends AppCompatActivity
{
    StudentManager studentManager;
    MyListAdapter listAdapter;
    final int REQ_ADD_STUDENT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    //////// 定义适配器 //////////////////
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
