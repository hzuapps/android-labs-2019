package com.example.tuku3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Soft1714080902227Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoshow);
        /*ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null)
        {
            actionbar.hide();
        }*/
        ImageView imageView=(ImageView)findViewById(R.id.showphoto);
        Intent intent = getIntent();
        String uri=intent.getStringExtra("uri");
        File file = new File(uri);
        ImageView img = (ImageView) findViewById(R.id.showphoto);
        if(file.exists()){
            Bitmap bm = BitmapFactory.decodeFile(uri);
            img.setImageBitmap(bm);
        }
    }
    @Override
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
