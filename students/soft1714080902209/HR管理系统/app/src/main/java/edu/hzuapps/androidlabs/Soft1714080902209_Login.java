package edu.hzuapps.androidlabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Soft1714080902209_Login extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            String[] data={"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango","Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(Soft1714080902209_Login.this,android.R.layout.simple_list_item_1,data);
            ListView listview=(ListView)findViewById(R.id.list_view);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    listview.setAdapter(adapter);
                    listview.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    listview.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    listview.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_login);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
