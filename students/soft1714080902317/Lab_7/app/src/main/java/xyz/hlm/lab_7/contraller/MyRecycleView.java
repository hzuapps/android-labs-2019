package xyz.hlm.lab_7.contraller;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;

import xyz.hlm.lab_7.model.BitmapAdapter;
import xyz.hlm.lab_7.model.MyBitmap;

public class MyRecycleView {

    private List<Uri> list = new ArrayList<>();
    private MyBitmap myBitmap;

    public MyRecycleView(Activity activity) {
        myBitmap = MyBitmap.instance(activity);
    }

    public void setting(RecyclerView recyclerView){
        initBitmap();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        BitmapAdapter adapter = new BitmapAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void initBitmap(){
        Uri[] uris = myBitmap.getUris();
        for (int i =0;i<uris.length;i++){
                list.add(uris[i]);
        }
    }

}
