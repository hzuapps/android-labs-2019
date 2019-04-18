package com.example.tuku3;
import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Soft1714080902227Activity extends AppCompatActivity implements View.OnClickListener{//,AdapterView.OnItemClickListener{
    /*private ImageButton weixin;
    private ImageButton contact;
    private ImageButton find;
    private ImageButton me;*/
    private Button weixin;
    private Button contact;
    private Button find;
    private Button me;
    private Soft1714080902227Fragment2 soft1714080902227Fragment2;
    private Soft1714080902227Fragment soft1714080902227Fragment;
    private Soft1714080902227Fragment3 soft1714080902227Fragment3;
    private Soft1714080902227Fragment4 soft1714080902227Fragment4;
    private ListView listview;
    private List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
    private GridView gridView;
    ArrayList names = new ArrayList();
    ArrayList descs = new ArrayList();
    ArrayList<String> fileNames = new ArrayList();    //ArrayList是动态数组
    ArrayList<HashMap<String, Object>> listItems = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    public  List<Map<String, Object>> getdataList()
    {
        showMediaPhoto();
        return dataList;
    }
    public ArrayList<String> getfileNames()
    {
        showMediaPhoto();
        return fileNames;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weixin = (Button) findViewById(R.id.weixin1);
        contact = (Button) findViewById(R.id.weixin2);
        find = (Button) findViewById(R.id.weixin3);
        me = (Button) findViewById(R.id.weixin4);
        weixin.setOnClickListener(this);
        contact.setOnClickListener(this);
        find.setOnClickListener(this);
        me.setOnClickListener(this);
        //该按钮点击一次
        weixin.performClick();
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        showMediaPhoto();
        //simpleAdapter = new SimpleAdapter(this, dataList, R.layout.dialog_item, new String[] { "image" }, new int[] { R.id.show});
        // simpleAdapter = new SimpleAdapter(this, dataList, R.layout.item, new String[] { "image" }, new int[] { R.id.imageView1}); //两条都可以，（Activity,XX，xml,XX,控件）
        //gridView.setAdapter(simpleAdapter);
        //gridView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.weixin1:
                if (soft1714080902227Fragment == null) {
                    soft1714080902227Fragment = new Soft1714080902227Fragment();
                }
                transaction.replace(R.id.fragment_container, soft1714080902227Fragment);
               // weixin.setImageResource(R.drawable.home);
                break;
            case R.id.weixin2:
                if (soft1714080902227Fragment2 == null) {
                    soft1714080902227Fragment2 = new Soft1714080902227Fragment2();
                }
                transaction.replace(R.id.fragment_container, soft1714080902227Fragment2);
                //contact.setImageResource(R.drawable.home);
                break;
            case R.id.weixin3:
                if (soft1714080902227Fragment3 == null) {
                    soft1714080902227Fragment3 = new Soft1714080902227Fragment3();
                }
                transaction.replace(R.id.fragment_container, soft1714080902227Fragment3);
              //  find.setImageResource(R.drawable.home);
                break;
            case R.id.weixin4:
                if (soft1714080902227Fragment4 == null){
                    soft1714080902227Fragment4 = new Soft1714080902227Fragment4();
                }
                transaction.replace(R.id.fragment_container, soft1714080902227Fragment4);
               // me.setImageResource(R.drawable.home);
                break;
        }
        transaction.commit();
    }



    private void showMediaPhoto() {
        names.clear();
        descs.clear();
        //通过ContentResolver查询所有图片信息(存储在外部存储器上的图片的Uri)
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取图片的显示名
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            //这里的desc为空，没有提供具体的描述信息
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            Log.d("GsonUtils", "desc=" + desc);
            //故描述信息用文件的大小来展示
            String size = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
            Log.d("GsonUtils", "size=" + size);
            //获取图片的保存位置的数据
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            names.add(name);
            //descs.add(desc);
            //故描述信息用文件的大小来展示
            descs.add(size);
            //将图片的保存位置的数据转换成字符串形式的路径
            String filePath = new String(data, 0, data.length - 1);
            Log.d("GsonUtils", "filePath=" + filePath);
            fileNames.add(filePath);
        }

       for (int i = 0; i < names.size(); i++) {
           Map<String, Object> map = new HashMap<String, Object>();
           map.put("image", fileNames.get(i));
           dataList.add(map);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //四个参数的含义。1，group的id,2,item的id,3,是否排序，4，将要显示的内容
        menu.add(0, 1, 0, "菜单一");
        menu.add(0, 2, 0, "菜单二");
        menu.add(0, 3, 0, "菜单三");
        menu.add(0, 4, 0, "菜单四");
        SubMenu sub = menu.addSubMenu("子菜单");
        sub.add(0, 5, 0, "子菜单一");
        sub.add(0, 6, 0, "子菜单二");
        sub.add(0, 7, 0, "子菜单三");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "菜单一", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "菜单二", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "菜单三", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "菜单四", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "子菜单一", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(this, "子菜单二", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(this, "子菜单三", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    /*//长按文本可以打开
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v == mTv) {
            menu.add(0, 1, 0, "上下文菜单一");
            menu.add(0, 2, 0, "上下文菜单二");
            menu.add(0, 3, 0, "上下文菜单三");

        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }
*/
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "上下文菜单一", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "上下文菜单二", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "上下文菜单三", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

}