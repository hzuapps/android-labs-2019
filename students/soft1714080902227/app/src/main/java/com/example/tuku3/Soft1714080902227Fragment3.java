package com.example.tuku3;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Soft1714080902227Fragment3 extends Fragment {
    private EditText et;
    String wangzhi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find, container, false);
        Soft1714080902227Activity activity = (Soft1714080902227Activity) getActivity();
        et = view.findViewById(R.id.input);
        EditText edt = (EditText) view.findViewById(R.id.input);
        final String content = et.getText().toString();
        Button btn = (Button) view.findViewById(R.id.search);
        if (wangzhi == null){
           et.setText("海贼王");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String path = et.getText().toString().trim();
                if (TextUtils.isEmpty(path)) {
                    Toast.makeText(getActivity(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getActivity(), Soft1714080902227Activity2.class);
                    wangzhi = et.getText().toString();
                    String URL = "https://image.baidu.com/search/index?tn=baiduimage&word=" + wangzhi;//URL是根据使用百度图片搜索某个关键字得到的url截取得到的
                    Bundle bundle = new Bundle();
                    bundle.putString("address", URL);  //Bundle主要用于传递数据；它保存的数据，是以key-value(键值对)的形式存在的。
                    intent.putExtras(bundle); //传输一个bundle来传输网址
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}