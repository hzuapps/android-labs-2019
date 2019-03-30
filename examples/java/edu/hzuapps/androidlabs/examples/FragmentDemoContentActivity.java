package edu.hzuapps.androidlabs.examples;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class FragmentDemoContentActivity extends BackActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_fragment_content);

        if (getResources().getConfiguration().orientation //
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish(); // 此时用户切换屏幕时,停止活动
            return;
        }

        if (getIntent() != null) { // 必须有,才能取到contentId
            Log.i(getClass().getSimpleName(), "在活动中显示内容.");
            Bundle bundle = getIntent().getExtras();
            // 在这个活动中显示内容碎片
            FragmentDemoContentFragment fragment = //
                    FragmentDemoContentFragment.newInstance(bundle);

            getSupportFragmentManager() //
                    .beginTransaction() //
                    .replace(R.id.fragment_content, fragment) //
                    //.add(R.id.fragment_content, fragment) //
                    .commit();
        }
    }
}
