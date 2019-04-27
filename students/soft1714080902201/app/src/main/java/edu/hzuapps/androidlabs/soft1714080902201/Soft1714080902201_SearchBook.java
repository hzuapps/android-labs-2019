package edu.hzuapps.androidlabs.soft1714080902201;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Soft1714080902201_SearchBook extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "edu.hzuapps.androidlabs.soft1714080902201.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902201__search_book);
    }

    public void search(View view) {
        Intent intent = new Intent(this, Soft1714080902201_Activity_2.class);
        EditText editText = (EditText) findViewById(R.id.search);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
