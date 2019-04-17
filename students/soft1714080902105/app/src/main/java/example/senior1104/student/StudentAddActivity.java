package example.senior1104.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import example.senior1104.R;

public class StudentAddActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);
        ((TextView)findViewById(R.id.id_titlebar_title)).setText("添加Student");
    }

    // 点击 '取消' 按钮
    public void onBack(View view)
    {
        super.onBackPressed();
    }

    // 点击'完成'按钮
    public void onOK(View view)
    {
        String id = ((EditText)findViewById(R.id.id_student_id)).getText().toString();
        String name = ((EditText)findViewById(R.id.id_name)).getText().toString();
        String phone = ((EditText)findViewById(R.id.id_phone)).getText().toString();

        // 设置返回码和返回数据
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        setResult(RESULT_OK, intent);
        finish();
    }
}
