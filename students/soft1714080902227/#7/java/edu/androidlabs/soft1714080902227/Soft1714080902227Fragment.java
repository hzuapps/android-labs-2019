package edu.androidlabs.soft1714080902227;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.tuku3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Soft1714080902227Fragment extends Fragment implements AdapterView.OnItemClickListener {


    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    ArrayList names = new ArrayList();
    ArrayList descs = new ArrayList();
    ArrayList<String> fileNames = new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo, container, false);
        Soft1714080902227Activity activity=(Soft1714080902227Activity)getActivity();
        gview = (GridView) view.findViewById(R.id.gview);
        //获取数据
        data_list = activity.getdataList();
        fileNames=activity.getfileNames();
        //新建适配器
        String [] from ={"image"};
        int [] to = {R.id.image};
        SimpleAdapter listItemAdapter=new SimpleAdapter(
                getActivity(),	//这里要使用此，这样才不会报错。
                data_list,
                R.layout.item,
                from,
                to
        );
        gview.setAdapter(listItemAdapter);
        gview.setOnItemClickListener(this);
        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =new Intent(getActivity(), Soft1714080902227Activity3.class);
        intent.putExtra("uri",fileNames.get(position));  //传图片地址
        startActivity(intent);
    }
}
