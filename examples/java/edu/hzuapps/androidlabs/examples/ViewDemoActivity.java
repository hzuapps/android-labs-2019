package edu.hzuapps.androidlabs.examples;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class ViewDemoActivity extends BackActivity {

    String[] examples = {
            "LinearLayout", "RelativeLayout"
            //, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, //
                R.layout.activity_view_demo_item, examples);

        ListView listView = (ListView) findViewById(R.id.view_demo_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Class activityClass = null;
                Log.i("按钮", "第" + index + "个");
                if (index == 0) {
                    activityClass = GridViewOneActivity.class;
                }
                if (activityClass != null) {
                    Intent intent = new Intent(getApplicationContext(), activityClass);
                    startActivity(intent);
                }

            }
        });

    }

}
