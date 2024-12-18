package tn.uu.advancedskill.example;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tn.uu.baselibrary.R;
import tn.uu.baselibrary.utils.AlarmManagerUtils;
import tn.uu.baselibrary.utils.FileRWUtil;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {

                String channelId = "测试渠道";
                Notification notification = new Notification.Builder(MyService.this,channelId)
                        .setContentTitle("测试通知标题")
                        .setContentText("测试通知文本")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_baseline_delete_forever_24)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_baseline_delete_sweep_24))   //设置大图标
                        .build();


                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel( channelId,"测试渠道名称", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);

                notificationManager.notify(1123, notification);


                Log.d("xxxx", "run: -->>>>>>>>>>>>>>");
                SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String content = mSdf.format(new Date(System.currentTimeMillis())) + "\n";
                String filePath = "/sdcard/alarmclock.txt";
                FileRWUtil.INSTANCE.writeContentWithLine(content, filePath, true);
            }
        }).start();

//        AlarmManagerUtils.getInstance(getApplicationContext()).getUpAlarmManagerWorkOnOthers();

        return super.onStartCommand(intent, flags, startId);
    }
}


