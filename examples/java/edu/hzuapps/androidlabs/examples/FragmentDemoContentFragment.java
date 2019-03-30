package edu.hzuapps.androidlabs.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hzuapps.androidlabs.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentDemoContentFragment extends Fragment {

    public static FragmentDemoContentFragment newInstance(int contentId) {
        FragmentDemoContentFragment fragment = new FragmentDemoContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("contentId", contentId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static FragmentDemoContentFragment newInstance(Bundle bundle) {
        FragmentDemoContentFragment fragment = new FragmentDemoContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FragmentDemoContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 构造出界面层次
        View view = inflater.inflate(R.layout.fragment_contentfragment, container, false);

        //TextView textView = (TextView) view.findViewById(R.id.text_content);
        //textView.setText(R.string.demo_content + getContentId());

        Log.i(getClass().getSimpleName(), "在碎片中显示内容");

        return view;
    }

    public int getContentId() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt("contentId") : 0;
    }
}
