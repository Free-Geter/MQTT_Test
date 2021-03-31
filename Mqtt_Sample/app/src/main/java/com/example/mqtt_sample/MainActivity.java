package com.example.mqtt_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {

    private TextView textView_Switch,textView_Clock,textView_Plus;

    private MqttClient client;
    private MqttConnectOptions options;
    private Handler handler;
    private ScheduledExecutorService scheduler;
    private String mqtt_sub_topic = "1805120301";
    private String mqtt_pub_topic = "1805120301_PC";
    private String host = "tcp://192.168.31.44:1883";
    private String userName = "android";
    private String passWord = "1805120301";
    private String mqtt_id = "1805120301";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_Clock = findViewById(R.id.Clock);
        textView_Switch = findViewById(R.id.Switch);
        textView_Plus = findViewById(R.id.Plus);

        textView_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView_Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView_Clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
