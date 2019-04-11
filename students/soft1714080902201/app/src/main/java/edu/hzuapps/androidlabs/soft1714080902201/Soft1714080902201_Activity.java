package edu.hzuapps.androidlabs.soft1714080902201;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Soft1714080902201_Activity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "edu.hzuapps.androidlabs.soft1714080902201.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902201);
        /** 标题栏还没做好
         *  ActionBar actionBar = getSupportActionBar();
         * if (actionBar != null){
         *   actionBar.hide();
         * }
         */
    }


    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Soft1714080902201_Activity_2.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendMessage2 (View view){
        Intent intent2 = new Intent(this, Soft1714080902201_Activity_Recommendation.class);
        startActivity(intent2);
    }

    public void sendMessage3 (View view){
        Intent intent3 = new Intent(this, Soft1714080902201_Activity_3.class);
        startActivity(intent3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.my_item:
                Toast.makeText(this,"该功能待实现",Toast.LENGTH_SHORT).show();
                break;
            case R.id.recommend_item:
                Toast.makeText(this,"该功能待实现",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
