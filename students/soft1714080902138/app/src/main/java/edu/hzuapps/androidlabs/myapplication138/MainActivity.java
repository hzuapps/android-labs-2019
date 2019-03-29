package edu.hzuapps.androidlabs.myapplication138;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent = new Intent(MainActivity.this, Button1.class);
                                            startActivity(intent);

                                        }
                                    }
        );


        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent = new Intent(MainActivity.this, Button2.class);
                                            startActivity(intent);

                                        }
                                    }
        );

        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent = new Intent(MainActivity.this, Button3.class);
                                            startActivity(intent);

                                        }
                                    }
        );


        Button button4 = (Button) findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent = new Intent(MainActivity.this, Button4.class);
                                            startActivity(intent);

                                        }
                                    }
        );

        Button button5 = (Button) findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent = new Intent(MainActivity.this, Button5.class);
                                            startActivity(intent);

                                        }
                                    }
        );


        Button button6 = (Button) findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent = new Intent(MainActivity.this, Button6.class);
                                            startActivity(intent);

                                        }
                                    }
        );


    }
}
