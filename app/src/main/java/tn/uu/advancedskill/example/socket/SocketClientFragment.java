package tn.uu.advancedskill.example.socket;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import tn.uu.baselibrary.utils.socket.ISocketClientBinder;
import tn.uu.baselibrary.utils.socket.SocketClientService;

import static android.content.Context.WIFI_SERVICE;

public class SocketClientFragment extends Fragment {
    //Service相关
    private Intent intent;
    private MyConn conn;
    private ISocketClientBinder myBinder;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        startConnect();

        startAndBindService();

        return new View(getActivity());
    }


    private void startConnect() {

        //连接
        String strip = "192.168.1.153:4580".toString().trim();
        String[] stripx = strip.split(":");
        if (stripx.length > 1) {
            // 开始连接
            String ip = stripx[0];
            int port = Integer.parseInt(stripx[1]);
            if (myBinder != null) {
                myBinder.startSocketWithIp(ip, port);
            }
        }


        //向服务端发送信息
        myBinder.sendMsgToServer("向服务端发送消息");

        //断开连接
        if (myBinder != null) {
            myBinder.disConnect();
        }

        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = (ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "." + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff);

    }


    private void startAndBindService() {
        intent = new Intent(getContext(), SocketClientService.class);
        conn = new MyConn();

        //绑定服务，
        // 第一个参数是intent对象，表面开启的服务。
        // 第二个参数是绑定服务的监听器
        // 第三个参数一般为BIND_AUTO_CREATE常量，表示自动创建bind
        getContext().bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            myBinder = (ISocketClientBinder) iBinder;
            initSetBinder();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("xxx", "服务断开连接了");
        }
    }

    //配置下binder
    private void initSetBinder() {
        // 接收到消息
        myBinder.setCallbackHandler(new Handler(Looper.getMainLooper()) {
            @Override
            public void dispatchMessage(@NonNull Message msg) {
                Log.e("xxx", "收到的消息是: " + msg.obj);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //取消绑定服务
        if (conn != null) {
            getContext().unbindService(conn);
        }
    }
}