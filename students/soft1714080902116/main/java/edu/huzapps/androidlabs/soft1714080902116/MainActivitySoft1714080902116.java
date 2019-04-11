package edu.huzapps.androidlabs.soft1714080902116;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivitySoft1714080902116 extends AppCompatActivity implements View.OnClickListener{

   private EditText editText;


   @Override
   public void onClick(View v){
       switch (v.getId()){
           case R.id.button_3:
               String inputText = editText.getText().toString();
               Toast.makeText(MainActivitySoft1714080902116.this, inputText, Toast.LENGTH_SHORT).show();
               break;
               default:
                   break;
           }
       }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button button1 = (Button) findViewById(R.id.button_1);
        Button button3 = (Button) findViewById(R.id.button_3);
        editText = (EditText) findViewById(R.id.edit_text_1);
        button3.setOnClickListener(this);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(MainActivitySoft1714080902116.this, Main2ActivitySoft1714080902116.class);
                startActivity(intent);
            }

        });
    }
}
