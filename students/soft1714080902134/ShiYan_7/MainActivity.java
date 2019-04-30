package edus.hzuapp.androidlabs.soft1714080902134;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TAKE_PHOTO = 1;

    private  ImageView picture;

    private  Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button3=(Button) findViewById(R.id.button_3);
        Button sendRequest=(Button) findViewById(R.id.test_button);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        sendRequest.setOnClickListener(this);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override

            public  void onClick(View v){
                Intent intent=new Intent(MainActivity.this, Soft1714080902134PowerActivity.class);
                startActivity(intent);
            }

        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24){
                    imageUri = FileProvider.getUriForFile(MainActivity.this,"com.example.cameraalbumtest.fileprovider",outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent , TAKE_PHOTO);
            }
        });
    }


    @Override

    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


    public void onClick(View v) {
        if(v.getId() == R.id.test_button)
        {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread (new Runnable(){
                @Override
                public void run(){
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url("https://raw.githubusercontent.com/17727263252/android-labs-2019/master/students/soft1714080902134/shiyan_6/Android.json").build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        parseJSONWithGSON(responseData);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
    }
    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        int i=0;
        for(App app:appList){
            Log.d("MainActivity","id: "+app.getId());

            Log.d("MainActivity","version: "+app.getVersion());

             Log.d("MainActivity","name: "+app.getName());

        }
    }


}
