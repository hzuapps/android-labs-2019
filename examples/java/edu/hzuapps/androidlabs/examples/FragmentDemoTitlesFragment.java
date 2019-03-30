package edu.hzuapps.androidlabs.examples;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.hzuapps.androidlabs.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentDemoTitlesFragment extends Fragment {

    public FragmentDemoTitlesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 构造出界面层次
        View view = inflater.inflate(R.layout.fragment_titlesfragment, container, false);

//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.text_demo);
//        container.addView(textView);

        // 点击按钮时显示内容
        FragmentActivity activity = getActivity();
        final FragmentDemoActivity demoActivity = (FragmentDemoActivity) activity;
        Button button = (Button) view.findViewById(R.id.button_open);
        button.setText(button.getText() + ": 100");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoActivity.showContent(100);
            }
        });

        return view;
    }
}
