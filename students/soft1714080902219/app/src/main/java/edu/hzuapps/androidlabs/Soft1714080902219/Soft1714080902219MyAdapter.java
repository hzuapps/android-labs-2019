package edu.hzuapps.androidlabs.Soft1714080902219;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Soft1714080902219MyAdapter extends BaseAdapter {
    Context c;
    ArrayList<HashMap<String, String>> arrayList;
    public Soft1714080902219MyAdapter(Context c, ArrayList<HashMap<String, String>> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(c,R.layout.soft_1714080902219_item_xml,null);
        TextView txTitle = view.findViewById(R.id.text_title);
        txTitle.setText(arrayList.get(i).get("title"));
        TextView txDate = view.findViewById(R.id.text_date);
        txDate.setText(arrayList.get(i).get("pdate"));
        TextView txSrc = view.findViewById(R.id.text_src);
        txSrc.setText(arrayList.get(i).get("src"));
        return view;
    }

}
