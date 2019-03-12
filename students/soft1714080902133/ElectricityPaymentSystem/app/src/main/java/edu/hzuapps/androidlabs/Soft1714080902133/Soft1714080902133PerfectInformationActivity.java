package edu.hzuapps.androidlabs.Soft1714080902133;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.hzuapps.androidlabs.Soft1714080902133.javabean.Soft1714080902133User;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

//惠大宿舍缴电费app
//注册后完善用户信息
public class Soft1714080902133PerfectInformationActivity extends AppCompatActivity {

    private Soft1714080902133User user = new Soft1714080902133User();

    // 显示位置声明
    private TextView perfectNumber;
    private TextView perfectNickname;
    private TextView perfectSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_perfectinformationactivity);

        Toolbar toolbar = findViewById(R.id.perfect_information_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }

        Intent intent = getIntent();
        String telephone = intent.getStringExtra("telephone");
        String password = intent.getStringExtra("password");
        user.setTelephone(telephone);
        user.setPassword(password);

// 显示位置id
        perfectNumber = findViewById(R.id.perfect_number_show);
        perfectNickname = findViewById(R.id.perfect_nickname_show);
        perfectSex = findViewById(R.id.perfect_sex_show);
    }


    //    根据结果修改显示
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    String number = null;
                    if (data != null) {
                        number = data.getStringExtra("number");
                    }
                    perfectNumber.setText(number);
                    user.setNumber(number);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    String nickname = null;
                    if (data != null) {
                        nickname = data.getStringExtra("nickname");
                    }
                    perfectNickname.setText(nickname);
                    user.setNickname(nickname);
                }
            case 2:
                if (resultCode == RESULT_OK) {
                    String sex = null;
                    if (data != null) {
                        sex = data.getStringExtra("sex");
                    }
                    perfectSex.setText(sex);
                    user.setSex(sex);
                }
            default:
        }
    }

    //提交 添加信息给User
    public void submit(View view) {
        register();
        Intent intent = new Intent(Soft1714080902133PerfectInformationActivity.this,Soft1714080902133HomeActivity.class);
        startActivity(intent);
    }

    private void register() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("number", user.getNumber())
                            .add("telephone", user.getTelephone())
                            .add("nickname", user.getNickname())
                            .add("sex", user.getSex())
                            .add("password", user.getPassword())
                            .build();
                    Request request = new Request.Builder()
                            .url(URL)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String data = null;
                    if (response.body() != null) {
                        data = response.body().string();
                    }
                    showResult(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //显示结果UI操作
    private void showResult(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (data.equals("true")) {
                    Toast.makeText(Soft1714080902133PerfectInformationActivity.this, "注册成功,将会在一秒后跳转首页",
                            Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Soft1714080902133PerfectInformationActivity.this, Soft1714080902133User.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Soft1714080902133PerfectInformationActivity.this,"此学号已被注册，如有问题请联系客服",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
