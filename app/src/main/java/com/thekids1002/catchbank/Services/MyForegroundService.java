package com.thekids1002.catchbank.Services;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

import com.thekids1002.catchbank.MainActivity;
import com.thekids1002.catchbank.R;

public class MyForegroundService extends Service {

    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        // Thực hiện các công việc khởi tạo ở đây
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Thực hiện công việc chạy ngầm ở đây

        // Tạo thông báo Foreground
        Notification notification = createNotification();
        startForeground(NOTIFICATION_ID, notification);

        // Trả về START_STICKY để dịch vụ được khởi động lại khi bị hủy bởi hệ điều hành
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Thực hiện các công việc dọn dẹp ở đây
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        // Tạo thông báo cho Foreground Service
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Foreground Service")
                .setContentText("Ứng dụng đang chạy ngầm")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent);

        return builder.build();
    }
}