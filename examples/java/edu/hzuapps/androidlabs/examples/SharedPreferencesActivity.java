package edu.hzuapps.androidlabs.examples;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.hzuapps.androidlabs.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    public static final String CFG_KEY = "edu.hzuapps.androidworks.examples.CONFIGS";

    public static final String SHOW_ITEMS = "showItems";

    private SharedPreferences mSharedPref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveValueToPreferences();
                showValueToPreferences();
            }
        });
    }

    /**
     * 保存设置值
     */
    private void saveValueToPreferences() {
        if (mSharedPref == null) {
            mSharedPref = this.getSharedPreferences(CFG_KEY, Context.MODE_PRIVATE);
            //mSharedPref = this.getPreferences(Context.MODE_PRIVATE);
        }
        // 从界面取出数据
        String value = ((EditText) findViewById(R.id.show_items)).getText().toString();
        int items = 0;
        try {
            items = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            //
        }
        // 写入数据
        SharedPreferences.Editor editor = mSharedPref.edit();
        // 将配置值保存到首选项存储中
        editor.putString(SHOW_ITEMS, value);
        editor.putInt(SHOW_ITEMS + "Int", items);
        //editor.putBoolean()

        editor.commit();
    }

    /**
     * 读取设置值
     */
    private void showValueToPreferences() {
        if (mSharedPref != null) {
            String showItems = mSharedPref.getString(SHOW_ITEMS, "");
            int items = mSharedPref.getInt(SHOW_ITEMS + "Int", -1);
            showItems = "显示条数: " + showItems;
            showItems = showItems + " - " + items;
            ((TextView) findViewById(R.id.text_content)) //
                    .setText(showItems.toCharArray(), 0, showItems.length());
        }
    }

}
