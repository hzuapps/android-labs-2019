package edu.hzuapps.androidlabs.soft1714080902227;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tuku.R;
public class Soft1714080902227Activity3 extends AppCompatActivity {

 private String[] data={
         "图库信息","建议反馈","版本更新"
 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null)
        {
            actionbar.hide();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Soft1714080902227Activity3.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }
}
