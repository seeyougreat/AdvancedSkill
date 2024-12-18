package tn.uu.advancedskill.example.socket;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import tn.uu.baselibrary.utils.socket.SocketTCPServer;

public class SocketServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //显示当前IP地址
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = (ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "." + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff);

        //启动服务端
        SocketTCPServer socketTCPServer = new SocketTCPServer(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) { //服务端接收到的消息
                Log.e("xxx", "服务端接收到的消息" + msg.obj);
            }
        });

        socketTCPServer.sendToClient("这是服务端发送的信息");

    }

}