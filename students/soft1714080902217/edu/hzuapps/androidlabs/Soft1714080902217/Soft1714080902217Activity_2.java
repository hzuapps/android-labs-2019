package edu.hzuapps.androidlabs.soft1714080902217;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Soft1714080902217Activity_2 extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    private SharedPreferences sharedPreferences;


    private String fileName = "my_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902217_2);

        textView = (TextView) findViewById(R.id.tv);
        editText = (EditText) findViewById(R.id.et);

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void WriteSharedPreferences(View view) {
        String content = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("content", content);
        boolean isCommitSuccessful = editor.commit();
        Toast.makeText(Soft1714080902217Activity_2.this, "数据保存成功：" + isCommitSuccessful, Toast.LENGTH_SHORT).show();
    }

    public void ReadSharedPreferences(View view) {
        String content = sharedPreferences.getString("content", "default");
                textView.setText(content);
    }

}
