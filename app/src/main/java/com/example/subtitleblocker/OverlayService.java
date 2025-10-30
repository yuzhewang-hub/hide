package com.example.subtitleblocker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.WindowManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class OverlayService extends Service {
    private static final String TAG = "OverlayService";
    private static final String CHANNEL_ID = "overlay_service_channel";
    private static final int NOTIFICATION_ID = 1;
    private OverlayView overlayView;
    private WindowManager windowManager;
    public static OverlayService serviceInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
        serviceInstance = this;
        createNotificationChannel();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        overlayView = new OverlayView(this);

        try {
            windowManager.addView(overlayView, overlayView.getParams());
            Log.d(TAG, "Overlay view added");
        } catch (Exception e) {
            Log.e(TAG, "Error adding overlay view", e);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 启动前台服务，防止被系统杀死
        startForeground(NOTIFICATION_ID, createNotification());
        Log.d(TAG, "Service started as foreground");
        return START_STICKY; // 服务被杀死后自动重启
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null && windowManager != null) {
            try {
                windowManager.removeView(overlayView);
                Log.d(TAG, "Overlay view removed");
            } catch (Exception e) {
                Log.e(TAG, "Error removing overlay view", e);
            }
        }
        serviceInstance = null;
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "遮挡块服务",
                    NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("字幕遮挡工具正在运行");
            channel.setShowBadge(false);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("字幕遮挡工具")
                .setContentText("遮挡块正在运行中")
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
    }

    public OverlayView getOverlayView() {
        return overlayView;
    }
}
