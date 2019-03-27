package edu.hzuapps.androidlabs.soft1714080902217;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Soft1714080902217Activity_1 extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[18];
    private int[] ids = new int[]{R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7,
            R.id.bt8, R.id.bt9, R.id.bt10, R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15, R.id.bt16, R.id.bt17, R.id.bt18
    };

    private TextView textView;
    private String expression = "";
    private boolean end = false;
    private int countOperate = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1);
        for (int i = 0; i < ids.length; i++) {
            buttons[i] = (Button) findViewById(ids[i]);
            buttons[i].setOnClickListener(this);
        }
        textView = (TextView) findViewById(R.id.contentText);
    }

    public void onClick(View view) {
        int id = view.getId();
        Button button = (Button) view.findViewById(id);
        String current = button.getText().toString();
        if (end) {
            expression = "";
            end = false;
        }
        if (current.equals("CE")) {
            expression = "";
            countOperate = 0;
        } else if (current.equals("Backspace")) {
            if (expression.length() > 1) {
                expression = expression.substring(0, expression.length() - 1);
                int i = expression.length() - 1;
                char tmp = expression.charAt(i);
                char tmpFront = tmp;
                for (; i >= 0; i--) {
                    tmpFront = expression.charAt(i);
                    if (tmpFront == '.' || tmpFront == '+' || tmpFront == '-' || tmpFront == '*' || tmpFront == '/') {
                        break;
                    }
                }
                if (tmp >= '0' && tmp <= '9') {
                    countOperate = 0;
                } else if (tmp == tmpFront && tmpFront != '.') countOperate = 2;
                else if (tmpFront == '.') countOperate = 1;
            } else if (expression.length() == 1) {
                expression = "";
            }
        } else if (current.equals(".")) {
            if (expression.equals("") || countOperate == 2) {
                expression += "0" + current;
                countOperate = 1;
            }
            if (countOperate == 0) {
                expression += ".";
                countOperate = 1;
            }
        } else if (current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/")) {
            if (countOperate == 0) {
                expression += current;
                countOperate = 2;
            }
        }/*else if(current.equals("=")){
            double result = (double) Math.round(count()*100)/100;
            expression+="="+result;
            end = true;
        }*/ else {
            if (expression.length() >= 1) {
                char tmp1 = expression.charAt(expression.length() - 1);
                if (tmp1 == '0' && expression.length() == 1) {
                    expression = expression.substring(0, expression.length() - 1);
                } else if (tmp1 == '0' && expression.length() > 1) {
                    char tmp2 = expression.charAt(expression.length() - 2);
                    if (tmp2 == '+' || tmp2 == '-' || tmp2 == '*' || tmp2 == '/') {
                        expression = expression.substring(0, expression.length() - 1);
                    }
                }
            }
            expression += current;
            if (countOperate == 2 || countOperate == 1) countOperate = 0;
        }
        textView.setText(expression);
    }

}