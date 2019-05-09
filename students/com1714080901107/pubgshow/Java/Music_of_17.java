package com.example.pubgshow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Music_of_17 extends AppCompatActivity {

   public abstract class MainActivity extends AppCompatActivity implements View.OnClickListener{ 
    
private EditText et_path; 
    
private ImageView bt_play; 
   
 private VideoView videoView; 
    
private MediaController controller; 
    
@Override 
    
protected void onCreate(Bundle savedInstanceState){ 
    super.onCreate(savedInstanceState); 
    
setContentView(R.layout.music_of_17); 
    
et_path=(EditText) findViewById(R.id.et_path); 
   
 bt_play=(ImageView) findViewById(R.id.bt_play); 
   
 videoView=(VideoView) findViewById(R.id.sv); 
   
 controller=new MediaController(this); 
   
 videoView.setMediaController(controller); 
    
bt_play.setOnClickListener(this); 
   
 } 
   
 @Override 
   
 public void onClick(View v){ 
    
switch (v.getId()){ 
   
 case R.id.bt_play: 
    play(); 
  
  break; 
   
  } 
   
 } 
 
  private void play() { 
    if(videoView!=null&&videoView.isPlaying()){ 
   
 bt_play.setImageResource(android.R.drawable.ic_media_play); 
   
 videoView.stopPlayback(); 
   
 return; 
    
} 
  
  videoView.setVideoPath(et_path.getText().toString()); 
  
  videoView.start(); 
    
bt_play.setImageResource(android.R.drawable.ic_media_pause); 
    
videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { 
   
 @Override 
   
 public void onCompletion(MediaPlayer mediaPlayer) { 
  
  bt_play.setImageResource(android.R.drawable.ic_media_play); 
    } 

}
