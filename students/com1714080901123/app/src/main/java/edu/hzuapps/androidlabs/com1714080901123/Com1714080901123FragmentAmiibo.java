package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class Com1714080901123FragmentAmiibo extends Fragment {


    public Com1714080901123FragmentAmiibo() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_com1714080901123_fragment_amiibo, container, false);

        ImageButton imageButton01 = (ImageButton)view.findViewById(R.id.imageButton01);
        ImageButton imageButton02 = (ImageButton)view.findViewById(R.id.imageButton02);

        imageButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1){
                clickImgButton(0);
            }
        });
        imageButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1){
                clickImgButton(1);
            }
        });

        return view;
    }

    public void clickImgButton(int i) {
        Intent intent = new Intent();
        intent.putExtra("imgButton",i); //通过按键传值，来确定activity应该显示的内容，从而不用创建对应按键数量的那么多个activity
        intent.setClass(getActivity(), Com1714080901123ActivityAmiibo.class);   //Fragment获取activity要用getActivity()
        getActivity().startActivity(intent);
    }
}
