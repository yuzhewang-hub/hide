package com.example.subtitleblocker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.WindowManager;
import android.util.Log;

public class OverlayService extends Service {
    private static final String TAG = "OverlayService";
    private OverlayView overlayView;
    private WindowManager windowManager;
    public static OverlayService serviceInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
        serviceInstance = this;
        
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
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public OverlayView getOverlayView() {
        return overlayView;
    }
}

