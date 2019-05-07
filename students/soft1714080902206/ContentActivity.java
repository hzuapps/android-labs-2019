package com.exampler.mynews;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.exampler.mynews.databinding.ContentActivityBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentActivity extends AppCompatActivity {

    private ContentActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.content_activity);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String time = simpleDateFormat.format(date);
        String content = intent.getStringExtra("content");

        News news = new News(title, image, time, content);

        NewsVM newsVM = new NewsVM(news);

        binding.setVm(newsVM);

    }
}
