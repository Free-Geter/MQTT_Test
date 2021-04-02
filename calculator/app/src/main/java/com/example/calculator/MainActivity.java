package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    Button btn_1; // 数字1
    Button btn_2; // 数字2
    Button btn_3; // 数字3
    Button btn_4; // 数字4
    Button btn_5; // 数字5
    Button btn_6; // 数字6
    Button btn_7; // 数字7
    Button btn_8; // 数字8
    Button btn_9; // 数字9
    Button btn_0; // 数字0
    Button btn_clear; // 清0
    Button btn_del; // 删除健
    Button btn_divide; // 除号
    Button btn_multiply; // *号
    Button btn_minus; // -号
    Button btn_plus; // +号
    Button btn_point; // 小数点
    Button btn_equals; // =
    EditText et_Text; // 显示文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取页面上的控件
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equals = (Button) findViewById(R.id.btn_equals);
        et_Text = (EditText) findViewById(R.id.et_Text);

        // 按钮的单击事件
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equals.setOnClickListener(this);

    }

    //定义第一个操作数和第二个操作数
    double d1 = 0, d2 = 0;
    //定义运算符
    String oprator = "";

    @Override
    public void onClick(View v) {
        String str = et_Text.getText().toString();

        switch (v.getId()) {
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_0:
            case R.id.btn_point:
                // 点击数字按钮和小数点时，在文本内追加内容
                et_Text.setText(str + ((Button) v).getText().toString());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                // 点击运算符按钮时，获取前面输入的第一个运算符
                d1 = Double.parseDouble(et_Text.getText().toString());
                // 添加到文本区域内
                et_Text.setText(str + " " + ((Button) v).getText().toString() + " ");
                // 获取点击的运算符
                oprator = ((Button) v).getText().toString();
                break;
            case R.id.btn_clear:
                // 清空文本内容
                et_Text.setText("");
                break;
            case R.id.btn_del:
                // 点击删除按钮，删除一个字符
                if (str != null && !str.equals("")) {
                    str = str.substring(0, str.length() - 1);
                    et_Text.setText(str);
                }
                break;
            case R.id.btn_equals:
                // 计算结果方法，获取第二个输入的数字
                int start = str.lastIndexOf(oprator);
                d2 = Double.parseDouble(str.substring(start + 1, str.length()));
                getResult(d1, d2, oprator);
                break;
        }
    }

    // 计算结果
    private void getResult(double d1, double d2, String oprator) {
        // 计算结果
        String str = et_Text.getText().toString();
        double result = 0;
        if (oprator.equals("+")) {
            result = d1 + d2;
        } else if (oprator.equals("-")) {
            result = d1 - d2;
        } else if (oprator.equals("×")) {
            result = d1 * d2;
        } else if (oprator.equals("÷")) {
            if (d2 == 0) {
                result = 0;
            } else {
                result = d1 / d2;
            }
        }

        // 如果不包含小数点则为小数和除法运算
        if (!str.contains(".") && oprator != "÷") {
            et_Text.setText(((int) result) + "");
        } else {
            et_Text.setText(result + "");
        }

    }

}