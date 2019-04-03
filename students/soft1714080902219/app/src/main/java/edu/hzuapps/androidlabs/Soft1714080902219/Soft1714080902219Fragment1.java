package edu.hzuapps.androidlabs.Soft1714080902219;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class Soft1714080902219Fragment1 extends Fragment {
    private SQLiteDatabase db1;
    //	private SQLiteDatabase db2;
    private MyDatabaseUtil myDatabaseUtil;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.soft_1714080902219_fragment1, container, false);
        LinearLayout LinearLayout1 = contentView.findViewById(R.id.news1);
        LinearLayout LinearLayout2 = contentView.findViewById(R.id.news2);
        LinearLayout LinearLayout3 = contentView.findViewById(R.id.news3);
        LinearLayout LinearLayout4 = contentView.findViewById(R.id.news4);
        LinearLayout1.setOnClickListener(new MyOnclick());
        LinearLayout2.setOnClickListener(new MyOnclick());
        LinearLayout3.setOnClickListener(new MyOnclick());
        LinearLayout4.setOnClickListener(new MyOnclick());

        return contentView;
    }
    public class MyOnclick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //参数二是数据库文件名
            myDatabaseUtil = new MyDatabaseUtil(getActivity(), "db1.db", null, 1);
            db1 = getActivity().openOrCreateDatabase("db1.db", Context.MODE_PRIVATE, null);
            /*/data/data/com.example.sqlite/databases必须存在，否则创建出错，最后是databases而不是database，不要忘了加's'*/
            //      db2 = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.sqlite/databases/db2.db3",null);---------------------
            /*创建表，并判断是否已经存在此表，没创建，则创建并初始化*/
            if (!myDatabaseUtil.tabIsExist("news")) {
                db1.execSQL("CREATE TABLE news (news_id int)");
            } else {
                Log.i("+++++++++++", "已经创建了，无需再创建");
            }
            String id;
            int user_id=0;
            TextView tv;
            System.out.print("db1开启");
            switch (view.getId()) {
                case R.id.news1:
                    tv=getActivity().findViewById(R.id.newsTextId1);
                    user_id=Integer.parseInt(tv.getText().toString());
                    break;
                case R.id.news2:
                    tv=getActivity().findViewById(R.id.newsTextId2);
                    user_id=Integer.parseInt(tv.getText().toString());
                    break;
                case R.id.news3:
                    tv=getActivity().findViewById(R.id.newsTextId3);
                    user_id=Integer.parseInt(tv.getText().toString());
                    break;
                case R.id.news4:
                    tv=getActivity().findViewById(R.id.newsTextId4);
                    user_id=Integer.parseInt(tv.getText().toString());
                    break;
                default:
                    break;

            }
            ContentValues values = new ContentValues();
            values.put("news_id", user_id);
            System.out.print("user_id:"+user_id);
            db1.insert("news", "user_id", values);
            Intent intent = new Intent(getActivity(), Soft1714080902219Activity.class);
            startActivity(intent);
        }
    }


    public class MyDatabaseUtil extends SQLiteOpenHelper {
        public MyDatabaseUtil(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
            //
        }

        // @Override
        public void onCreate(SQLiteDatabase arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        /**
         * * 判断某张表是否存在
         * * @param tabName 表名         *
         *
         * @return
         */
        public boolean tabIsExist(String tabName) {
            boolean result = false;
            if (tabName == null) {
                return false;
            }
            SQLiteDatabase db = null;
            Cursor cursor = null;
            try {
                db = this.getReadableDatabase();//此this是继承SQLiteOpenHelper类得到的
                String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tabName.trim() + "' ";
                cursor = db.rawQuery(sql, null);
                if (cursor.moveToNext()) {
                    int count = cursor.getInt(0);
                    if (count > 0) {
                        result = true;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            return result;
        }
    }
}