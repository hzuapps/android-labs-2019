package edu.hzuapps.androidlabs.soft1714080902228;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends AppCompatActivity {
    static List<Music> musicList = new ArrayList<Music>();
    private Button btnTurnToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        initMusicList();
        MusicAdapter adapter = new MusicAdapter(MusicListActivity.this, R.layout.music_list_item, musicList);
        ListView listView = (ListView) findViewById(R.id.music_listview);
        findView();
        addListener();
        listView.setAdapter(adapter);
    }

    void initMusicList() {
        Music music1=new Music("可ai女人",R.drawable.jielun0);
        Music music2=new Music("算什么男人",R.drawable.jielun1);
        Music music3=new Music("手写的从前",R.drawable.jielun2);
        Music music4=new Music("爱在西元前",R.drawable.jielun3);
        Music music5=new Music("稻香",R.drawable.jielun4);
        Music music6=new Music("给我一首歌的时间",R.drawable.jielun5);
        Music music7=new Music("红尘客栈",R.drawable.jielun6);
        Music music8=new Music("兰亭序",R.drawable.jielun7);
        Music music9=new Music("说好的幸福呢",R.drawable.jielun8);
        Music music0=new Music("算什么男人",R.drawable.jielun9);
        musicList.add(music0);
        musicList.add(music1);
        musicList.add(music2);
        musicList.add(music3);
        musicList.add(music4);
        musicList.add(music5);
        musicList.add(music6);
        musicList.add(music7);
        musicList.add(music8);
        musicList.add(music9);
    }

    void findView() {
        btnTurnToMain=(Button)findViewById(R.id.turn_to_main);
    }

    private void addListener() {
        btnTurnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicListActivity.this, MainActivity.class);
                startActivity(intent);
                MusicListActivity.this.finish();            }
        });
    }

}
