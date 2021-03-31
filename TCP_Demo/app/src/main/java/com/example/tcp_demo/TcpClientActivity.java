package com.example.tcp_demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientActivity extends AppCompatActivity {
    EditText ed_ip,ed_port,ed_msg;
    Button btn_send,btn_connect;
    TextView tv_CO2,tv_temp,tv_shidu,tv_jiaquan,tv_pm;
    Socket socket = null;
    boolean TCPconnected = false;           //TCP连接状态
    boolean Reading = false;                //读写线程是否再执行

    OutputStream outputStream = null;
    InputStream inputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_ip = findViewById(R.id.et_ip);
        ed_port = findViewById(R.id.et_port);
        ed_msg = findViewById(R.id.et_msg);
        btn_connect = findViewById(R.id.btn_connect);
        btn_send = findViewById(R.id.btn_send);
        tv_CO2 = findViewById(R.id.tv_CO2);
        tv_jiaquan = findViewById(R.id.tv_jiaquan);
        tv_pm = findViewById(R.id.tv_pm);
        tv_shidu = findViewById(R.id.tv_shidu);
        tv_temp = findViewById(R.id.tv_temp);
    }

    public void play(View view)
    {
        if(thisif()){
            ThreadSendData t1 = new ThreadSendData();
            t1.start();
        }else{
            //:TODO 自定义格式错误提示
            return;
        }
    }

    public void link(View view)
    {
        if(TCPconnected == false)
        {
            TCPconnected = true;
            Reading = true;

            //创建线程用于初始化Socket
            Connect_Thread connect_Thread = new Connect_Thread();
            Connect_Thread.start();
            btn_connect.setText("断开连接");
        }else{
            btn_connect.setText("连接服务器");
            TCPconnected = false;
            try{
                //关闭Socket
                socket.close();
                socket = null;
                Reading = false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

   public  class Connect_Thread extends Thread
    {
        public void run()
        {
            InetAddress ipAddress;
            try {
                if(socket == null)
                {
                    ipAddress = InetAddress.getByName(ed_ip.getText().toString());
                    int port = Integer.valueOf(ed_port.getText().toString());
                    socket = new Socket(ipAddress,port);
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();

                    //新建一个线程读取数据
                    ThreadReadData t1 = new ThreadReadData();
                    t1.start();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    class ThreadReadData extends Thread
    {
        public void run()
        {
            //存储服务器发来的消息
            String textdata;
            //根据Reading变量判断是否读数据
            while (Reading)
            {
                try {
                    final byte[] ReadBuff = new byte[2048];
                    final int Buffsize;
                    //inputStream 获取从服务器发来的数据
                    Buffsize = inputStream.read(ReadBuff);
                    if(Buffsize == -1)
                    {
                        Reading = false;
                        socket.close();
                        socket = null;
                        TCPconnected = false;
                        btn_connect.setText("连接服务器");
                    }else{
                        //根据服务器编码格式，对数据进行转换
                        textdata = new String(ReadBuff,0,Buffsize,"UTF-8");

                        //:TODO 解析数据格式并改变TextView
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    class ThreadSendData extends Thread
    {
        public void run()
        {
            try {
                //使用outputStream输出数据
                outputStream.write(ed_msg.getText().toString().getBytes());
                //数据发送完毕后会自动断开TCP连接，需要恢复为初始状态
                //但是还需要等待服务器的回复，所以不能close socket
                TCPconnected = false;
                btn_connect.setText("连接服务器");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public boolean thisif()
    {
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        String ip = ed_ip.getText().toString();
        String port = ed_port.getText().toString();
        String msg = ed_msg.getText().toString();

        if (ip == null || ip.length() == 0 || port == null || port.length() == 0 || msg == null || msg.length() == 0)
        {
            message.setMessage("各数据不能为空");
            AlertDialog m1 = message.create();
            m1.show();
            return false;
        }else{
            return true;
        }
    }

}
