package edu.hzuapps.androidlabs.examples;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class FragmentDemoActivity extends BackActivity {

    public static final String TAG = FragmentDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    public boolean isMultiPane() {
        return getResources().getConfiguration().orientation //
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    public void showContent(int contentId) {
        Log.i(TAG, "显示内容: " + contentId);

        if (isMultiPane()) { // 宽屏 - 在碎片中显示
            FragmentDemoContentFragment contentFragment //
                    //= (FragmentDemoContentFragment) getFragmentManager() //
                    = (FragmentDemoContentFragment) getSupportFragmentManager() //
                    .findFragmentById(R.id.fragment_content);
            if (contentFragment == null //
                    || contentFragment.getContentId() != contentId) {
                // contentFragment = new FragmentDemoContentFragment();
                contentFragment = FragmentDemoContentFragment.newInstance(contentId);

                // 执行事务, 显示新碎片
                Log.i(TAG, "准备执行事务并显示新碎片……");
                FragmentTransaction fragTran = getSupportFragmentManager() //
                        .beginTransaction();
                // 设置过渡动画效果
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                //fragTran.addToBackStack("content");
                fragTran.replace(R.id.fragment_content, contentFragment);
                fragTran.commit(); // 事务提交,显示新内容

            }
        } else { // 竖屏, 小屏 - 在活动中显示
            Intent intent = new Intent(this, FragmentDemoContentActivity.class);
            //intent.setClass(this, FragmentDemoContentActivity.class);
            intent.putExtra("contentId", contentId);
            startActivity(intent);
        }
    }

}
