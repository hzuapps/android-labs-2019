package com.example.myproject2;

        import android.graphics.drawable.Drawable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

public class Soft1714080902105Activity extends AppCompatActivity {

    Drawable[] images = new Drawable[3];
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 加载3张图片
        images[0] = getDrawable(R.drawable.im_055);
        images[1] = getDrawable(R.drawable.im_j20);
        images[2] = getDrawable(R.drawable.im_df21d);

        // 设置监听器
        Button button = (Button)findViewById(R.id.id_playNext);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playNext( );
            }
        });

    }

    public void playNext ()
    {
        // 0, 1, 2 ... 0,1, 2 轮番显示
        index += 1;
        if(index >=3) index = 0;

        ImageView imageView = (ImageView) findViewById( R.id.id_imageBox);
        imageView.setImageDrawable( images[ index] );
    }

}
