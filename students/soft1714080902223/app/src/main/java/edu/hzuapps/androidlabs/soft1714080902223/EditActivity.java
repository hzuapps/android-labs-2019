package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import cn.qqtheme.framework.picker.DateTimePicker;
import edu.hzuapps.androidlabs.presenter.TaskService;

public class EditActivity extends AppCompatActivity {

    private Button mBtn;
    private EditText title;
    private EditText content;
    private int position;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 调用父亲保存的状态
        super.onCreate(savedInstanceState);
        // 布局方法
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.edit_content);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //标题栏为空时，提示并焦聚（暂未处理空白字符）
                if(title.getText().toString().trim().length() == 0){
                    //Toast toast = Toast.makeText(EditActivity.this, "请输入标题", Toast.LENGTH_SHORT);
                    new AlertDialog.Builder(EditActivity.this)
                            .setTitle("提示")
                            .setMessage("标题不能为空")
                            .setIcon(R.drawable.ic_error_black_24dp)
                            .setPositiveButton("确定", null)
                            .setNegativeButton("取消", null)
                            .show();

//                    int[] location = new int[2];
//                    title.getLocationInWindow(location);
//                    toast.setGravity(Gravity.TOP, location[0], location[1]);
//                    toast.show();
                    title.requestFocus();
                }
                //正文为空时，提示并焦聚（暂未处理空白字符）
                else if(content.getText().toString().trim().length() == 0) {
                    //Toast toast = Toast.makeText(EditActivity.this, "请输入正文内容", Toast.LENGTH_SHORT);
                    new AlertDialog.Builder(EditActivity.this)
                            .setTitle("提示")
                            .setMessage("正文不能为空")
                            .setIcon(R.drawable.ic_error_black_24dp)
                            .setPositiveButton("确定", null)
                            .setNegativeButton("取消", null)
                            .show();
//                    int[] location = new int[2];
//                    content.getLocationInWindow(location);

//                    toast.setGravity(Gravity.TOP, location[0], location[1]);
//                    toast.show();
                    content.requestFocus();
                }
                else{
                    TaskService taskService = TaskService.INSTANCE.getTaskService();
                    String titleStr = title.getText().toString();
                    String contentStr = content.getText().toString();
                    String timeStr = time.getText().toString();
                    Toast.makeText(EditActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    //跳转到详情页
                    Intent intent = new Intent(EditActivity.this,
                            DetailActivity.class);
                    //如果是新建就创建新的任务，若是编辑就更新任务
                    if(position == -1){
                        taskService.save(titleStr, contentStr, timeStr);
                        position = 0;
                    }
                    else{
                        taskService.update(position, titleStr, contentStr, timeStr);
                    }
                    //把id传递给DetailActivity
                    intent.putExtra("position", position);
                    startActivity(intent);

                }
            }
        });
        //点击时间文本时，显示时间选择器
        time = findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePicker picker = new DateTimePicker(EditActivity.this, DateTimePicker.HOUR_24);//24小时值
                picker.setDateRangeStart(2017, 1, 1);//日期起点
                picker.setDateRangeEnd(2020, 1,1);//日期终点
                picker.setTimeRangeStart(0, 0);//时间范围起点
                picker.setTimeRangeEnd(23, 59);//时间范围终点
                //获取文本上的时间作为默认时间
                String timeStr = time.getText().toString();
                Calendar date = Calendar.getInstance();
                date.setTime(TaskService.INSTANCE.stringToDate(timeStr));
                //设置默认值为之前的时间
                picker.setSelectedItem(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH),date.get(Calendar.HOUR_OF_DAY),
                                date.get(Calendar.MINUTE));
                picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        String lastTime = String.format("%s-%s-%s %s:%s", year, month, day, hour, minute);
                        time.setText(lastTime);
                    }
                });
                picker.show();

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        //如果没获取到，则意味着是新建数据
        position = intent.getIntExtra("position", -1);
        if (position != -1) {
            Map<String, String> map = TaskService.INSTANCE.get(position);
            title.setText(map.get("title"));
            content.setText(map.get("content"));
            time.setText(map.get("last_time"));
        }
        else {
            //若为新建，填入现在的时间
            Date now = new Date();
            time.setText(TaskService.INSTANCE.timeToString(now));
        }

    }

}
