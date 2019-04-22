package edu.hzuapps.androidlabs.Soft1714080902219;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Soft1714080902219Fragment3 extends Fragment {
    private View view,view2;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.soft_1714080902219_fragment3,container,false);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        LinearLayout linearLayout=getActivity().findViewById(R.id.LinearLayout03);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Soft1714080902219TakephotoActivity.class);
                startActivity(intent);
                //Log.d("mylog","setOnClickListener;运行startActivity(intent);");

                Log.d("mylog","setOnClickListener;运行");
            }
        });


    }

}
