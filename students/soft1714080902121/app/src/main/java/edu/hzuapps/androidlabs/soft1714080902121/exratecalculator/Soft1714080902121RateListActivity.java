package edu.hzuapps.androidlabs.soft1714080902121.exratecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Soft1714080902121RateListActivity extends AppCompatActivity {

    private File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private File txtFile = new File(dir, "RateList.txt");
    private String strRateList;
    private TextView txtRateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratelist);

        Intent intent = getIntent();
        strRateList = (String) intent.getStringExtra("strRateList");
        txtRateList = (TextView) findViewById(R.id.txtRateList);
        txtRateList.setText(strRateList);

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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateText: {
                Write();
                Toast.makeText(Soft1714080902121RateListActivity.this, "在" + dir + "生成" + txtFile.getName(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void Write() {
        String RateList = strRateList;
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
}
