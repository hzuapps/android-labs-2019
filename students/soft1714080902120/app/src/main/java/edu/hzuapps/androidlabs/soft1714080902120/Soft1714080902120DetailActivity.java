package edu.hzuapps.androidlabs.soft1714080902120;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Soft1714080902120DetailActivity extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);

            // 从Intent获取数据
            int igid=getIntent().getIntExtra("soft1714080902120_igid",0);
            String lvde = getIntent().getStringExtra("soft1714080902120_lvde");
            String repu = getIntent().getStringExtra("soft1714080902120_repu");

            // 获取特定的视图
            ImageView IgidView=(ImageView) findViewById(R.id.HistoryPeople_large_imageView);
            TextView LvdeView = (TextView) findViewById(R.id.HistoryPeople_lvde_textView);
            TextView RepuView = (TextView) findViewById(R.id.HistoryPeople_repu_textView);

            // 根据数据设置视图展现
            IgidView.setImageResource(igid);
            LvdeView.setText(lvde);
            RepuView.setText(repu);
        }

    }