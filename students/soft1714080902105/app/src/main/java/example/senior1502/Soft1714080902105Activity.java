package example.senior1502;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import example.senior1502.http.AfBitmapGetter;

public class Soft1714080902105Activity extends AppCompatActivity
{
    final String TAG = "测试MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doDownload(View view)
    {
        String uri = "http://192.168.1.109:8080/demo/images/wuzhi10.jpg";

        AfBitmapGetter.get(uri, new AfBitmapGetter.Callback()
        {
            @Override
            public void onResult(AfBitmapGetter ab, Bitmap bitmap) throws Exception
            {
                // 这里可以直接更新界面
                ImageView imageView = (ImageView)findViewById(R.id.id_imageview);
                if(bitmap != null)
                {
                    imageView.setImageBitmap( bitmap );
                }
            }

        });
    }


}
