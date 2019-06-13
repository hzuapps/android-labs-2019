package edu.hzuapps.androidlabs.androiddazuoye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    public ArrayList list=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

    }

    public void Click(View view){
        EditText text=(EditText)findViewById(R.id.e);
        String s=text.getText().toString();
        list.add(s);
        ListView listView=(ListView)findViewById(R.id.l);
        ArrayAdapter<String> ad =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(ad);
    }
}
