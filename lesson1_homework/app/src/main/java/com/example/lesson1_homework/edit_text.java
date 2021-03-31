package com.example.lesson1_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_text extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        EditText et = findViewById(R.id.et);

        Button bt = findViewById(R.id.bt_commit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                message = String.valueOf(et.getText());
                Intent intent = new Intent(edit_text.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("message",message);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}