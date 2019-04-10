package edu.hzuapps.androidlabs.soft1714080902121.exratecalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Soft1714080902121RateListActivity extends AppCompatActivity{

    private Button btn;
    private File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private File txtFile = new File(dir,"RateList.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = (Button) findViewById(R.id.createText);
        setContentView(R.layout.activity_ratelist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    private void Write()
    {
        String RateList = (String) (this.getResources().getString(R.string.rate_list01 ))+(String) (this.getResources().getString(R.string.rate_list02 ));
        try {
            if (!txtFile.exists()) {
                txtFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(txtFile);
            fos.write(new String(RateList).getBytes("utf-8"));
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createText(View v) {
        switch(v.getId()){
            case R.id.createText: {
                Write();
                Toast.makeText(Soft1714080902121RateListActivity.this,"在"+dir+"生成"+txtFile.getName(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
